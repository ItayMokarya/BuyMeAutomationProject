package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Utils;
import pages.PurchaseSecondPage;

import java.io.IOException;
import java.util.Date;

public class TestSanityBuyGiftMyself {

    public static WebDriver driver;
    public static String baseUrl;
    static String browser;

    static MainPage mainPage;
    static LoginPage loginPage;
    static GiftPage giftPage;
    static ResultPage resultPage;
    static PurchaseFirstPage purchaseFirstPage;
    static PurchaseSecondPage purchaseSecondPage;
    static PurchaseThirdPage purchaseThirdPage;

    static String email;
    static String password;
    static String existGiftName;
    static String amount;
    static String phoneNumber;

    static String fileName;

    private static final Logger logger = LogManager.getLogger(TestSanityBuyGiftMyself.class);

    @BeforeAll
    public static void setup() throws IOException {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

        baseUrl = Utils.getProperty("baseurl");
        browser = Utils.getProperty("browser");

        // Initialize variables
        email = Utils.getProperty("RegistrationEmail");
        password = Utils.getProperty("RegistrationPassword");
        existGiftName = "חדר בריחה";
        amount = "₪"+Utils.getProperty("amount");
        phoneNumber = Utils.getProperty("phoneNumber");

        driver = Utils.getDriver(browser, baseUrl);
    }

    @BeforeEach
    public void testSetup() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        giftPage = new GiftPage(driver);
        resultPage = new ResultPage(driver);
        purchaseFirstPage = new PurchaseFirstPage(driver);
        purchaseSecondPage = new PurchaseSecondPage(driver);
        purchaseThirdPage = new PurchaseThirdPage(driver);
    }

    @AfterEach
    public void afterTest() throws Exception {
        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") +" - "+ getClass().getName()+".png";
        Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\Images\\" + fileName);
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Test finish");
        driver.quit();
    }

    /**
    Given: getting to base URL
    When: purchase myself a gift
    Then: Reach the last screen with the purchase amount
     */
    @Test
    @DisplayName("Start Valid myself purchase test")
    public void searchAndSelectGiftToBuy() throws Exception {
        logger.info("Begin Search gift - buyGift");
        // Do login
        mainPage.goToLogin();
        loginPage.doLogin(email, password);
        mainPage.setSearchField(existGiftName);
        mainPage.clickSearchGift();
        logger.info("Validate if more than 0 result appears");
        try {
            Assertions.assertTrue(resultPage.countResult() > 0);
        } catch (Exception e) {
            logger.error("The'r 0 results to this gift name");
        }
        resultPage.selectGift();
        giftPage.selectOption();
        giftPage.clickSubmit();
        purchaseFirstPage.clickOnMyselfBtn();
        purchaseSecondPage.clickOnCloseField();
        purchaseSecondPage.howToSend(PurchaseSecondPage.howToSendGiftMethod.SMS, email, phoneNumber);
        purchaseSecondPage.clickOnContinue();
        purchaseThirdPage.selectPurchase(PurchaseThirdPage.purchaseMethod.CREDIT_CARD);
        try {
            Assertions.assertEquals(amount,purchaseThirdPage.getAmountPay());
        } catch (Exception e) {
            logger.error("The amount is incorrect");
        }
    }

}
