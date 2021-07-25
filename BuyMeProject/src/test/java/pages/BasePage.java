package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class BasePage {

    public static final Logger logger = LogManager.getLogger(BasePage.class);

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    //General Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 8);
        js = (JavascriptExecutor) driver;
    }

    //General click (strong with wait + scroll into)
    public void clickElement(WebElement element){
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (Exception e){
            logger.error("Could not click on the element");
        }
    }

    //General send keys + clear before
    public void typeText(WebElement element, String text){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    //Select by value from select object
    public void selectByValue(WebElement element, String value){

        Select selection = new Select(element);
        selection.selectByValue(value);
    }
    public void deselectAll(WebElement element){

        Select selection = new Select(element);
        selection.deselectAll();
    }


    //Switch to new window (after open it)
    public void switchToNewWindow(){
        String baseHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();


        for (String h: handles){
            if (!h.equals(baseHandle))
                driver.switchTo().window(h);
        }
    }


    //Switch to new window (after open it)
    public void closeAndSwitchToMainWindow(){
        String baseHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        driver.close();

        for (String h: handles){
            if (!h.equals(baseHandle))
                driver.switchTo().window(h);
        }
    }


    //Switch to frame by id
    public void switchToFrameByID(String frameId){
        driver.switchTo().frame(frameId);
    }

    //Switch to frame by index
    public void switchToFrameByID(int frameIndex){
        driver.switchTo().frame(frameIndex);
    }

    public void isElementDisplay(WebElement element){
        try {
            Assertions.assertTrue(element.isDisplayed());
        }catch (Exception e){
            logger.info("The element is NOT exist");
        }
    }

    public void isTextEqualToElementText(WebElement element, String text){
        try {
            Assertions.assertEquals(element.getAttribute("value"), text);
        }catch (Exception e) {
            logger.error("The text is incorrect");
        }
    }

}
