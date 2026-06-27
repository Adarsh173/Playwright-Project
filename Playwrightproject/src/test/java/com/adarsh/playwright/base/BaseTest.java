package com.adarsh.playwright.base;
import com.adarsh.playwright.config.ConfigReader;
import com.adarsh.playwright.factory.BrowserFactory;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {

        playwright = Playwright.create();

        playwright = Playwright.create();

        browser = BrowserFactory.launchBrowser(playwright);

        context = browser.newContext();

        page = context.newPage();

        context = browser.newContext();

        page = context.newPage();

        page.navigate(ConfigReader.getUrl());
    }

    @AfterMethod
    public void tearDown() {

        context.close();
        browser.close();
        playwright.close();
    }
}
