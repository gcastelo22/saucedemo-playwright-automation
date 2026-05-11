package com.github.gcastelo22.tests;

import com.github.gcastelo22.core.BaseTest;
import com.github.gcastelo22.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite focused on authentication scenarios.
 * Verifies both successful login and error handling for restricted accounts.
 */
public class LoginTest extends BaseTest {

    /**
     * Verifies that a standard user can log in successfully and reach the products page.
     */
    @Test
    public void shouldLoginSuccessfully() {
        LOG.info("Starting test: shouldLoginSuccessfully");

        new LoginPage(page)
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLogin()
                .verifyPageTitle("Products");

        LOG.info("Login successful test passed.");
    }

    /**
     * Verifies that a locked-out user receives the appropriate error message.
     */
    @Test
    public void shouldShowErrorForLockedOutUser() {
        LOG.info("Starting test: shouldShowErrorForLockedOutUser");

        LoginPage loginPage = new LoginPage(page);

        loginPage.enterUsername("locked_out_user")
                .enterPassword("secret_sauce")
                .clickLogin();

        // Validation of the specific error message displayed by SauceDemo
        String expectedError = "Sorry, this user has been locked out.";
        String actualError = loginPage.getErrorMessageText();

        Assert.assertTrue("Error message mismatch! Expected it to contain: " + expectedError,
                actualError.contains(expectedError));

        LOG.info("Locked out user test passed.");
    }
}