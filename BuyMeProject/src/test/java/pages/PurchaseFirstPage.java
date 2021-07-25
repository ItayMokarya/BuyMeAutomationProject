package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseFirstPage extends BasePage {


    public PurchaseFirstPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    Actions actions  = new Actions(driver);

    @FindBy (xpath = "//form/div[1]/div/div/div/div/div[2]")
    WebElement myselfBtn;

    @FindBy(xpath = "//form/div[2]/div[1]/label/input")
    WebElement receiverName;

    @FindBy(xpath = "//*[@type=\"submit\"]")
    WebElement continueBtn;

    @FindBy(xpath = "//textarea[@rows=\"4\"]")
    WebElement textField;

    @FindBy (xpath = "//*[@class=\"textarea-clear-all \"]")
    WebElement cleanTextBtn;

    @FindBy(name = "eventType")
    WebElement giftReasons;

    @FindBy(xpath = "//li[@value=\"10\"]")
    WebElement birthdayReason;

    @FindBy(xpath = "//li[@value=\"29\"]")
    WebElement otherReason;

    @FindBy(xpath = "//*[@id=\"gift-step-1\"]/div[2]/div[4]/div/div/i[2]")
    WebElement goLeft;

    @FindBy(xpath = "//*[@id=\"gift-step-1\"]/div[2]/div[4]/div/div/i[1]")
    WebElement goRight;

    @FindBy (className = "selected-name")
    WebElement selectBtn;

    public void clickOnMyselfBtn(){
        clickElement(myselfBtn);
        clickOnContinue();
    }

    public void setReceiverName(String name){
        typeText(receiverName, name);
    }

    public void clickOnContinue(){
        clickElement(continueBtn);
    }

    public void selectReason() {
        // The reason are saved so i change the reason and the choose birthday
        clickElement(selectBtn);
        clickElement(otherReason);
        clickElement(selectBtn);
        clickElement(birthdayReason);
    }

    public void typeNewText(String text){
        clickElement(cleanTextBtn);
        typeText(textField,text);
    }

    public void isBirthdayTextDisplayGood(String text,String text2){
        isTextEqualToElementText(textField, text);
        goToNextGreeting();
        logger.info("Validate if the second text appears");
        isTextEqualToElementText(textField, text2);
    }

    public void goToNextGreeting(){
        clickElement(goLeft);
    }
}
