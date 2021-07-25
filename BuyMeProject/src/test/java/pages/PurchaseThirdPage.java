package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PurchaseThirdPage extends BasePage {
    public PurchaseThirdPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//*[@class=\"value\"]")
    WebElement amountToPay;

    @FindBy (xpath = "//div[3]/div/div[2]")
    WebElement creditCardBtn;

    @FindBy (css = "div > div.header")
    WebElement creditCardPopAppHeader;

    @FindBy (xpath = "//div[3]/div/div[3]")
    WebElement bitBtn;

    @FindBy (xpath = "//div[3]/div/div[4]")
    WebElement payPalBtn;

    @FindBy (id = "times")
    WebElement exitPopUpBtn;

    @FindBy (css = "span.text.bm-body-2")
    WebElement payInNextPageText;

    @FindBy (css = "div > span.circle > svg")
    WebElement readAndAgreeBtn;

    @FindBy(xpath = "//*[@type=\"submit\"]")
    WebElement continueBtn;

    @FindBy (className = "times-circle")
    WebElement deleteBtn;

    @FindBy(className = "mx-12 error-message")
    WebElement errorMassage;

    public enum purchaseMethod{
        CREDIT_CARD, BIT, PAYPAL
    }

    public void selectPurchase(purchaseMethod method){
        switch (method){
            case CREDIT_CARD:
                clickCreditCardAndCheck();
                clickElement(deleteBtn);
                clickCreditCardAndCheck();
                break;
            case BIT:
                clickOnBitAndCheck();
                clickElement(deleteBtn);
                clickOnBitAndCheck();
                logger.info("Validate if can finish the purchase without click on read and agree");
                clickOnContinue();
                try {
                    Assertions.assertTrue(isErrorMassageDisplay());
                } catch (Exception e) {
                    logger.error("The error massage is NOT appears");
                }
                break;
            case PAYPAL:
                clickPayPal();
                clickElement(deleteBtn);
                clickPayPal();
                logger.info("Validate if can finish the purchase without click on read and agree");
                clickOnContinue();
                try {
                    Assertions.assertTrue(isErrorMassageDisplay());
                } catch (Exception e) {
                    logger.error("The error massage is NOT appears");
                }
        }
    }

    public String getAmountPay(){
        wait.until(ExpectedConditions.elementToBeClickable(amountToPay));
        return amountToPay.getText();
    }

    public void clickCreditCardAndCheck(){
        clickElement(creditCardBtn);
        isElementDisplay(creditCardPopAppHeader);
        clickElement(exitPopUpBtn);
    }

    public void clickOnBitAndCheck(){
        clickElement(bitBtn);
        isElementDisplay(payInNextPageText);
    }

    public void clickPayPal(){
        clickElement(payPalBtn);
        isElementDisplay(payInNextPageText);
    }

    public void clickOnReadAndAgree(){
        clickElement(readAndAgreeBtn);
    }

    public void clickOnContinue(){
        clickElement(continueBtn);
    }

    public boolean isErrorMassageDisplay(){
        return errorMassage.isDisplayed();
    }
}
