package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import com.microsoft.playwright.Page;
import org.junit.Assert;

/**
 * Page Object representing the Shopping Cart screen.
 */
public class CartPage extends BasePage {

    // Selectors
    private final String titleSpan = ".title";
    private final String checkoutButton = "#checkout";

    /**
     * Constructor for CartPage.
     * @param page The Playwright Page instance.
     */
    public CartPage(Page page) {
        super(page);
    }

    /**
     * Verifies if the cart page title matches the expected value.
     * @param expectedTitle The expected title string (e.g., "Your Cart").
     * @return Current instance for chaining.
     */
    public CartPage verifyPageTitle(String expectedTitle) {
        Assert.assertEquals("Cart page title mismatch!", expectedTitle, getText(titleSpan));
        return this;
    }

    /**
     * Verifies if a specific product is visible in the cart.
     * Uses Playwright's text selector to find the element.
     * @param productName The name of the product to verify.
     * @return Current instance for chaining.
     */
    public CartPage verifyProductInCart(String productName) {
        LOG.info("Verifying product in cart: " + productName);
        // Playwright dynamic selector: finds the inventory name containing specific text
        boolean isVisible = page.isVisible(".inventory_item_name >> text='" + productName + "'");
        Assert.assertTrue("Product " + productName + " was not found in the cart!", isVisible);
        return this;
    }

    /**
     * Clicks the checkout button and transitions to the Information step.
     * @return A new instance of CheckoutInfoPage.
     */
    public CheckoutInfoPage clickCheckout() {
        LOG.info("Proceeding to checkout information...");
        click(checkoutButton);
        return new CheckoutInfoPage(page);
    }
}