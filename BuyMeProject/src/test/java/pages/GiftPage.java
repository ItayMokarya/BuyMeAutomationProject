package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GiftPage extends BasePage {

    public GiftPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "gift-card-img")
    WebElement giftImg;

    @FindBy(xpath = "//form/div[1]/label/input")
    WebElement amountField;

    @FindBy (xpath = "//*[@type=\"submit\"][@gtm=\"בחירה\"]")
    WebElement submit;

    @FindBy(xpath = "//li[2]/a/div/div/div[3]/img")
    WebElement selectGift;

    @FindBy (id = "angle-left")
    WebElement leftArrow;

    @FindBy (xpath = "//ul/li[1]/a/div/div/div[2]/div/div/button")
    WebElement firstGift;

    public void setAmount(String value){
        wait.until(ExpectedConditions.elementToBeClickable(giftImg));
        typeText(amountField,value);
    }

    public void clickSubmit(){
        clickElement(submit);
    }

    public void selectOption() throws InterruptedException {
        Thread.sleep(1000);
        clickElement(leftArrow);
        clickElement(selectGift);
    }

    //Validate if the gift is voucher or gift card
    public void checkAndContinue(String amount){
        try {
            amountField.isDisplayed();
            logger.info("The gift is voucher");
            setAmount(amount);
            clickSubmit();
        }catch (Exception e){
            logger.info("The gift is NOT voucher");
            chooseFirstGift();
        }
    }

    public void chooseFirstGift(){
        clickElement(firstGift);
    }

}
