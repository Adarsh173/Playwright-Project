package com.adarsh.playwright.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.adarsh.playwright.base.BaseTest;
import com.adarsh.playwright.pages.CartPage;
import com.adarsh.playwright.pages.InventoryPage;
import com.adarsh.playwright.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 0)
public void verifySuccessfulLogin() {

    LoginPage loginPage = new LoginPage(page);

    InventoryPage inventoryPage =
            loginPage.login(
                    "standard_user",
                    "secret_sauce");

    Assert.assertTrue(inventoryPage.isLoaded());
}

@Test(priority = 1)
public void addProductToCart() throws InterruptedException {

    LoginPage loginPage = new LoginPage(page);

    InventoryPage inventoryPage =
            loginPage.login(
                    "standard_user",
                    "secret_sauce");

    Assert.assertTrue(inventoryPage.isLoaded());

    inventoryPage.addToCart("Sauce Labs Backpack");
    Thread.sleep(1000);
    
    Assert.assertEquals(inventoryPage.getCartItemCount(), 1);
}

@Test(priority = 2)
public void verifyItemInCart() throws InterruptedException {

    LoginPage loginPage = new LoginPage(page);
    CartPage cartPage = new CartPage(page);

    InventoryPage inventoryPage =
            loginPage.login(
                    "standard_user",
                    "secret_sauce");

    Assert.assertTrue(inventoryPage.isLoaded());

    inventoryPage.addToCart("Sauce Labs Backpack");
    Thread.sleep(1000);
    
    Assert.assertEquals(inventoryPage.getCartItemCount(), 1);

    inventoryPage.navigateToCart();
    Thread.sleep(4000);            
    Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));
}

}
