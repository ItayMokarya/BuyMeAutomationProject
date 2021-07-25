package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import pages.CareerPage;
import pages.MainPage;
import utils.Utils;

import java.io.IOException;
import java.util.Date;

public class TestCareers {

    public static WebDriver driver;
    public static String baseUrl;
    static String browser;
    static String fileName;

    private static final Logger logger = LogManager.getLogger(TestCareers.class);

    MainPage mainpage;
    CareerPage careerPage;

    @BeforeAll
    public static void setup() throws IOException {
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

        baseUrl = Utils.getProperty("baseurl");
        browser = Utils.getProperty("browser");

        driver = Utils.getDriver(browser, baseUrl);
    }

    @AfterEach
    public void afterTest() throws Exception {
        Date d = new Date();
        fileName = d.toString().replace(":", "_").replace(" ", "_") +" - "+ getClass().getName()+".png";
        Utils.takeSnapShot(driver, System.getProperty("user.dir")+ "\\images\\"+fileName);
    }


    @BeforeEach
    public void testSetup(){
        logger.info("test setup");
        mainpage = new MainPage(driver);
        careerPage = new CareerPage(driver);

    }

    /**
    Given: getting to base URL
    When: access the career page
    Then: at list one job exist

     */


    @Test
    public void test_VerifyCareersPage() {
        logger.info("Begin registration test");
        mainpage.goToCareers();
        int careers = careerPage.countCarrers();
        Assertions.assertTrue(careers>0);

    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
        logger.info("Test finish");

    }

}
