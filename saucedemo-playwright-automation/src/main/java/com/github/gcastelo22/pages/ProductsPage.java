package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import com.microsoft.playwright.Page;
import org.junit.Assert;

/**
 * Page Object representing the Inventory/Products screen.
 * Implements Fluent Interface for seamless navigation through the catalog.
 */
public class ProductsPage extends BasePage {

    // Selectors using CSS strings
    private final String titleSpan = ".title";
    private final String shoppingCartBadge = ".shopping_cart_badge";
    private final String cartLink = ".shopping_cart_link";

    /**
     * Constructor for ProductsPage.
     * @param page The Playwright Page instance.
     */
    public ProductsPage(Page page) {
        super(page);
    }

    /**
     * Verifies if the page title matches the expected value.
     * @param expectedTitle The expected title string (e.g., "Products").
     * @return Current instance for chaining.
     */
    public ProductsPage verifyPageTitle(String expectedTitle) {
        Assert.assertEquals("Page title mismatch!", expectedTitle, getText(titleSpan));
        return this;
    }

    /**
     * Adds a specific product to the cart by its name.
     * Dynamically constructs the button ID.
     * @param productName The name of the product (e.g., "Sauce Labs Backpack").
     * @return Current instance for chaining.
     */
    public ProductsPage addProductToCart(String productName) {
        LOG.info("Adding product to cart: " + productName);
        // Convert product name to the ID format used by SauceDemo (e.g., add-to-cart-sauce-labs-backpack)
        String buttonId = "#add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        click(buttonId);
        return this;
    }

    /**
     * Verifies the number of items displayed on the shopping cart badge.
     * @param expectedCount The expected number as a string.
     * @return Current instance for chaining.
     */
    public ProductsPage verifyCartBadgeCount(String expectedCount) {
        Assert.assertEquals("Cart badge count mismatch!", expectedCount, getText(shoppingCartBadge));
        return this;
    }

    /**
     * Navigates to the Cart page.
     * Playwright's auto-wait handles the page transition without explicit URL checks.
     * @return A new instance of CartPage.
     */
    public CartPage goToCart() {
        LOG.info("Navigating to the shopping cart.");
        click(cartLink);
        return new CartPage(page);
    }
}