package pages.commonmodule;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class Loader extends BasePage {

    @FindBy(how = How.CSS , using = "div._1aqMN-Bo9NmwQ_tqgWnTin")
    private WebElement elementLoader;

    public Loader(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Loading bar")
    public void waitForLoaderToDispose() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOf(elementLoader)); // wait for loader to disappear
    }
}
