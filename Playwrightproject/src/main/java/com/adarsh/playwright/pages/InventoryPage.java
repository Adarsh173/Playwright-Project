package com.adarsh.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage {

    private final Page page;
    private final Locator lblProducts;
    private final Locator lblCartIcon;
    private final Locator cartBadge;

    public InventoryPage(Page page) {

        this.page = page;

        lblProducts = page.locator(".title");
        lblCartIcon = page.locator(".shopping_cart_link");
        cartBadge = page.locator(".shopping_cart_badge");
    }

    public boolean isLoaded() {
        return lblProducts.isVisible();
    }

    public String getTitle() {
        return page.title();
    }

    public void addToCart(String productName) {
        clickProductButton(productName, "Add to cart");
    }

    public void removeFromCart(String productName) {
        clickProductButton(productName, "Remove");
    }

    private void clickProductButton(String productName, String buttonText) {

        Locator product = page.locator(".inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName));

        product.locator("button",
                new Locator.LocatorOptions().setHasText(buttonText))
                .click();
    }

    public int getCartItemCount() {
        Locator cartBadge = page.locator(".shopping_cart_badge");
        if (cartBadge.isVisible()) {
            return Integer.parseInt(cartBadge.innerText());
        } else {
            return 0;
        }
    }
//User should be able to open the cart.
    public void openCart() {
    lblCartIcon.click();
}

//User should be able to verify the cart badge count.
 public int getCartBadgeCount() {
        return Integer.parseInt(cartBadge.innerText());
    }  

    }  

