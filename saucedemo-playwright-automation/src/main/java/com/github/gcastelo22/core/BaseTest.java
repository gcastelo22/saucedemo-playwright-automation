package com.github.gcastelo22.core;

import com.microsoft.playwright.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * BaseTest handles the Playwright lifecycle, configuration loading and test evidence.
 * All Test Suites must extend this class to inherit the automation engine.
 */
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected static final Logger LOG = Logger.getLogger(BaseTest.class.getName());

    // Load environment configurations from HOCON file
    protected static final Config APP_CONFIG = ConfigFactory.load("config/env.conf");
    protected static final String BROWSER_TYPE = APP_CONFIG.getString("conf.browser");
    protected static final String URL = APP_CONFIG.getString("conf.url");
    protected static final boolean HEADLESS = APP_CONFIG.getBoolean("conf.headless");

    /**
     * JUnit Rule to retrieve the current test method name for file naming.
     */
    @Rule
    public TestName testName = new TestName();

    /**
     * Setup method executed before each test.
     * Initializes the Playwright engine, browser context and navigates to the base URL.
     */
    @Before
    public void setUp() {
        LOG.info("Initializing Playwright engine...");
        playwright = Playwright.create();

        LOG.info("Launching browser: " + BROWSER_TYPE);
        initializeBrowser();

        // Create an isolated browser context (like an incognito window)
        context = browser.newContext();

        // Set a global timeout from configuration (converted to milliseconds if necessary)
        context.setDefaultTimeout(APP_CONFIG.getDouble("conf.timeout"));

        page = context.newPage();
        LOG.info("Navigating to: " + URL);
        page.navigate(URL);
    }

    /**
     * Configures and launches the specific Browser instance based on env.conf settings.
     */
    private void initializeBrowser() {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(HEADLESS);

        switch (BROWSER_TYPE.toUpperCase()) {
            case "CHROMIUM" -> browser = playwright.chromium().launch(options);
            case "FIREFOX" -> browser = playwright.firefox().launch(options);
            case "WEBKIT" -> browser = playwright.webkit().launch(options);
            default -> throw new IllegalArgumentException("Browser type not supported: " + BROWSER_TYPE);
        }
    }

    /**
     * Teardown method executed after each test.
     * Captures a screenshot of the final state and closes all Playwright resources.
     */
    @After
    public void tearDown() {
        if (page != null) {
            takeScreenshot();
            LOG.info("Closing Playwright resources...");
            context.close();
            browser.close();
            playwright.close();
        }
    }

    /**
     * Captures a screenshot and saves it to the target/screenshots directory.
     * Playwright handles the file saving natively.
     */
    private void takeScreenshot() {
        String path = "target/screenshots/" + testName.getMethodName() + ".png";
        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
            LOG.info("Screenshot successfully saved to: " + path);
        } catch (Exception e) {
            LOG.warning("Failed to save screenshot: " + e.getMessage());
        }
    }
}