package _11_PageObjectModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.BaseDriver;

public class LoginTestElements {

    // Constructor: Initializes the web elements for the current page
    public LoginTestElements() {
        PageFactory.initElements(BaseDriver.driver, this);
    }

    // Username input field
    @FindBy(id = "user-name")
    public WebElement usernameInput;

    // Password input field
    @FindBy(id = "password")
    public WebElement passwordInput;

    // Login button
    @FindBy(id = "login-button")
    public WebElement loginButton;

    // Burger menu button
    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenu;

    // Logout button
    @FindBy(id = "logout_sidebar_link")
    public WebElement logout;

    // Error message block
    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorMessage;

    // Error close button (X icon)
    @FindBy(xpath = "//h3[@data-test='error']/button")
    public WebElement errorCloseButton;
}

/**
     The LoginTestElements class is the structure that replaces all elements on the page.
     As part of the POM approach, this class gathers all locators together and improves the readability of the tests.
 */

