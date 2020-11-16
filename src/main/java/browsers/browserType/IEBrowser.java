package browsers.browserType;

import browsers.DriverManagerWeb;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;

public class IEBrowser extends DriverManagerWeb {

    @Override
    protected void createDriver() {
        WebDriverManager.iedriver().setup();
        webDriver = new InternetExplorerDriver();
        webDriver.manage().window().maximize();
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
