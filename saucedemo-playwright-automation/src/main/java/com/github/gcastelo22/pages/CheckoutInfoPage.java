package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import com.microsoft.playwright.Page;

/**
 * Page Object for the first step of Checkout (User Information).
 * Handles the input of customer details.
 */
public class CheckoutInfoPage extends BasePage {

    // Selectors
    private final String firstNameField = "#first-name";
    private final String lastNameField = "#last-name";
    private final String zipCodeField = "#postal-code";
    private final String continueButton = "#continue";

    /**
     * Constructor for CheckoutInfoPage.
     * @param page The Playwright Page instance.
     */
    public CheckoutInfoPage(Page page) {
        super(page);
    }

    /**
     * Fills the user details form and proceeds to the checkout overview.
     * * @param first The customer's first name.
     * @param last The customer's last name.
     * @param zip The postal/zip code.
     * @return A new instance of CheckoutOverviewPage.
     */
    public CheckoutOverviewPage fillInformation(String first, String last, String zip) {
        LOG.info("Entering checkout information for: " + first + " " + last);
        write(firstNameField, first);
        write(lastNameField, last);
        write(zipCodeField, zip);

        LOG.info("Clicking continue to go to Overview.");
        click(continueButton);

        return new CheckoutOverviewPage(page);
    }
}