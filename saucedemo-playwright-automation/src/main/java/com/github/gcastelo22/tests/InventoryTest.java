package com.github.gcastelo22.tests;

import com.github.gcastelo22.core.BaseTest;
import com.github.gcastelo22.pages.LoginPage;
import org.junit.Test;

/**
 * Test suite focused on inventory and product interactions.
 * Ensures that products can be added to the cart and are correctly listed.
 */
public class InventoryTest extends BaseTest {

    @Test
    public void shouldAddBackpackToCartSuccessfully() {
        String product = "Sauce Labs Backpack";

        LOG.info("Starting Inventory test: Adding " + product + " to cart.");

        // The 'page' object is inherited from BaseTest
        new LoginPage(page)
                .loginAsStandardUser()
                .verifyPageTitle("Products")
                .addProductToCart(product)
                .verifyCartBadgeCount("1")
                .goToCart()
                .verifyPageTitle("Your Cart")
                .verifyProductInCart(product);

        LOG.info("Inventory test completed successfully.");
    }
}