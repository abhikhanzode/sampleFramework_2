package browsers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManagerWeb {

    protected WebDriver webDriver;
    protected abstract void createDriver() ;
    protected abstract void terminateDriver();
    protected abstract String getDriverVersion(WebDriver driver);

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            createDriver();
        }
        return webDriver;
    }

    public void quitDriver()   {
        if (null != webDriver) {
            terminateDriver();
        }
    }

    public String getWebDriverVersion() throws InterruptedException {
        this.webDriver = getWebDriver();
        return getDriverVersion(webDriver);
    }
}
