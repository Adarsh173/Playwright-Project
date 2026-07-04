package com.adarsh.playwright.factory;

import com.adarsh.playwright.config.ConfigReader;
import com.microsoft.playwright.*;

public class BrowserFactory {

    public static Browser launchBrowser(Playwright playwright) {

        String browserName = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        switch (browserName.toLowerCase()) {

            case "chrome":
            options.setChannel("chrome");
            System.out.println("Launching Chrome browser...");
            return playwright.chromium().launch(options);

            case "edge":
            options.setChannel("msedge");
            System.out.println("Launching Edge browser...");
            return playwright.chromium().launch(options);   

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browserName);
        }
    }
}