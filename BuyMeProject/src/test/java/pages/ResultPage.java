package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class ResultPage extends BasePage {

    public ResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "div > h1")
    public WebElement mainHeader;

    @FindBy (xpath = "//span[@class='name bm-subtitle-1']")
    List<WebElement> allResult;

    @FindBy (xpath = "(//span[@class='name bm-subtitle-1'])[10]")
    WebElement resultGift;

    @FindBy (xpath = "//span[@class='name bm-subtitle-1']")
    List<WebElement> resultGifts;

    public int countResult(){
        wait.until(ExpectedConditions.elementToBeClickable(mainHeader));
        int numberOfCareers =  allResult.size();
        return numberOfCareers;

    }
    // select gift number 10
    public void selectGift(){
        //Actions actions = new Actions(driver);
        //actions.moveToElement(resultGift).click().build().perform();
         clickElement(resultGift);
    }

    public String getHeaderText(){
        return mainHeader.getText();
    }

}
