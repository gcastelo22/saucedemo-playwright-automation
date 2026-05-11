package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import com.microsoft.playwright.Page;

/**
 * Page Object representing the Login screen of Sauce Demo.
 * Extends BasePage to utilize modern Playwright interactions.
 */
public class LoginPage extends BasePage {

    // Selectors using CSS strings
    private final String usernameField = "#user-name";
    private final String passwordField = "#password";
    private final String loginButton = "#login-button";
    private final String errorMessage = "[data-test='error']";

    /**
     * Constructor for LoginPage.
     * @param page The Playwright Page instance.
     */
    public LoginPage(Page page) {
        super(page);
    }

    /**
     * Enters the username using the inherited "write" method.
     * @param user The username string.
     * @return Current instance for chaining.
     */
    public LoginPage enterUsername(String user) {
        write(usernameField, user);
        return this;
    }

    /**
     * Enters the password using the inherited "write" method.
     * @param pass The password string.
     * @return Current instance for chaining.
     */
    public LoginPage enterPassword(String pass) {
        write(passwordField, pass);
        return this;
    }

    /**
     * Clicks the login button and transitions to the ProductsPage.
     * @return A new instance of ProductsPage.
     */
    public ProductsPage clickLogin() {
        LOG.info("Attempting to login...");
        click(loginButton);
        return new ProductsPage(page);
    }

    /**
     * High-level action to perform a complete login.
     * Demonstrates the power of the Fluent pattern.
     * @return A new instance of ProductsPage.
     */
    public ProductsPage loginAsStandardUser() {
        return enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin();
    }

    /**
     * Retrieves the error message text displayed on the login page.
     * @return The error message string.
     */
    public String getErrorMessageText() {
        return getText(errorMessage);
    }
}