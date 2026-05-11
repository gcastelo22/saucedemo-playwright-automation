package com.github.gcastelo22.core;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.util.logging.Logger;

/**
 * BasePage serves as a parent for all Page Objects.
 * It centralizes common Playwright actions and leverages native auto-waiting capabilities.
 */
public abstract class BasePage extends BaseTest {

    protected Page page;
    protected static final Logger LOG = Logger.getLogger(BasePage.class.getName());

    /**
     * Constructor that initializes the Playwright Page instance.
     * @param page The Playwright Page object passed from the test or previous page.
     */
    public BasePage(Page page) {
        this.page = page;
    }

    /**
     * Waits for an element to be visible on the page.
     * Although Playwright performs auto-waiting before actions, this is useful for explicit assertions.
     * @param selector The string selector (CSS, XPath, etc.)
     */
    protected void waitForElementVisible(String selector) {
        LOG.info("Waiting for visibility of element: " + selector);
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    /**
     * Clicks on an element.
     * Playwright automatically waits for the element to be visible, enabled and stable.
     * @param selector The string selector.
     */
    protected void click(String selector) {
        LOG.info("Clicking on element: " + selector);
        page.click(selector);
    }

    /**
     * Fills a text field with the specified value.
     * The "fill" method is preferred over "type" as it clears the field and ensures the final state.
     * @param selector The string selector.
     * @param text The text to be entered.
     */
    protected void write(String selector, String text) {
        LOG.info("Writing text into element: " + selector);
        page.fill(selector, text);
    }

    /**
     * Retrieves the inner text of an element.
     * @param selector The string selector.
     * @return The trimmed string content of the element.
     */
    protected String getText(String selector) {
        String text = page.innerText(selector).trim();
        LOG.info("Text retrieved from [" + selector + "]: " + text);
        return text;
    }

    /**
     * Scrolls the element into view if needed.
     * @param selector The string selector.
     */
    protected void scrollToElement(String selector) {
        LOG.info("Scrolling to element: " + selector);
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**
     * Checks if an element is currently visible on the page.
     * @param selector The string selector.
     * @return true if visible, false otherwise.
     */
    protected boolean isElementPresent(String selector) {
        try {
            return page.isVisible(selector);
        } catch (Exception e) {
            LOG.warning("Element not present or error during check: " + selector);
            return false;
        }
    }
}