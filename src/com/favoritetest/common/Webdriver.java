package com.favoritetest.common;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Petr Skala
 */
public class Webdriver {

    protected WebDriver driver;
    protected Helpers helpers;
    protected WebDriverWait wait;

    protected final static String BROWSER = XMLReader.getValue("properties.browser");
    protected final static boolean REMOTE_WEB_DRIVER = Boolean.parseBoolean(XMLReader.getValue("properties.remoteWebDriver"));
    protected final static String DRIVERPATH = XMLReader.getValue("properties.driverPath");
    protected final static int WAIT = Integer.parseInt(XMLReader.getValue("properties.wait"));
    protected final static String URL = XMLReader.getValue("properties.url");
    protected final static String SIZE = XMLReader.getValue("properties.size");
    protected final static String SELENIUM_URL = XMLReader.getValue("properties.seleniumUrl");
    protected final static String PLATFORM = XMLReader.getValue("properties.platform");
    protected final static String VERSION = XMLReader.getValue("properties.version");

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

            switch (device.toUpperCase()) {

                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", DRIVERPATH);
                    driver = new ChromeDriver();
                    break;

                case "EDGE":
                    System.setProperty("webdriver.edge.driver", DRIVERPATH);
                    driver = new EdgeDriver();
                    break;

                default:
                    System.setProperty("webdriver.gecko.driver", DRIVERPATH);
                    driver = new FirefoxDriver();
                    break;
            }

        } else {

            DesiredCapabilities capabilities;

            switch (device.toUpperCase()) {

                case "FIREFOX":
                    capabilities = DesiredCapabilities.firefox();
                    break;

                case "CHROME":
                    capabilities = DesiredCapabilities.chrome();
                    break;

                case "ANDROID":
                    capabilities = DesiredCapabilities.android();
                    break;

                case "EDGE":
                    capabilities = DesiredCapabilities.edge();
                    break;

                case "HTMLUNIT":
                    capabilities = DesiredCapabilities.htmlUnit();
                    break;

                case "INTERNETEXPLORER":
                    capabilities = DesiredCapabilities.internetExplorer();
                    break;

                case "IPAD":
                    capabilities = DesiredCapabilities.ipad();
                    break;

                case "IPHONE":
                    capabilities = DesiredCapabilities.iphone();
                    break;

                case "OPERABLINK":
                    capabilities = DesiredCapabilities.operaBlink();
                    break;

                case "PHANTOMJS":
                    capabilities = DesiredCapabilities.phantomjs();
                    break;

                case "SAFARI":
                    capabilities = DesiredCapabilities.safari();
                    break;

                default:
                    capabilities = DesiredCapabilities.firefox();
                    break;
            }

            capabilities.setCapability("marionette", true);

            if (PLATFORM != null && !PLATFORM.isEmpty()) {
                capabilities.setCapability("platform", PLATFORM);
            }
            if (VERSION != null && !VERSION.isEmpty()) {
                capabilities.setCapability("version", VERSION);
            }

            if (SELENIUM_URL == null || SELENIUM_URL.isEmpty()) {

                driver = new RemoteWebDriver(capabilities);

            } else {

                try {

                    driver = new RemoteWebDriver(new URL(SELENIUM_URL), capabilities);

                } catch (Exception e) {

                    System.out.println(e);
                    return null;

                }

            }

        }

        return driver;

    }
}
