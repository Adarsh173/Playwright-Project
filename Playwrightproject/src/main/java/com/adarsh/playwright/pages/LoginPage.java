package com.adarsh.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

    private final Locator txtUsername;
    private final Locator txtPassword;
    private final Locator btnLogin;
    private final Locator lblError;
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;

        txtUsername = page.locator("#user-name");
        txtPassword = page.locator("#password");
        btnLogin = page.locator("#login-button");
        lblError = page.locator("[data-test='error']");

    }

    public InventoryPage login(String username,
                           String password) {

    txtUsername.fill(username);
    txtPassword.fill(password);
    btnLogin.click();

    return new InventoryPage(page);
}

    public String getErrorMessage() {
        return lblError.innerText();
    }

    public boolean isLoginPageDisplayed() {
    return btnLogin.isVisible();
    }

    public void pageReload() {
        page.reload();
    }

    public String getLoginPageTitle() { 
        return page.title();
    }

    
}
