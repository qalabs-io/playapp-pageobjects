package com.qalabs.selenium.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {
    
    @FindBy(linkText="Add")
    private WebElement addLink;
    
    @FindBy(linkText="Users")
    private WebElement usersLink;

    public AdminPage(WebDriver driver) {
        super(driver);
    }
    
    public AddUserPage add() {
        addLink.click();
        return initElements(AddUserPage.class);
    }
    
    public UserListPage userList() {
        usersLink.click();
        return initElements(UserListPage.class);
    }
}
