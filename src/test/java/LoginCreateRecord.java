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
 *         This test Login into iFormBuilder and asserts the user name
 *         Create a new record and asserts the record is available in list view and data is correct
 */
public class LoginCreateRecord extends IFormBuilderTestCase {

    @BeforeTest
    public void beforeTest() throws IOException {
        initialize();
    }

    @Test(timeOut = 5 * 60 * 1000)
    public void loginTest() throws InterruptedException {

        // Test Data
        String userName = CONFIG.getProperty("userName");
        String password = CONFIG.getProperty("password");
        String id = CONFIG.getProperty("id");
        String firstName = CONFIG.getProperty("firstName") + generateRandom();
        String lastName = CONFIG.getProperty("lastName") + generateRandom();
        String zipCode = CONFIG.getProperty("zipCode");

        // Login
        login(userName, password);

        Log.info("Assert the user name on home page is same as the username used to login - " + userName);
        Assert.assertEquals(userName, getUserName());

        Log.info("\nClick on Data tab -> Id link -> New Record button & Fill User data in Form\n");
        clickDataTab();
        clickIdLink(id);
        clickNewRecordTab();
        creataDataInForm(firstName, lastName, zipCode);

        Log.debug("Format/Split the data that we get from table to assert with expected data");
        String[] data = getFirstRowDataFromFormTable().split("\\r?\\n");
        String fNameInTable = data[1].replace(" ", ""); // ignore whitespaces
        String lNameInTable = data[2];
        String zipCodeInTable = data[3];

        Log.info("Assert the data is displayed in List view and the data is correct as expected");
        Assert.assertEquals(fNameInTable, firstName);
        Assert.assertEquals(lNameInTable, lastName);
        Assert.assertEquals(zipCodeInTable, zipCode);

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



