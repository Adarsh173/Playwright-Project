package com.adarsh.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {
     private final Page page;
     private final Locator lblCartItems;
     private final Locator btnCheckout;
     private final Locator btnContinueShopping;
     private final Locator lblCartItemCount;
     private final Locator btnRemoveItem;
     private final Locator btnAddItem;
     private final Locator lblCartItemName;
     private final Locator lblCartItemPrice;
     private final Locator lblCartItemQuantity;
     

    public CartPage(Page page) {
        this.page = page;

        lblCartItems = page.locator(".cart_item");
        btnCheckout = page.locator("#checkout");
        btnContinueShopping = page.locator("#continue-shopping");
        lblCartItemCount = page.locator(".shopping_cart_badge");
        btnRemoveItem = page.locator("button");
        btnAddItem = page.locator("button");
        lblCartItemName = page.locator(".inventory_item_name");
        lblCartItemPrice = page.locator(".inventory_item_price");
        lblCartItemQuantity = page.locator(".cart_quantity");
        


}



public void clickCheckout() {
    btnCheckout.click();
}
public void clickContinueShopping() {
    btnContinueShopping.click();
}
public void removeItemFromCart(String productName) {
    Locator product = page.locator(".cart_item")
            .filter(new Locator.FilterOptions().setHasText(productName));

    product.locator("button",
            new Locator.LocatorOptions().setHasText("Remove"))
            .click();
}

public boolean isProductPresent(String string) {
    Locator product = page.locator(".cart_item")
            .filter(new Locator.FilterOptions().setHasText(string));

    return product.isVisible();

}
public void navigateToCheckoutPage() {
    btnCheckout.click();    
}
}