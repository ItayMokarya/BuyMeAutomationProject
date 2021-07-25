package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.Utils;

import java.io.IOException;
import java.util.Date;

public class TestRegistration {
    public static WebDriver driver;
    public static String baseUrl;
    static String browser;
    static MainPage mainPage;
    static LoginPage loginPage;
    static RegistrationPage registrationPage;
    static String name;
    static String email;
    static String password;
    static String validationPassword;
    static String fileName;


    private static final Logger logger = LogManager.getLogger(TestRegistration.class);


    @BeforeAll
    public static void setup() throws IOException {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

        baseUrl = Utils.getProperty("baseurl");
        browser = Utils.getProperty("browser");
        name = Utils.getProperty("RegistrationName");
        email = Utils.getProperty("RegistrationEmail");
        password = Utils.getProperty("RegistrationPassword");
        validationPassword = Utils.getProperty("RegistrationValidation");

        driver = Utils.getDriver(browser, baseUrl);
    }

    @BeforeEach
    public void testSetup(){
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @AfterEach
    public void afterTest() throws Exception {
        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") +" - "+ getClass().getName()+".png";
        Utils.takeSnapShot(driver, System.getProperty("user.dir")+ "\\images\\"+fileName);
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("Test finish");
        driver.quit();
    }

    /**
    Given: getting to base URL
    When: access the registration page
    Then: all fields exists and enabled

     */

    @Test()
    @DisplayName("Navigate to the registration page")
    public void GoToRegistrationPage() throws InterruptedException {

        logger.info("Begin Registration test");
        mainPage.goToLogin();
        loginPage.goToRegistration();
        Thread.sleep(2000);
        try {
            Assertions.assertTrue(registrationPage.header.isEnabled());
        }catch (Exception e){
            logger.error("You did not get to the right page");
        }
    }

    /**
     Given: getting to base URL
     When: insert exist user details in registration page
     Then: error massage appears

     */
    @Test()
    @DisplayName("InValid Test - insert exist user")
    public void checkExistUser(){
        logger.info("Begin second invalid test - continue without all details");
        registrationPage.setNameTxt(name);
        registrationPage.setEmailTxt(email);
        registrationPage.setPasswordTxt(password);
        registrationPage.setValidPasswordTxt(validationPassword);
        registrationPage.clickContinue();
        logger.info("Validate that error massage display on the screen");
        registrationPage.isElementDisplay(registrationPage.validationPasswordError);
    }

    @Test()
    @DisplayName("Invalid Test - continue without validation password")
    public void continueWithoutTheField(){
        logger.info("Begin first invalid test - insert exist user detail");
        registrationPage.isRegistrationPageDisplay();
        registrationPage.setNameTxt(name);
        registrationPage.setEmailTxt(email);
        registrationPage.setPasswordTxt(password);
        logger.info("Validate error massage appear");
        registrationPage.isElementDisplay(registrationPage.errorMassage);

    }

}
