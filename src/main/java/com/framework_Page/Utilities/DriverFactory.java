package com.framework_Page.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver initializeDriver() {

            WebDriverManager.chromedriver().setup();

            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            return driver;
        }
    }

