package com.adarsh.playwright.pages;

import com.microsoft.playwright.*;

public class CheckoutOverviewPage {

    private final Page page;
    private final Locator pagetitle;
    private final Locator btnFinish;
    private final Locator btnCancel;

    public CheckoutOverviewPage(Page page) {
        this.page = page;
        pagetitle = page.locator(".title");
        btnCancel = page.locator("#cancel");
        btnFinish = page.locator("#finish");
    }

    public String getPageTitle() {
        return pagetitle.innerText();
    }

    public boolean isLoaded() {
        return pagetitle.isVisible();
    }

    public void clickFinish() {
        btnFinish.click();
    }

    public void clickCancel() {
        btnCancel.click();
    }
}
