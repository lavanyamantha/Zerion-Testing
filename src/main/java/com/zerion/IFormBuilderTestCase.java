package com.zerion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author LavanyaM
 */
public class IFormBuilderTestCase extends Base{

    /**
     * It will enter credentials and clicks on Login button and land in home page.
     * @param userName - accepts String
     * @param password - accepts String
     */
    protected void login(String userName, String password){

        Log.info("Navigate to to the login page\n" +
                "Enter the UserName - " + userName + "\n" +
                "Enter the Password - " + password + "\n" +
                "Click on the Login button and land on Home page.");

        driver.get(CONFIG.getProperty("loginUrl"));
        driver.findElement(By.xpath("//input[@name = 'USERNAME']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name = 'PASSWORD']")).sendKeys(password);
        driver.findElement(By.xpath(" //*[@id='login']/input")).click();
    }

    /**
     * Get UserName displayed on Page
     * @return String
     */
    protected String getUserName(){
        return driver.findElement(By.xpath("//*[@id='header_rt']/li[1]")).getText().substring(13);
    }

    protected void clickDataTab(){
        Log.info("Click on Data tab");
        driver.findElement(By.xpath("//*[@id = 'active']/a"))
                .click();
    }

    /**
     * This method will click on desired webelement
     * @param xPath expression
     */
    protected void clickByXpath(String xPath){
        Log.info("Click on desired webelement");
        driver.findElement(By.xpath(xPath)).click();
    }

    /**
     * Click on Id link in the My Data table and land in the Form Page
     * @param Id - accepts String
     */
    protected void clickIdLink(String Id) {
        Log.info("Click on ID -" + Id + " in the My Data table");
        WebElement table = driver.findElement(By.xpath("//div[@class = 'nDiv']/table"));
        // find the row
        WebElement datatable = table.findElement(By.xpath("//a[@href = 'dataList.php?PAGE_ID=" + Id + "']"));
        // click on the row
        datatable.click();
    }

    protected void clickNewRecordTab(){
        Log.info("Click on CreateNew Record Tab.");
        driver.findElement(By.xpath("//span[@class = 'new_record']")).click();
    }

    /**
     * Creata Data in Form by entering FirstName, LastName & ZipCode.
     * @param firstName - accepts String
     * @param lastName - accepts String
     * @param zipCode - accepts String
     */
    protected void creataDataInForm(String firstName, String lastName, String zipCode ) {
        Log.info("Enter firstname,lastname, zipcode.");
        driver.findElement(By.xpath("//input[@id = 'p3637524_rec0_first_name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id = 'p3637524_rec0_last_name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id = 'p3637524_rec0_zip_code']")).sendKeys(zipCode);
        Log.info("Click On Save Button.");
        driver.findElement(By.xpath("//a[@href = 'javascript:{}']")).click();
        // Syncro - wait for 20seconds for the form to save the data and display
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /**
     * Gets First row data from Form table
     * @return String
     */
    protected String getFirstRowDataFromFormTable(){
        WebElement baseTable = driver.findElement(By.tagName("table"));
        //To find 1st row of the table
        WebElement tableRow = baseTable.findElement(By.xpath("//*[@class='bDivBox']/table/tbody/tr[1]"));
        String rowtext = tableRow.getText();
        Log.debug("First row of table : " + rowtext);
        return rowtext;
    }

    /**
     * Create new User by entering UserName, Email, Password and Confirm Password and Save.
     * @param userName - accepts String
     * @param email - accepts String
     * @param password - accepts String
     */
    protected void createNewUser(String userName, String email, String password){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.xpath(ObjectRepository.getProperty("userName"))).sendKeys(userName);
        driver.findElement(By.xpath(ObjectRepository.getProperty("e-mail"))).sendKeys(email);
        driver.findElement(By.xpath(ObjectRepository.getProperty("initialPassword"))).sendKeys(password);
        driver.findElement(By.xpath(ObjectRepository.getProperty("confirmPassword"))).sendKeys(password);
        driver.findElement((By.xpath(ObjectRepository.getProperty("createUserButton")))).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /**
     * Check if user is present in User table
     * @param uName String
     * @return boolean
     */
    protected boolean isUserInUsersTable(String uName){
        return driver.findElement(By.linkText(uName)).isDisplayed();
    }

    protected int generateRandom() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

}
