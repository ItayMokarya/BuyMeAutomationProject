package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseSecondPage extends BasePage {

    public PurchaseSecondPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@type=\"submit\"]")
    WebElement continueBtn;

    @FindBy(xpath = "//*[@gtm=\"method-sms\"]")
    WebElement smsBtn;

    @FindBy (id = "sms")
    WebElement smsField;

    @FindBy(xpath = "//*[@gtm=\"method-email\"]")
    WebElement emailBtn;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy (xpath = "div.toggle-icon.hide-on-mobile > div.circle-area > svg")
    WebElement printBtn;

    @FindBy (xpath = "//div[3]/span[2]")
    WebElement printMassage;

    @FindBy (xpath = "//form/div[3]/div[2]/label/input")
    WebElement fromWhoField;

    @FindBy(className = "error-message")
    WebElement errorMassage;

    @FindBy(xpath = "//*[@class=\"times-circle\"]")
    WebElement closeFiled;


    public static enum howToSendGiftMethod{
        SMS, EMAIL, PRINT
    }

    public void howToSend(howToSendGiftMethod method, String email, String phone){
        switch (method){
            case SMS:
                clickOnSmsBtn();
                setSmsField(phone);
                break;
            case EMAIL:
                clickOnEmailBtn();
                setEmailField(email);
                break;
            case PRINT:
                clickOnPrint();
                isElementDisplay(printMassage);
        }
    }

    public void clickOnContinue(){
        clickElement(continueBtn);
    }

    public void clickOnSmsBtn(){
        clickElement(smsBtn);
    }

    public void clickOnEmailBtn(){
        clickElement(emailBtn);
    }

    public void setEmailField(String email){
        typeText(emailField,email);
    }

    public void setSmsField(String phoneNumber){
        typeText(smsField, phoneNumber);
    }

    public void clickOnPrint(){
        clickElement(printBtn);
    }

    public void setFromWhoField(String myName){
        typeText(fromWhoField,myName);
    }

    public boolean isFromWhoFieldDisplayGood(String text){
        return fromWhoField.getAttribute("value").equals(text);
    }

    public void isErrorMassageAppear() {
        isElementDisplay(errorMassage);
    }

    public void clickOnCloseField(){
        if (closeFiled.isDisplayed()){
            clickElement(closeFiled);
        }

    }

}
