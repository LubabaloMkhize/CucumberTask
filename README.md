# Cucumber + TestNG + Allure Automation Project

This project demonstrates automated UI testing for [saucedemo.com](https://www.saucedemo.com/) using:
- **Cucumber** for BDD feature files
- **TestNG** as the test runner
- **Selenium WebDriver** for browser automation
- **Allure** for advanced reporting
- **WebDriverManager** for driver management

## Project Structure
- `src/test/resources/features/` - Cucumber feature files
- `src/test/java/stepdefinitions/` - Step definitions for Cucumber steps
- `src/test/java/pages/` - Page Object Model classes
- `src/test/java/runner/` - TestNG runner for Cucumber
- `pom.xml` - Maven dependencies and plugins
- `allure-results/` - Allure results after test execution

## How to Run
1. **Install dependencies:**
   ```sh
   mvn clean install
   ```
2. **Run tests:**
   ```sh
   mvn test
   ```
3. **Generate Allure report:**
   - Make sure you have [Allure commandline](https://docs.qameta.io/allure/#_installing_a_commandline) installed.
   - Run:
     ```sh
     allure serve allure-results
     ```
   - This will open the Allure report in your browser.

## Sample Feature
The project includes a scenario outline for login with multiple users:
```
Scenario Outline: Successful login with valid credentials
  Given I am on the saucedemo login page
  When I login with username "<username>" and password "<password>"
  Then I should see the products page

  Examples:
    | username        | password      |
    | standard_user   | secret_sauce  |
    | locked_out_user | secret_sauce  |
    | problem_user    | secret_sauce  |
```

## Notes
- Allure screenshots are attached for key steps and failures.
- Update the Java version in `pom.xml` if you encounter build issues.
- You can add more feature files and step definitions as needed.

---

Feel free to extend this project for your own UI automation needs!

