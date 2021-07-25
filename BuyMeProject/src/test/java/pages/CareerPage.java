package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CareerPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(CareerPage.class);


    public CareerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".positionLink")
    List<WebElement> careersLinks;

    public int countCarrers(){
      switchToNewWindow();
      int numberOfCareers =  careersLinks.size();
      closeAndSwitchToMainWindow();
      return numberOfCareers;

    }



}
