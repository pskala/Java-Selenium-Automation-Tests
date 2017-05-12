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


## Configuration

File config.xml have to be in root folder in your project. Rename config_example.xml and set up your settings.

```
<?xml version="1.0" encoding="ISO-8859-1" ?>
<configuration>
    <properties>
        <browser>firefox</browser>
        <url>http://favoritetest.com/</url>
        <wait>20</wait>
        <geckoPath>c:\\Program Files\\geckodriver\\geckodriver.exe</geckoPath>
        <remoteWebDriver>false</remoteWebDriver>
        <size>desktop</size>
    </properties>
    <users>
        <user>
            <name>test_user</name>
            <password>psw</password>
        </user>
    </users>
</configuration>
```

Properties:

* browser - firefox, chrome, android, edge, htmlUnit, internetExplorer, ipad, iphone, operaBlink, phantomjs, safari - default value: firefox
* wait - The timeout in seconds for element waiting (WebDriverWait).
* geckoPath - path where is geckodriver present, use only if remoteWebDriver is set false
* remoteWebDriver - true or false, chaice false use FirefoxDriver (geckodriver)
* size - desktop, phone, iPad  - default value: desktop

## Run

TestNGSuite.xml

## Contributors

* [Petr Skala](http://github.com/pskala)

## License

Apache 2.0

Copyright (c) 2017 Petr Skala