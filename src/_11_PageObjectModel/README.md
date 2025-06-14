# Page Object Model (POM)

Page Object Model (POM) is a design pattern used in software testing that promotes creating separate classes for each web page or sections of a page.
This improves the structure, readability, maintainability, and reusability of test code.

What POM provides:

1. Page Object Classes:
   - Each web page or page section is represented by a class containing web elements and related operations.
   - Example:
     public class LoginPage {
         @FindBy(id = "username") WebElement usernameInput;
         @FindBy(id = "password") WebElement passwordInput;

         public void setUsername(String username) {
             usernameInput.sendKeys(username);
         }
     }

2. Using Page Objects in Tests:
   - Test classes interact with page objects instead of directly with WebDriver.
   - Example:
     LoginPage loginPage = new LoginPage(driver);
     loginPage.setUsername("standard_user");

3. Maintainability and Reusability:
   - Changes in the page structure only require updates in the page object class.
   - The same page objects can be reused across multiple test cases.

4. Cleaner and More Understandable Tests:
   - Page logic is separated from test logic.
   - Test code focuses only on test scenarios and validations.

Conclusion:
Page Object Model helps reduce code duplication and increases modularity.
It supports scalable, cleaner, and more maintainable test automation frameworks especially in large-scale projects.


## Login and Error Message Validation using POM

1. Navigate to https://www.saucedemo.com/.

2. Perform login using the following valid usernames:
   - standard_user
   - locked_out_user
   - problem_user
   - performance_glitch_user
   - error_user
   - visual_user

   Password for all: "secret_sauce"

3. Validate error message:
   - If login is attempted with invalid username or password, verify that the error message appears.

4. Validate locked-out user:
   - If the credentials are correct but the user is locked (e.g., locked_out_user), validate that a proper warning appears.

5. Use a single test method:
   - Implement all login and error validation scenarios using one test method with a data provider.
