package com.adarsh.playwright.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.adarsh.playwright.base.BaseTest;
import com.adarsh.playwright.pages.CartPage;
import com.adarsh.playwright.pages.InventoryPage;
import com.adarsh.playwright.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 0,description = "Verify that the user is able to login successfully with valid credentials.") 
public void verifySuccessfulLogin() {

    LoginPage loginPage = new LoginPage(page);

    InventoryPage inventoryPage =
            loginPage.login(
                    "standard_user",
                    "secret_sauce");

    Assert.assertTrue(inventoryPage.isLoaded());
}

@Test(priority = 1,description = "Verify that the user is able to add a product to the cart and the cart badge count is updated accordingly.")
public void addProductToCart() throws InterruptedException {

    LoginPage loginPage = new LoginPage(page);

    InventoryPage inventoryPage =
            loginPage.login(
                    "standard_user",
                    "secret_sauce");

    Assert.assertTrue(inventoryPage.isLoaded());

    inventoryPage.addToCart("Sauce Labs Backpack");
    
    Assert.assertEquals(inventoryPage.getCartItemCount(), 1);
}

@Test(priority = 2,description = "Verify that the selected product exists inside the cart.")
public void verifyItemInCart() throws InterruptedException {

    LoginPage loginPage = new LoginPage(page);
    CartPage cartPage = new CartPage(page);

    InventoryPage inventoryPage =
            loginPage.login(
                    "standard_user",
                    "secret_sauce");

    Assert.assertTrue(inventoryPage.isLoaded());

    inventoryPage.addToCart("Sauce Labs Backpack");
    
    
    Assert.assertEquals(inventoryPage.getCartItemCount(), 1);

    inventoryPage.openCart();           
    Assert.assertTrue(cartPage.isProductPresent("Sauce Labs Backpack"));

}
@Test(priority = 3,description = "Verify that the user is able to sign in, add product to the cart, remove it from the cart, and assert cart badge count then naviate to the cart page and assert that the product is not present in the cart.")
public void verifyItemInCartAfterRemoving() throws InterruptedException {

    LoginPage loginPage = new LoginPage(page);
    CartPage cartPage = new CartPage(page);

    InventoryPage inventoryPage =
            loginPage.login(
                    "standard_user",
                    "secret_sauce");

    Assert.assertTrue(inventoryPage.isLoaded());

    inventoryPage.addToCart("Sauce Labs Backpack");
        inventoryPage.addToCart("Sauce Labs Bike Light");

    
    Assert.assertEquals(inventoryPage.getCartItemCount(), 2);

    inventoryPage.removeFromCart("Sauce Labs Bike Light");
    
    Assert.assertEquals(inventoryPage.getCartItemCount(), 1);

    inventoryPage.openCart();           
    Assert.assertFalse(cartPage.isProductPresent("Sauce Labs Bike Light"));
    Assert.assertTrue(cartPage.isProductPresent("Sauce Labs Backpack"));
}

}
