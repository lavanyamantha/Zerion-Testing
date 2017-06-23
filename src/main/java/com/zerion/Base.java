package com.zerion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Base class - This includes all common declarations, initializations and methods for other tests to use.
 * All tests extends this class.
 */
public class Base {

    //Declaration of Variables
    protected static WebDriver driver = null;

    protected static Properties CONFIG = null;
    protected static Properties ObjectRepository = null;

    //Initialize the Properties file
    public static void initialize() throws IOException {
        if (driver == null) {

            //config file
            CONFIG = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//properties//config.properties");
            CONFIG.load(fis);

            //Object Repository file
            ObjectRepository = new Properties();
            fis = new FileInputStream(System.getProperty("user.dir") + "//src//properties//OR.properties");
            ObjectRepository.load(fis);

            //Initialize the WebDriver and EventFiringWebDriver
            if (CONFIG.getProperty("browser").equals("Firefox")) {
                driver = new FirefoxDriver();
            } else if (CONFIG.getProperty("browser").equals("Chrome")) {
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "//src//test//java//utility//chromedriver.exe");
                driver = new ChromeDriver();

            } else if (CONFIG.getProperty("browser").equals("IE")) {
                driver = new InternetExplorerDriver();
            }

            EventFiringWebDriver eventfiring = new EventFiringWebDriver(driver);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }
}
