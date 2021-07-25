package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Utils;

import java.io.IOException;
import java.util.Date;

public class TestSearchGiftInvalid {
    public static WebDriver driver;
    public static String baseUrl;
    static String browser;
    static MainPage mainPage;
    static LoginPage loginPage;
    static GiftPage giftPage;
    static ResultPage resultPage;

    static String email;
    static String password;
    static String invalidGiftName;
    static String existGiftName= "חדר בריחה";
    static String errorResultGift= "לא נמצאו תוצאות חיפוש ל\"aaaaa\"";
    static String errorResultGift2= "לא נמצאו תוצאות חיפוש ל\"aaaaa\" בירושלים בסכום 300-499 ש\"ח בקטגוריה גיפט קארד לבית, מטבח וגאדג'טים";
    static String errorResultGift3= "לא נמצאו תוצאות חיפוש ל\"חדר בריחה\" בקטגוריה גיפט קארד למותגי אופנה";

    static String fileName;



    private static final Logger logger = LogManager.getLogger(TestSearchGiftInvalid.class);



    @BeforeAll
    public static void setup() throws IOException {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

        baseUrl = Utils.getProperty("baseurl");
        browser = Utils.getProperty("browser");

        // Initialize variables
        email = Utils.getProperty("RegistrationEmail");
        password = Utils.getProperty("RegistrationPassword");
        invalidGiftName = Utils.getProperty("invalidGiftName");


        driver = Utils.getDriver(browser, baseUrl);
    }

    @BeforeEach
    public void testSetup(){
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        giftPage = new GiftPage(driver);
        resultPage = new ResultPage(driver);
    }

    @AfterEach
    public void afterTest() throws Exception {
        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") +" - "+ getClass().getName()+".png";
        Utils.takeSnapShot(driver, System.getProperty("user.dir")+ "\\Images\\"+fileName);
        // Go back to start new Test
        driver.navigate().refresh();
        Thread.sleep(1000);

    }

    @AfterAll
    public static void tearDown(){
        System.out.println("Test finish");
        driver.quit();
    }

    /**
     Given: getting to base URL
     When: insert invalid text
     Then: screen with 0 result and error massage appear */
    @Test
    @DisplayName("Invalid test - insert invalid gift name")
    public void insertInvalidNameAndClick() throws InterruptedException {

        logger.info("Begin Search gift invalid test - insertInvalidName");
        mainPage.goToLogin();
        loginPage.doLogin(email,password);
        mainPage.setSearchField(invalidGiftName);
        Thread.sleep(2000);
        mainPage.clickSearchGift();
        logger.info("Validate result Page");
        // Validate if suggest appear in the result page
        try {
            Assertions.assertTrue(resultPage.countResult() < 1);
        }catch (Exception e){
            logger.error("Suggestions appear even though invalid text was sent");
        }
        //validate if header is good
        try {
            Assertions.assertEquals(errorResultGift, resultPage.getHeaderText());
        }catch (Exception e){
            logger.error("The header is NOT good");
        }
    }

    /**
     Given: getting to base URL
     When: fill all the fields in valid details except gift name
     Then: screen with 0 result and error massage appear */
    @Test
    @DisplayName("Invalid test - insert all fields and Invalid gift name")
    public void freeTextFieldInvalid() throws Exception {

        logger.info("Begin Search gift invalid test - freeTextFieldInvalid");
        mainPage.selectAmount(5);
        mainPage.selectArea(6);
        mainPage.selectCategory(13);
        mainPage.setSearchField(invalidGiftName);
        mainPage.clickSearchGift();
        logger.info("Validate result Page");
        // Validate if suggest appear in the result page
        try {
            Assertions.assertTrue(resultPage.countResult() < 1);
        }catch (Exception e){
            logger.error("Suggestions appear even though invalid text was sent");
        }

        //validate if header is good
        try {
            Assertions.assertEquals(errorResultGift2, resultPage.getHeaderText());
        }catch (Exception e){
            logger.error("The header is NOT good");
        }
    }

    //Validate if in category field selected coffee bar And in Search field insert something else
    /**
     Given: getting to base URL
     When: Insert a valid gift name with a valid and inconsistent category
     Then: screen with 0 result and error massage appear */
    @Test
    @DisplayName("Invalid test - category not match to search field")
    public void  searchFieldNotMatchToCategory() throws InterruptedException {

        logger.info("Begin Search gift invalid test - searchFieldNotMatchToCategory");
        mainPage.selectCategory(5);
        mainPage.setSearchField(existGiftName);
        mainPage.clickSearchGift();
        logger.info("Validate result Page");

        // Validate if more then 0 suggests appears in result page
        int count = resultPage.countResult();
        try {
            Assertions.assertTrue(count < 1);
        }catch (Exception e){
            logger.error("Suggestions appear even though invalid text was sent");
        }

        //validate if header is good
        try {
            Assertions.assertEquals(errorResultGift3, resultPage.getHeaderText());
        }catch (Exception e){
            logger.error("The header is NOT good");
        }
    }

}
