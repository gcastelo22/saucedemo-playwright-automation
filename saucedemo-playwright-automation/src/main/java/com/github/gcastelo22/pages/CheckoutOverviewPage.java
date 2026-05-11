package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import com.microsoft.playwright.Page;
import org.junit.Assert;

/**
 * Page Object for the Checkout Overview screen (Final Review).
 * Handles the final step of the purchase and confirmation.
 */
public class CheckoutOverviewPage extends BasePage {

    // Selectors
    private final String finishButton = "#finish";
    private final String completeHeader = ".complete-header";

    /**
     * Constructor for CheckoutOverviewPage.
     * @param page The Playwright Page instance.
     */
    public CheckoutOverviewPage(Page page) {
        super(page);
    }

    /**
     * Completes the order by clicking the Finish button.
     * @return This instance to allow for final verification chaining.
     */
    public CheckoutOverviewPage clickFinish() {
        LOG.info("Clicking the Finish button to complete the order.");
        click(finishButton);
        return this;
    }

    /**
     * Final assertion to confirm the order was successful.
     * Verifies if the success message matches the expected criteria.
     * @param expectedMessage The expected success message (e.g., "Thank you for your order!").
     */
    public void verifyOrderCompletion(String expectedMessage) {
        LOG.info("Verifying order completion message.");
        String actualMessage = getText(completeHeader);
        Assert.assertEquals("Order completion message mismatch!", expectedMessage, actualMessage);
    }
}