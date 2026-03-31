package com.framework_Page;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.framework_Page.Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public static WebDriver driver;



    public void setUp() throws IOException {

        driver = DriverFactory.initializeDriver();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
    }


    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("TearDown was successful");
    }
}


