package com.serli.selenium.po;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "remember")
    private WebElement rememberMe;

    @FindBy(id = "signin")
    private WebElement signin;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public static LoginPage openPlayApplication(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "/login");
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public AdminPage login(String username, String password, boolean rememberMe) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        if (rememberMe) {
            this.rememberMe.click();
        }
        signin.submit();
        
        return initElements(AdminPage.class);

    }
    
    public AdminPage loginAsAdmin(boolean rememberMe) {
        return login("admin", "admin", rememberMe);
    }

    public LoginPage loginWithError(String username, String password, boolean rememberMe) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        if (rememberMe) {
            this.rememberMe.click();
        }
        signin.submit();
        
        return initElements(LoginPage.class);
    }

    public boolean isErrorMessagePresent() {
        return isElementPresent(By.xpath("//p[@class='error']"));
    }
}
