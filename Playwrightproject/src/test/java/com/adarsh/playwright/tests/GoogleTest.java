package com.adarsh.playwright.tests;

import com.adarsh.playwright.base.BaseTest;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void verifyGoogleTitle() {

        page.navigate("https://www.google.com");
        page.waitForTimeout(5000); //not sure if this is needed, but it works without it as well and it is not thread.sleep :-P
        System.out.println(page.title());
        

    }
}