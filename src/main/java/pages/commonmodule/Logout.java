package pages.commonmodule;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.BasePage;
import java.util.List;

public class Logout extends BasePage {

    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "//div[@id='wpadminbar']/div/ul[2]//a")
    private WebElement usernameMenu;

    @FindBy(how = How.XPATH, using = "//ul[@id='wp-admin-bar-user-actions' and @class='ab-submenu']")
    private WebElement userSubMenu;

    private Actions actions;
    
    public Logout(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        actions = new Actions(driver);
    }

    @Step("Hover on UserName")
    public void doHoverOnUserName() {
        actions.moveToElement(usernameMenu).build().perform();
    }

    @Step("Submenu displayed")
    public boolean isSubMenuDisplayed() {
        return userSubMenu.isDisplayed();
    }

    @Step("Logout")
    public void doLogout() {
        List<WebElement> userSubMenuList = userSubMenu.findElements(By.xpath("li/a"));
        actions.moveToElement(userSubMenuList.get(2)).build().perform();
        userSubMenuList.get(2).click();
    }

//    public void doLogout() {
//       webDriverWait.until(ExpectedConditions.elementToBeClickable(logout));
//        logout.click();
//    }
}
