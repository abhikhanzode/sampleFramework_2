package browsers;

import browsers.browserType.ChromeBrowser;
import browsers.browserType.FirefoxBrowser;
import browsers.browserType.IEBrowser;
import org.openqa.selenium.remote.BrowserType;

public class BrowserFactory {

    public static DriverManagerWeb getManager(String webDriverType)  {

        DriverManagerWeb driverManager;
        if (webDriverType.trim().toLowerCase().contains(BrowserType.IE)) {
            driverManager = new IEBrowser();
        } else if (webDriverType.trim().toLowerCase().contains(BrowserType.FIREFOX)) {
            driverManager = new FirefoxBrowser();
        } else if (webDriverType.trim().toLowerCase().contains(BrowserType.CHROME)) {
            driverManager = new ChromeBrowser();
        } else {
            throw new IllegalArgumentException("Invalid web driver : " + webDriverType);
        }
        return driverManager;
    }
}
