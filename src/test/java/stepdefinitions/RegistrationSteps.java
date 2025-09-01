package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.Map;

public class RegistrationSteps {
    private WebDriver driver;
    private LoginPage loginPage;


    @Given("I am on the Ndosi Automation homepage")
    public void i_am_on_the_ndosiautomation_home_page() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://gray-island-0bd788c1e.2.azurestaticapps.net/#overview");
        driver.manage().window().maximize();
        Allure.addAttachment("Home Page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @When("I click on the \"Learning Materials\" tab")
    public void i_click_on_the_learning_materials_tab() {
        driver.findElement(By.xpath("//button[contains(@class, 'nav-btn') and normalize-space(text())='Learning Materials']")).click();
        Allure.addAttachment("Learning Materials Page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @And("I click on the \"Sign Up\" button")
    public void i_click_on_the_signup_button() {
        driver.findElement(By.xpath("//button[normalize-space(text())='Sign Up Here']")).click();
    }


    @And("I fill in the registration form with valid details")
    public void i_fill_in_the_registration_form_with_valid_details(DataTable dataTable) {
        Map<String, String> formData = dataTable.asMap(String.class, String.class);

        driver.findElement(By.xpath("//input[@placeholder='First Name' and @type='text']")).sendKeys(formData.get("FirstName"));
        driver.findElement(By.xpath("//input[@placeholder='Last Name' and @type='text']")).sendKeys(formData.get("LastName"));
        driver.findElement(By.xpath("//input[@placeholder='Email' and @type='email']")).sendKeys(formData.get("Email"));
        driver.findElement(By.xpath("//input[@placeholder='Password' and @type='password']")).sendKeys(formData.get("Password"));
        driver.findElement(By.xpath("//input[@placeholder='Confirm Password' and @type='password']")).sendKeys(formData.get("ConfirmPassword"));

        Allure.addAttachment("Filled Registration Form", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }


    @And("I submit the registration form")
    public void i_submit_the_registration_form() {
        driver.findElement(By.xpath("//button[normalize-space(text())='Create Account']")).click();
    }

    @Then("I should see a confirmation message {string}")
    public void i_should_see_a_confirmation_message(String expectedMessage) {
        try {
            // Wait for the alert to be present
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // Get alert text
            String alertText = alert.getText();

            // Assert the alert message
            Assert.assertEquals(alertText, expectedMessage);

            // Accept the alert before doing anything else
            alert.accept();

            // Now it's safe to take a screenshot
            Allure.addAttachment("Confirmation Message Screenshot",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        } catch (Exception e) {
            // Only take screenshot if there's no unhandled alert
            try {
                Allure.addAttachment("Failure Screenshot",
                        new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            } catch (UnhandledAlertException ignored) {
                // Alert already handled or still pending — no screenshot can be taken
            }
            throw e;
        }
    }



    @When("I enter my password to log in")
    public void i_enter_my_password_to_log_in() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@placeholder='Password' and @type='password']")).sendKeys("Pass1234");

    }

    @And("I submit the login form")
    public void i_submit_the_login_form() {
        driver.findElement(By.xpath("//button[normalize-space(text())='Login']")).click();

    }

    @Then("I should be logged in and see the logout button")
    public void i_should_be_logged_in_and_see_the_logout_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //boolean isLogoutButtonPresent =
        driver.findElement(By.xpath("//button[normalize-space(text())='Logout']")).isDisplayed();
        //Assert.assertTrue(isLogoutButtonPresent, "Logout button is not displayed, login might have failed.");
        Allure.addAttachment("Logout Button Visible", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }
}
