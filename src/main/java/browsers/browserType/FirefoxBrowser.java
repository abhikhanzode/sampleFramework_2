package browsers.browserType;

import browsers.DriverManagerWeb;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;

public class FirefoxBrowser extends DriverManagerWeb {

    @Override
    protected void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        webDriver =  new FirefoxDriver();
        webDriver.manage().window().maximize();
    }

    @Override
    protected void terminateDriver() {
        webDriver.quit();
        webDriver = null;
    }

    @Override
    protected String getDriverVersion(WebDriver webDriver) {
        Capabilities caps = ((RemoteWebDriver) webDriver).getCapabilities();;
        System.out.println(caps.getCapabilityNames() + "  defrfrf");
        Map<String, String> pref = (Map<String, String>) caps.getCapability("marionette");
        for (Map.Entry<String, String> e:pref.entrySet()) {
            System.out.println(e.getKey() + "     " + e.getValue());
        }
        return pref.get("geckodriverVersion");
    }
}
