package com.favoritetest.common;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Petr Skala
 */
public class WindowSize {

    public static final Dimension DESKTOP = new Dimension(1200, 1000);
    public static final Dimension PHONE = new Dimension(414, 736); // iphone 6 Pro
    public static final Dimension IPAD = new Dimension(1024, 1366); // iPad

    public static final void setWindowSize(WebDriver driver, String size) {

        Dimension dimension;

        switch (size.toUpperCase()) {
            case "PHONE":
                dimension = WindowSize.PHONE;
                break;
            case "IPAD":
                dimension = WindowSize.IPAD;
                break;
            default:
                dimension = WindowSize.DESKTOP;
                break;
        }

        try {

            driver.manage().window().setSize(dimension);

        } catch (Exception e) {
            
        }

    }

    private WindowSize() {
        throw new IllegalAccessError("Utility class");
    }

}
