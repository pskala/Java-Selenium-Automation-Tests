package com.favoritetest.testcases.basics;

import org.testng.annotations.Test;
import com.favoritetest.common.Webdriver;
import com.favoritetest.common.XMLReader;
import org.testng.Assert;

/**
 *
 * @author Petr Skala
 */
@Test(groups = "auth-signUp")
public class Login extends Webdriver {

    @Test(groups = "auth-signUp-loginExist")
    public void loginExist() {

        String user = XMLReader.getValue("users.user.name");
        String password = XMLReader.getValue("users.user.password");

        Assert.assertTrue(helpers.login(user, password), "User administration is not visible!");
        
        helpers.logout();

    }

    @Test(groups = "auth-signUp-loginNonExist")
    public void loginNonExist() {

        Assert.assertFalse(helpers.login("nonExistUser", "psw"), "User administration is not visible!");

    }
}
