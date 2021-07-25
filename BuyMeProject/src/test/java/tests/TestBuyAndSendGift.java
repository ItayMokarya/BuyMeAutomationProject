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

public class TestBuyAndSendGift {
    public static WebDriver driver;
    public static String baseUrl;
    static String browser;
    static String fileName;

    static MainPage mainPage;
    static LoginPage loginPage;
    static GiftPage giftPage;
    static ResultPage resultPage;
    static PurchaseFirstPage purchaseFirstPage;
    static PurchaseSecondPage purchaseSecondPage;
    static PurchaseThirdPage purchaseThirdPage;

    static String senderName;
    static String email;
    static String password;
    static String receiverName;
    static String myName;
    static String congratsText;
    static String amountToVoucher;
    static String phoneNumber;
    static String amount;
    static String birthdayText = "המון מזל טוב ליום ההולדת,\n" +
            "שיהיה יום מדהים, מלא באושר ובחיוכים :)\n" +
            "ממני באהבה";
    static String secondBithdayText = "מזל טוב!\n" +
            "לעוד הרבה שנים מאושרות מלאות בחוויות \n" +
            "ומלאות אהבה, בריאות ואושר ";



    private static final Logger logger = LogManager.getLogger(TestBuyAndSendGift.class);

    @BeforeAll
    public static void setup() throws IOException {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

        baseUrl = Utils.getProperty("baseurl");
        browser = Utils.getProperty("browser");

        // Initialize variables
        email = Utils.getProperty("RegistrationEmail");
        password = Utils.getProperty("RegistrationPassword");
        phoneNumber = Utils.getProperty("phoneNumber");
        amountToVoucher = Utils.getProperty("amountToVoucher");
        receiverName = Utils.getProperty("receiverName");
        myName = Utils.getProperty("myName");
        congratsText = Utils.getProperty("CongratsText");
        amount = "₪"+Utils.getProperty("secondAmount");
        senderName = Utils.getProperty("RegistrationName");


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
    When: Sending a gift to someone
    Then: Reach the last screen with the purchase amount

     */

    @Test
    @DisplayName("Start valid test")
    public void selectGiftFromMainPage() throws Exception {
        logger.info("Begin Search gift valid test - buyGift");
        // Do login
        mainPage.goToLogin();
        loginPage.doLogin(email, password);
        mainPage.selectOptionMainPage(MainPage.mainPageOptions.SPORT);
        resultPage.selectGift();
        logger.info("Validate if the gift is voucher send amount and continue");
        giftPage.checkAndContinue(amountToVoucher);
    }

    @Test
    @DisplayName("fill valid receiver details and finish test")
    public void startPurchase() throws InterruptedException {
        Thread.sleep(2000);
        purchaseFirstPage.setReceiverName(receiverName);
        purchaseFirstPage.selectReason();
        logger.info("Validate if the texts appears");
        purchaseFirstPage.isBirthdayTextDisplayGood(birthdayText,secondBithdayText);
        logger.info("Insert free text and continue");
        purchaseFirstPage.typeNewText(congratsText);
        purchaseFirstPage.clickOnContinue();
        logger.info("Validate if error massage appears");
        // if the email/Sms/print field appear close him
        purchaseSecondPage.clickOnCloseField();
        purchaseSecondPage.clickOnContinue();
        purchaseSecondPage.isErrorMassageAppear();
        logger.info("Pick how to send gift");
        purchaseSecondPage.howToSend(PurchaseSecondPage.howToSendGiftMethod.EMAIL, email, phoneNumber);
        purchaseSecondPage.isFromWhoFieldDisplayGood(senderName);
        purchaseSecondPage.setFromWhoField(myName);
        purchaseSecondPage.clickOnContinue();
        logger.info("Validate if the amount is correct");
        try {
            Assertions.assertEquals(amount,purchaseThirdPage.getAmountPay());
        } catch (Exception e) {
            logger.error("The amount is incorrect");
        }
        purchaseThirdPage.selectPurchase(PurchaseThirdPage.purchaseMethod.BIT);
        purchaseThirdPage.clickOnReadAndAgree();
    }
}

