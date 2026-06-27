package com.adarsh.playwright.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.adarsh.playwright.base.BaseTest;
import com.adarsh.playwright.pages.InventoryPage;
import com.adarsh.playwright.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
public void verifySuccessfulLogin() {

    LoginPage loginPage = new LoginPage(page);

    InventoryPage inventoryPage =
            loginPage.login(
                    "standard_user",
                    "secret_sauce");

    Assert.assertTrue(inventoryPage.isLoaded());
}

@Test
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

}
