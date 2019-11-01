package com.serli.selenium.po;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new RuntimeException("Driver is null");
        }
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected <G extends BasePage> G initElements(Class<G> clazz) {
        return PageFactory.initElements(driver, clazz);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public LoginPage logout() {
        getDriver().findElement(By.linkText("Logout")).click();
        return initElements(LoginPage.class);
    }
    
    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
