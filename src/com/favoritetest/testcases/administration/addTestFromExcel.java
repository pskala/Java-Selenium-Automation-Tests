package com.favoritetest.testcases.administration;

import com.favoritetest.common.Webdriver;
import com.favoritetest.common.XLSReader;
import com.favoritetest.common.XMLReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Petr Skala
 */
@Test(groups = "admin-addTest")
public class addTestFromExcel extends Webdriver {

    @Test(groups = "admin-addTest")
    public void addTest() {

        int questionCell;
        int answerCell;
        String user;
        String password;
        String excelUserLoginType = XMLReader.getValue("properties.excelUserLoginType");

        if (excelUserLoginType == null) {

            excelUserLoginType = "DEFAULT";

        }

        switch(excelUserLoginType.toUpperCase()) {
            case "FACEBOOK":
                    user = XMLReader.getValue("users.user.facebookName");
                    password = XMLReader.getValue("users.user.facebookPassword");
                    Assert.assertTrue(helpers.loginByFB(user, password), "User administration is not visible!");
                break;
            case "GOOGLE":
                    user = XMLReader.getValue("users.user.googleName");
                    password = XMLReader.getValue("users.user.googlePassword");
                    Assert.assertTrue(helpers.loginByGoogle(user, password), "User administration is not visible!");
                break;
            default:
                    user = XMLReader.getValue("users.user.name");
                    password = XMLReader.getValue("users.user.password");
                    Assert.assertTrue(helpers.login(user, password), "User administration is not visible!");
                break;
        }

        // read testcases for excel from config xml
        XMLReader.getListXMLtreeValues("excelTestCases.testcase");

        while(XMLReader.hasNext()) {

            XMLReader.next();

            int sheet;
            if (XMLReader.getXMLtreeValue("sheet") != null) {

                sheet = new Integer(XMLReader.getXMLtreeValue("sheet"));

            } else {

                sheet = 0;

            }

            // read excel file
            XLSReader xls = new XLSReader(sheet);

            questionCell = new Integer(XMLReader.getXMLtreeValue("questionCell")) - 1;
            answerCell = new Integer(XMLReader.getXMLtreeValue("answerCell")) - 1;

            // set language page
            new Select(driver.findElement(By.cssSelector("#language_option select"))).selectByVisibleText(XMLReader.getXMLtreeValue("languagePage"));

            driver.get(URL);
            driver.get(URL + "addtest#" + XMLReader.getXMLtreeValue("testPath"));

            // click on add test 
            driver.findElement(By.id("addtest")).click();

            // set new test values
            driver.findElement(By.id("testname")).sendKeys(XMLReader.getXMLtreeValue("testName"));
            driver.findElement(By.id("testdescription")).sendKeys(XMLReader.getXMLtreeValue("description"));
            new Select(driver.findElement(By.id("testrights"))).selectByVisibleText(XMLReader.getXMLtreeValue("visibility"));
            new Select(driver.findElement(By.id("langselect"))).selectByVisibleText(XMLReader.getXMLtreeValue("languageTest"));
            new Select(driver.findElement(By.id("questions_lang"))).selectByVisibleText(XMLReader.getXMLtreeValue("questionLanguage"));
            new Select(driver.findElement(By.id("answers_lang"))).selectByVisibleText(XMLReader.getXMLtreeValue("answerLanguage"));

            // create test
            driver.findElement(By.id("save_send_test_button")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#questionsTab a")));

            driver.findElement(By.cssSelector("#questionsTab a")).click();

            while(xls.hasNextRow()) {

                xls.nextRow();

                driver.findElement(By.id("edit_add_question")).click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addQuestion")));

                driver.findElement(By.id("text_question")).sendKeys(xls.getCell(questionCell));
                driver.findElement(By.id("text_answer1")).sendKeys(xls.getCell(answerCell));

                driver.findElement(By.id("send_button")).click();

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addQuestion")));

            }

            xls.close();

        }

        helpers.logout();

    }

}