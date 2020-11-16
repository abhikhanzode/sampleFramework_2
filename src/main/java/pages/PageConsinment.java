package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageConsinment extends BasePage {

    public PageConsinment(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
    }

    @FindBy(how = How.XPATH, using = "//button[normalize-space(text())='Get Started']")
    private WebElement elementbtnGetStarted;

//    @FindBy(how = How.XPATH, using="//div[@class='selector app-list tabbed app-selector-1']")
//    private WebElement elementDivSelector;

    @FindBy(how = How.XPATH, using = "//div[@class='selector app-list tabbed app-selector-1']//label[normalize-space(text())='Consign']/..")
    private WebElement elementbtnConsign;

    @FindBy(how = How.XPATH, using = "//input[@id='ConsignmentID']")
    private WebElement elementtxtConsignmentID;

    @FindBy(how = How.XPATH, using = "//input[@id='BilledTo']")
    private WebElement elementtxtBilledTo;

    @FindBy(how = How.XPATH, using = "//div[@class='v-menu__content theme--light menuable__content__active v-autocomplete__content']//div[@role='listitem'][1]")
    private WebElement elementMenuBilledTo;

    @FindBy(how = How.XPATH, using = "//input[@id='Commodity']")
    private WebElement elementtxtCommodity;

    @FindBy(how = How.XPATH, using = "//div[@class='v-menu__content theme--light menuable__content__active']//div[@role='listitem' and @class='primary--text']//text()")
    private WebElement elementMenuCommodity;

    public void clickOnBtnGetStarted() {
        elementbtnGetStarted.click();
    }

    public void clickOnBtnConsign() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementbtnConsign);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementbtnConsign);
    }

    public void enterTxtConsignmentID(String txt, Keys keys) {
        clearAndInputText(elementtxtConsignmentID, txt);
    }

    public void enterTxtBilledTo(String txt) throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(elementtxtBilledTo));
//        Thread.sleep(5000);
        elementtxtBilledTo.sendKeys(txt);
        elementtxtBilledTo.click();
    }

    public boolean isMenuBilledToDisplayed() {
        webDriverWait.until(ExpectedConditions.visibilityOf(elementtxtBilledTo));
        return elementMenuBilledTo.isDisplayed();
    }

    public void selectMenuBilledTo() {
        elementMenuBilledTo.click();
    }

    public void enterTxtCommodity(String txt) {
        elementtxtCommodity.click();
    }

    public boolean iselementMenuCommodity() {
        return elementMenuCommodity.isDisplayed();
    }

    public void iselementMenuCommodity1() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(elementMenuCommodity));
    }
}