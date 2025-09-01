package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By passwordField = By.xpath("//input[@placeholder='Password' and @type='password']");
    private By loginButton = By.xpath("//button[normalize-space(text())='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String password) {

        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}

