package com.favoritetest.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 *
 * @author Petr Skala
 */
public class Webdriver {

    protected WebDriver driver;
    protected Helpers helpers;
    protected WebDriverWait wait;
    
    protected final String BROWSER = XMLReader.getValue("properties.browser");
    protected final boolean REMOTE_WEB_DRIVER = Boolean.parseBoolean(XMLReader.getValue("properties.remoteWebDriver"));;
    protected final int WAIT = Integer.parseInt(XMLReader.getValue("properties.wait"));
    protected final String URL = XMLReader.getValue("properties.url");
    protected final String SIZE = XMLReader.getValue("properties.size");


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

        driver = setDriver(BROWSER, REMOTE_WEB_DRIVER);
        wait = new WebDriverWait(driver, WAIT);
        helpers = new Helpers(driver, wait);
        
        // set window size
        WindowSize.setWindowSize(driver, SIZE);
        
        driver.get(URL);

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

        // Close the driver
        driver.quit();

    }

    private static WebDriver setDriver(String device, boolean remoteWebDriver) {

        WebDriver driver;

        if (!remoteWebDriver) {

            System.setProperty("webdriver.gecko.driver", XMLReader.getValue("properties.geckoPath"));
            driver = new FirefoxDriver();

        } else {

            DesiredCapabilities capabilities;

            switch (device) {
                case "firefox":
                    capabilities = DesiredCapabilities.firefox();
                    break;
                case "chrome":
                    capabilities = DesiredCapabilities.firefox();
                    break;
                default:
                    capabilities = DesiredCapabilities.firefox();
                    break;
            }

            capabilities.setCapability("marionette", true);
            driver = new RemoteWebDriver(capabilities);

        }

        return driver;

    }
}
