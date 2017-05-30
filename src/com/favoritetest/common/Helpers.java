package com.favoritetest.common;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Petr Skala
 */
public class Helpers {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Helpers(WebDriver driver, WebDriverWait wait) {

        this.driver = driver;
        this.wait = wait;

    }

    /**
     *
     * @param name
     * @param password
     * @return - user account is accepted
     */
    public boolean login(String name, String password) {

        driver.findElement(By.id("login")).sendKeys(name);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.cssSelector("div.section-title")).click();
        driver.findElement(By.id("login_button")).click();

        if (isDialogPresent()) {

            driver.switchTo().alert().accept();
            return false;

        }

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameChange")));

        } catch (TimeoutException e) {

            return false;

        }

        return true;

    }

    /**
     *
     * @param name
     * @param password
     * @return - user account is accepted
     */
    public boolean loginByFB(String name, String password) {

        String winHandleBefore = driver.getWindowHandle();

        driver.findElement(By.cssSelector("div.fbbutton")).click();

        driver.getWindowHandles().stream().forEach((winHandle) -> {

            driver.switchTo().window(winHandle);

        });

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

            driver.findElement(By.id("email")).sendKeys(name);
            driver.findElement(By.id("pass")).sendKeys(password);
            driver.findElement(By.id("loginbutton")).click();

        } catch (Exception e) {

        } finally {

            driver.switchTo().window(winHandleBefore);

        }
        
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameChange")));

        } catch (TimeoutException e) {

            return false;

        }

        return true;

    }

    /**
     *
     * @param name
     * @param password
     * @return - user account is accepted
     */
    public boolean loginByGoogle(String name, String password) {

        String winHandleBefore = driver.getWindowHandle();

        driver.findElement(By.cssSelector("div.gpbutton")).click();

        driver.getWindowHandles().stream().forEach((winHandle) -> {

            driver.switchTo().window(winHandle);

        });

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));

            driver.findElement(By.cssSelector("input[type='email']")).sendKeys(name);
            
            driver.findElement(By.id("identifierNext")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));

            driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);

            driver.findElement(By.id("passwordNext")).click();

        } catch (Exception e) {

        } finally {

            driver.switchTo().window(winHandleBefore);

        }
        
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameChange")));

        } catch (TimeoutException e) {

            return false;

        }

        return true;

    }

    public void logout() {

        driver.findElement(By.cssSelector("#username a")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("usernameChange")));

    }

    boolean isDialogPresent() {

        try {

            return wait.until(ExpectedConditions.alertIsPresent()) != null;

        } catch (TimeoutException e) {

            return false;

        }

    }

}
