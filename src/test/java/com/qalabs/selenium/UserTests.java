package com.qalabs.selenium;

import static org.junit.Assert.assertTrue;

import com.qalabs.selenium.po.LoginPage;
import org.junit.Test;

import com.qalabs.selenium.po.AdminPage;

public class UserTests extends BaseSeleniumTest {
    
    @Test
    public void testCreateDeleteUser() throws Exception {
        String correctEmail = "formation.selenium@serli.com";
        
        //1. Login
        LoginPage loginPage = LoginPage.openPlayApplication(getDriver(), getBaseUrl());
        AdminPage adminPage = loginPage.login("admin", "admin", false);

        //2. Add user
        AddUserPage addUserPage = adminPage.add();
        addUserPage.setFirstname("formation");
        addUserPage.setLastname("selenium");
        addUserPage.setPassword("azertyuiop");
        addUserPage.setAddress("Poitiers");
        addUserPage.setEmail(correctEmail);
        UserListPage userListPage = addUserPage.save();

        //3. Delete user
        EditUserPage editUserPage = userListPage.editUser(correctEmail);
        userListPage = editUserPage.delete();

        //4. Logout
        userListPage.logout();
    }
    
    @Test
    public void testWrongPassword() throws Exception {
        LoginPage loginPage = LoginPage.openPlayApplication(getDriver(), getBaseUrl());
        loginPage = loginPage.loginWithError("admin", "admin2", false);
        assertTrue(loginPage.isErrorMessagePresent());
    }
    
    @Test
    public void testWrongLogin() throws Exception {
        LoginPage loginPage = LoginPage.openPlayApplication(getDriver(), getBaseUrl());
        loginPage = loginPage.loginWithError("admin2", "admin", false);
        assertTrue(loginPage.isErrorMessagePresent());
    }

}
