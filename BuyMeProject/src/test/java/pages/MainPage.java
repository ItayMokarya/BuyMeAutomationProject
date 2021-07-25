package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    Actions actions  = new Actions(driver);

    @FindBy(className = "seperator-link")
    WebElement loginBtn;

    @FindBy(linkText = "careers")
    WebElement linkToCareers;

    @FindBy(linkText = "סכום")
    WebElement amountDropDown;

    @FindBy(linkText = "אזור")
    WebElement areaDropDown;

    @FindBy(linkText = "קטגוריה")
    WebElement categoryDropDown;

    @FindBy(linkText = "תמצאו לי מתנה")
    WebElement findGift;

    @FindBy (xpath = "//form/label/input")
    WebElement searchField;

    @FindBy (xpath = "//div/div[6]/div[2]/div/div/a")
    List<WebElement> giftOptions;

    @FindBy (xpath = "//div[6]/div[2]/div/div[1]/a/div")
    WebElement mainPageOption1;

    @FindBy (xpath = "//div[6]/div[2]/div/div[2]/a/div")
    WebElement mainPageOption2;

    @FindBy (xpath = "//div[6]/div[2]/div/div[3]/a/div")
    WebElement mainPageOption3;

    @FindBy (xpath = "//div[6]/div[2]/div/div[4]/a/div")
    WebElement mainPageOption4;

    @FindBy (xpath = "//div[6]/div[2]/div/div[5]/a/div")
    WebElement mainPageOption5;

    @FindBy (xpath = "//div[6]/div[2]/div/div[6]/a/div")
    WebElement mainPageOption6;

    @FindBy (xpath = "//div[6]/div[2]/div/div[7]/a/div")
    WebElement mainPageOption7;

    @FindBy (xpath = "//div[6]/div[2]/div/div[8]/a/div")
    WebElement mainPageOption8;

    @FindBy (xpath = "//div[6]/div[2]/div/div[9]/a/div")
    WebElement mainPageOption9;

    @FindBy (xpath = "//div[6]/div[2]/div/div[10]/a/div")
    WebElement mainPageOption10;

    @FindBy (xpath = "//div[6]/div[2]/div/div[11]/a/div")
    WebElement mainPageOption11;

    @FindBy (xpath = "//div[6]/div[2]/div/div[12]/a/div")
    WebElement mainPageOption12;

    @FindBy (xpath = "//div[6]/div[2]/div/div[13]/a/div")
    WebElement mainPageOption13;

    @FindBy (xpath = "//div[6]/div[2]/div/div[14]/a/div")
    WebElement mainPageOption14;

    @FindBy (xpath = "//div[6]/div[2]/div/div[15]/a/div")
    WebElement mainPageOption15;

// Methods
    public void goToLogin(){
        try{
            clickElement(loginBtn);
        }catch (Exception e){
            logger.error("Could not click on the link to login");
        }
    }

    public void goToCareers(){
        try{
            clickElement(linkToCareers);
        }catch (Exception e){
            logger.error("Could not click on the link to login");
        }
    }

    public void selectAmount(int index) throws InterruptedException {
        clickElement(amountDropDown);
        for (int i = 1; i<index;i++){
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
            Thread.sleep(1000);
        }
        actions.sendKeys(Keys.ENTER).build().perform();

    }

    public void selectArea(int index) throws InterruptedException {
        clickElement(areaDropDown);
        for (int i = 1; i<index;i++){
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
            Thread.sleep(1000);
        }
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void selectCategory(int index) throws InterruptedException {
        clickElement(categoryDropDown);
        for (int i = 1; i<index;i++){
            actions.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(2000);
        }
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void clickSearchGift() throws InterruptedException {
        Thread.sleep(2000);
        clickElement(findGift);
    }

    public void setSearchField(String value){
        typeText(searchField,value);
    }

    public enum mainPageOptions{
        GIFT_TO_HOME, SHEFF_RESTAURANT, FASHION_BRAND, BREAKFAST, RESTAURANT, BIRTH_AND_TOYS_GIFT, SPA, COLONIAL, VACATION_HOTEL,
        SHARD_EXPERIENCES, ENTERTAINMENT, KITCHEN_AND_GADGETS, SPORT, BEAUTY, WORKSHOP
    }
    public void selectOptionMainPage(mainPageOptions option){
        switch (option){
            case GIFT_TO_HOME:
                clickElement(mainPageOption1);
                break;
            case SHEFF_RESTAURANT:
                clickElement(mainPageOption2);break;
            case FASHION_BRAND:
                clickElement(mainPageOption3);
                break;
            case BREAKFAST:
                clickElement(mainPageOption4);
                break;
            case RESTAURANT:
                clickElement(mainPageOption5);
                break;
            case BIRTH_AND_TOYS_GIFT:
                clickElement(mainPageOption6);
                break;
            case SPA:
                clickElement(mainPageOption7);
                break;
            case COLONIAL:
                clickElement(mainPageOption8);
                break;
            case VACATION_HOTEL:
                clickElement(mainPageOption9);
                break;
            case SHARD_EXPERIENCES:
                clickElement(mainPageOption10);
                break;
            case ENTERTAINMENT:
                clickElement(mainPageOption11);
                break;
            case KITCHEN_AND_GADGETS:
                clickElement(mainPageOption12);
                break;
            case SPORT:
                clickElement(mainPageOption13);
                break;
            case BEAUTY:
                clickElement(mainPageOption14);
                break;
            case WORKSHOP:
                clickElement(mainPageOption15);
                break;
        }
    }
}
