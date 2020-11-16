package pages.commonmodule;

/*
  This is generic login form implementation. Here there is only element which is <form> </form>, rest path get formed automatically.
  Currently only xpath locator is supported. To use this in you code provide the form xpath here @FindBy(how = How.XPATH, using = "//form")
  Deepak Verma - 27-04-2020
 */

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.BasePage;
import java.util.List;

public class Login extends BasePage {

//    @FindBy(how = How.XPATH, using = "//form[normalize-space(@id)='loginform']")
//    @FindBy(how = How.XPATH, using = "//form[@id='login-form']")
    @FindBy(how = How.XPATH, using = "//form[1]")
    private WebElement loginForm;

    public Login(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Enter UserName: \"{0}\"")
    public WebElement enterUserName(String userName) {
        WebElement elementUserName = loginForm.findElement(By.xpath("//child::input[@type='email' or @type='text']"));
        return clearAndInputText(elementUserName, userName);
    }

    @Step("Enter Password: \"{0}\"")
    public WebElement enterPassword(String password) {
        WebElement elementPassword = loginForm.findElement(By.xpath("//child::input[@type='password']"));
        return clearAndInputText(elementPassword, password);
    }

    @Step("Click Login button")
    public void loginClick() {
        WebElement elementPassword = loginForm.findElement(By.xpath("//child::*[@type='submit']"));
        elementPassword.click();
    }

    public void inputLogin(String userName, String password) {
        List<WebElement>  loginListElements = loginForm.findElements(By.xpath("//child::*"));
        for (WebElement loginElement : loginListElements) {
            if (loginElement.getTagName().equalsIgnoreCase("input")) {

                switch (loginElement.getAttribute("type")) {
                    case "email":
                    case "text":
                        clearAndInputText(loginElement, userName);
                        continue;
                    case "password":
                        clearAndInputText(loginElement, password);
                        continue;
                    default:
                        break;
                }
            }

            if (loginElement.getTagName().equalsIgnoreCase("button") ) {
                switch (loginElement.getAttribute("type")) {
                    case "reset":
                    case "submit":
                        loginElement.click();
                        continue;
                    default:
                        break;
                }
            }
        }
    }
}
