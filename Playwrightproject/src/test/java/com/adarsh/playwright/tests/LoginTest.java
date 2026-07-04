package com.adarsh.playwright.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.adarsh.playwright.base.BaseTest;
import com.adarsh.playwright.pages.CartPage;
import com.adarsh.playwright.pages.CheckoutOverviewPage;
import com.adarsh.playwright.pages.CheckoutPage;
import com.adarsh.playwright.pages.InventoryPage;
import com.adarsh.playwright.pages.LoginPage;
import com.microsoft.playwright.Page.AddLocatorHandlerOptions;

public class LoginTest extends BaseTest {

    private InventoryPage loginAsStandardUser() {

        LoginPage loginPage = new LoginPage(page);

        InventoryPage inventoryPage = loginPage.login(
                "standard_user",
                "secret_sauce");

        Assert.assertTrue(inventoryPage.isLoaded());

        return inventoryPage;
    }

    @Test(priority = 0, description = "Verify that the user is able to login successfully with valid credentials.")
    public void verifySuccessfulLogin() {

        InventoryPage inventoryPage = loginAsStandardUser();

        Assert.assertTrue(inventoryPage.isLoaded());
    }

    @Test(priority = 1, description = "Verify that the user is able to add a product to the cart and the cart badge count is updated accordingly.")
    public void addProductToCart() {

        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addToCart("Sauce Labs Backpack");

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1, "Cart badge should show one item.");
    }

    @Test(priority = 2, description = "Verify that the selected product exists inside the cart.")
    public void verifyItemInCart() {
        CartPage cartPage = new CartPage(page);
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addToCart("Sauce Labs Backpack");

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1);

        inventoryPage.openCart();
        Assert.assertTrue(cartPage.isProductPresent("Sauce Labs Backpack"));

    }

    @Test(priority = 3, description = "Verify that the user is able to sign in, add product to the cart, remove it from the cart, and assert cart badge count then naviate to the cart page and assert that the product is not present in the cart.")
    public void verifyRemainingProductAfterRemovingOneItem() {
        CartPage cartPage = new CartPage(page);
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bike Light");

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 2);

        inventoryPage.removeFromCart("Sauce Labs Bike Light");

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1);

        inventoryPage.openCart();
        Assert.assertFalse(cartPage.isProductPresent("Sauce Labs Bike Light"));
        Assert.assertTrue(cartPage.isProductPresent("Sauce Labs Backpack"));
    }

    @Test(priority = 4, description = "Verify that the user is able to navigate from the Cart page to the Checkout Information page.")
    public void verifyNavigationToCheckoutPage() {


        CartPage cartPage = new CartPage(page);
        CheckoutPage checkoutPage = new CheckoutPage(page);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(page);
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addToCart("Sauce Labs Backpack"); 
        inventoryPage.addToCart("Sauce Labs Bike Light");
        inventoryPage.openCart();
        cartPage.navigateToCheckoutPage();

        Assert.assertTrue(page.url().contains("checkout-step-one.html"));
        checkoutPage.fillCheckoutInformation("Adarsh", "Pandey", "227816");
        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
        checkoutOverviewPage.clickFinish();
        Assert.assertTrue(page.url().contains("checkout-complete.html"));
        
    }
}
