package pages;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestPageConsinment extends TestBase {

    PageConsinment pageConsinment;

    @Test
    public void doStarted() throws InterruptedException {
        pageConsinment = new PageConsinment(eventFiringWebDriver);
        waitForLoad(eventFiringWebDriver);
        pageConsinment.clickOnBtnGetStarted();
        pageConsinment.clickOnBtnConsign();
        ArrayList<String> tabsCount = new ArrayList<>(eventFiringWebDriver.getWindowHandles());
        eventFiringWebDriver.switchTo().window(tabsCount.get(1));
        waitForLoad(eventFiringWebDriver);
        pageConsinment.enterTxtConsignmentID("UAT31234567", Keys.ENTER);
        pageConsinment.enterTxtBilledTo("CELW01");
        pageConsinment.selectMenuBilledTo();

        Thread.sleep(10000);
    }
}

