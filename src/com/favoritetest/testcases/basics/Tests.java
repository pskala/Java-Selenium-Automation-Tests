package com.favoritetest.testcases.basics;

import org.testng.annotations.Test;
import com.favoritetest.common.Webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 *
 * @author Petr Skala
 */
@Test(groups = "basic-tests")
public class Tests extends Webdriver {

    String testName = "Family - La familia";

    @Test(groups = "basic-openTest")
    public void openExistTestFromList() {

        new Select(driver.findElement(By.cssSelector("#language_option select"))).selectByVisibleText("english");

        WebElement searchInput = driver.findElement(By.id("nameAutocompl"));
        
        searchInput.click();
        searchInput.sendKeys(testName);
        searchInput.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#listTable tr")));

        driver.findElement(By.cssSelector("#listTable tr")).click();
        
        Assert.assertEquals(driver.findElement(By.id("title")).getText().substring(0, testName.length()), testName, "Test opened right");

    }

    @Test(groups = "basic-openTest")
    public void openExistTestFromURL() {

        driver.get(URL + "#/Languages/Spanish/Vocabulary/Family-La-familia");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
        
        Assert.assertEquals(driver.findElement(By.id("title")).getText().substring(0, testName.length()), testName, "Test from URL opened right");

    }

    @Test(groups = "basic-checkTestButtons")
    public void checkTestButtons() {

        driver.get(URL + "#/Languages/Spanish/Vocabulary/Family-La-familia");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
        
        Assert.assertFalse(driver.findElement(By.id("answer")).isDisplayed());
        
        driver.findElement(By.id("show_answer_button")).click();

        Assert.assertTrue(driver.findElement(By.id("answer")).isDisplayed(), "Answer button show answer.");

        Assert.assertFalse(driver.findElement(By.id("show_answer_button")).isDisplayed(), "Answer button have is hidden.");

        String actualQuestion = driver.findElement(By.id("question_field")).getText();

        driver.findElement(By.id("next_button")).click();

        Assert.assertNotEquals(driver.findElement(By.id("question_field")).getText(), actualQuestion, "Next button show other question.");

        driver.findElement(By.id("previous_button")).click();

        Assert.assertEquals(driver.findElement(By.id("question_field")).getText(), actualQuestion, "Previous button show first question.");

    }
}
