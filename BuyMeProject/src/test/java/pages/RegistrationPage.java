package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[2]/h1")
    public WebElement header;

    @FindBy(xpath = "//div[3]/form/div[1]/label/input")
    public WebElement nameTxt;

    @FindBy (xpath = "//div[3]/form/div[2]/label/input")
    public WebElement emailTxt;

    @FindBy (xpath = "//div[3]/form/div[3]/label/input")
    public WebElement passwordTxt;

    @FindBy (xpath = "//div[3]/form/div[4]/label/input")
    public WebElement validPasswordTxt;

    @FindBy (xpath = "//form/button")
    public WebElement continueBtn;

    @FindBy (xpath = "//form/div[5]")
    public WebElement errorMassage;

    @FindBy (id = "//label/ul/li")
    public WebElement validationPasswordError;


    public void isRegistrationPageDisplay(){
        isElementDisplay(header);
    }

    public void setNameTxt(String name){
        typeText(nameTxt,name);
    }

    public void setEmailTxt(String email) {
        typeText(emailTxt,email);
    }

    public void setPasswordTxt(String password) {
        typeText(passwordTxt,password);
    }

    public void setValidPasswordTxt(String validPassword) {
        typeText(validPasswordTxt,validPassword);
    }

    public  boolean isErrorMassageDisplay(){
        return errorMassage.isDisplayed();
    }

    public boolean isPasswordErrorDisplay(){
        return validationPasswordError.isDisplayed();
    }

    public void clickContinue(){
        clickElement(continueBtn);
    }
}
