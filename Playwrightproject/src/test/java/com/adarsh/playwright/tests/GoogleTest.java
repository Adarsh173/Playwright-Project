package com.adarsh.playwright.tests;

import com.adarsh.playwright.base.BaseTest;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void verifyGoogleTitle() {

        page.navigate("https://www.google.com");
        System.out.println(page.title());
        

    }
}