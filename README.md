# Java-Selenium-Automation-Tests

Selenium automation tests for favoritetest.com project written in java language.

## Development Environment

Libraries:
* Selenium - http://www.seleniumhq.org/download/
* TestNG - http://testng.org/doc/download.html
* Commons BeanUtils - http://commons.apache.org/proper/commons-beanutils/

All jar libraries are downloadable: http://petrskala.com/Documents/lib.zip

Webdriver for Firefox (if you don't want using remoteWebDriver):
https://github.com/mozilla/geckodriver/releases

or other Webdrivers are inside selenium-standalone (see https://www.npmjs.com/package/selenium-standalone)


## Configuration

File config.xml have to be in root folder in your project. Rename config_example.xml and set up your settings.

```
<?xml version="1.0" encoding="ISO-8859-1" ?>
<configuration>
    <properties>
        <browser>firefox</browser>
        <url>http://favoritetest.com/</url>
        <wait>20</wait>
        <driverPath>c:\\Program Files\\geckodriver\\geckodriver.exe</driverPath>
        <remoteWebDriver>false</remoteWebDriver>
        <size>DESKTOP</size>
        <seleniumUrl>http://127.0.0.1:4444/wd/hub or https://USERNAME:ACCESS_KEY@ondemand.saucelabs.com:443/wd/hub ,....</seleniumUrl>
        <platform>Windows XP</platform>
        <version>43.0</version>
        <excelFile>excel.xlsx</excelFile>
        <excelUserLoginType>FACEBOOK/GOOGLE/FAVORITETEST</excelUserLoginType>
    </properties>
    <users>
        <user>
            <name>test_user</name>
            <password>psw</password>
            <facebookName>facebook account email</facebookName>
            <facebookPassword>psw</facebookPassword>
            <googleName>google account email</googleName>
            <googlePassword>psw</googlePassword>
        </user>
    </users>
    <excelTestCases>
        <testcase>
            <languagePage>english</languagePage>
            <testPath>/Languages/Others</testPath>
            <testName>Name of the test</testName>
            <description></description>
            <visibility>for all</visibility>
            <languageTest>Yes</languageTest>
            <questionLanguage>English</questionLanguage>
            <answerLanguage>German</answerLanguage>
            <questionCell>1</questionCell>
            <answerCell>2</answerCell>
            <sheet>0</sheet>
        </testcase>
    </excelTestCases>
</configuration>
```

Properties:

* browser - firefox, chrome, android, edge, htmlUnit, internetExplorer, ipad, iphone, operaBlink, phantomjs, safari - default value: firefox
* wait - The timeout in seconds for element waiting (WebDriverWait).
* driverPath - path where is browser driver present, use only if remoteWebDriver is set false, drivers can be only Firefox (gecko), Chrome or Edge
* remoteWebDriver - true or false
* size - desktop, phone, iPad  - default value: desktop
* seleniumUrl - only if remoteWebDriver is true, empty property or localhost url - selenium standalone should be installed and running (https://www.npmjs.com/package/selenium-standalone)
* platform - OS system - more information is on saucelabs.com
* version - browser version - more information is on saucelabs.com
* excelFile - path and name of the excel file for addTestFromExcel testcase
* excelUserLoginType - login type for access to admin and create new test - addTestFromExcel testcase, choices: FACEBOOK, GOOGLE or FAVORITETEST, default is FAVORITETEST

excelTestCases:

* languagePage - select language in favoritetest.com, choices: česky or english
* testPath - hash in url for test save
* testName - Uniq name of the test
* description - property of the test
* visibility - property of the test
* languageTest - property of the test
* questionLanguage - property of the test
* answerLanguage - property of the test
* questionCell - cell in excel for question field
* answerCell - cell in excel for answer field
* sheet - number of the sheet in excel

## Run

TestNGSuite.xml

## Contributors

* [Petr Skala](http://petrskala.com/)

## License

Apache 2.0

Copyright (c) 2017 Petr Skala