import com.zerion.IFormBuilderTestCase;
import com.zerion.Log;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.Utility;

import java.io.IOException;

/**
 * @author LavanyaM
 *         This test Login into iFormBuilder and creates a new user and asserts the user in Add/Edit User page and log out.
 *         Again Login as newly created user and assert the user name.
 */
public class LoginCreateNewUser extends IFormBuilderTestCase {

    @BeforeTest
    public void beforeTest() throws IOException {
        initialize();
    }

    @Test(timeOut = 5 * 60 * 1000)
    public void loginAndValidateUserTest() throws IOException {

        // Test Data
        String userName = CONFIG.getProperty("userName");
        String password = CONFIG.getProperty("password");
        String newUserName = CONFIG.getProperty("newUser") + generateRandom();
        String newUserEmail = newUserName + "@test.com";
        String newUserPassword = CONFIG.getProperty("newUserPassword");

        // Login
        login(userName, password);

        Log.info("Assert the user name on home page is same as the username used to login - " + userName);
        Assert.assertEquals(userName, getUserName());

        Log.info("Click on User Tab -> Add/Edit Tab -> New button");
        clickByXpath(ObjectRepository.getProperty("userTab"));
        clickByXpath(ObjectRepository.getProperty("Add/EditTab"));
        clickByXpath(ObjectRepository.getProperty("NewButton"));

        //Create New User
        createNewUser(newUserName, newUserEmail, newUserPassword);

        Log.info("Assert if the new created user is present in User list table");
        Assert.assertTrue(isUserInUsersTable(newUserName));

        // Logout
        clickByXpath(ObjectRepository.getProperty("logoutUser"));

        //Login with new User
        login(newUserName, newUserPassword);

        Log.info("Assert the user name on home page is same as the username used to login - " + newUserName);
        Assert.assertEquals(newUserName, getUserName());

        // Logout
        clickByXpath(ObjectRepository.getProperty("logoutUser"));
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            Utility.captureScreenshot(driver, this.getClass().getSimpleName() + Utility.getCurrentDateTime());
        }
        driver.quit();
    }
}
