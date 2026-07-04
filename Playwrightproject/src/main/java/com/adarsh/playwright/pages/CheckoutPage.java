package com.adarsh.playwright.pages;

import com.microsoft.playwright.*;

public class CheckoutPage {

    private final Page page;
    private final Locator lblCheckoutInfo;
    private final Locator btnContinue;
    private final Locator InFirstName;
    private final Locator InLastName;
    private final Locator InPostalCode;
    private final Locator btnFinish;

    public CheckoutPage(Page page) {
        this.page = page;
        lblCheckoutInfo = page.locator(".checkout_info");
        btnContinue = page.locator("#continue");
        InFirstName = page.locator("#first-name");
        InLastName = page.locator("#last-name");
        InPostalCode = page.locator("#postal-code");
        btnFinish = page.locator("#finish");

    }

    // small reusable field-level
    public boolean isLoaded() {
        return lblCheckoutInfo.isVisible();
    }

    public void enterFirstName(String firstName) {
        InFirstName.fill(firstName);
    }

    public void enterLastName(String lastName) {
        InLastName.fill(lastName);
    }

    public void enterPostalCode(String postalCode) {
        InPostalCode.fill(postalCode);
    }
    public void clickContinue() {
        btnContinue.click();
    }

    //User should be able to navigate from the Cart page to the Checkout Information page.
    public CheckoutOverviewPage fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickOnContinue();
        return new CheckoutOverviewPage(page);
    }

    public CheckoutOverviewPage clickOnContinue() {
        btnContinue.click();
        return new CheckoutOverviewPage(page);
    }
    public void clickfinish() {
        btnFinish.click();
    }
}