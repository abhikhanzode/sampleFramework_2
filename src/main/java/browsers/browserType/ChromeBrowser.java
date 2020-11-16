package browsers.browserType;

import browsers.DriverManagerWeb;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;

public class ChromeBrowser extends DriverManagerWeb {

    public ChromeBrowser() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    protected void createDriver()  {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeOptions.addArguments("start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @Override
    protected void terminateDriver() {
        webDriver.quit();
        webDriver = null;
    }

    @Override
    protected String getDriverVersion(WebDriver webDriver) {
        Capabilities caps = ((RemoteWebDriver) webDriver).getCapabilities();
        Map<String, String> pref = (Map<String, String>) caps.getCapability("chrome");
        return pref.get("chromedriverVersion");
    }
}
