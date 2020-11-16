package pages;

import browsers.BrowserFactory;
import browsers.DriverManagerWeb;
import io.qameta.allure.Step;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.filereading.files.properties.ReadProperties;
import utilities.filereading.files.xml.EnvironmentXml;
import utilities.listener.WebEventListener;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected DriverManagerWeb driverManager;
    protected static WebDriver webDriver;
    protected static EventFiringWebDriver eventFiringWebDriver;
    protected WebEventListener eventListener;
    private boolean IS_BROWSER_OPENED = false;
    private ReadProperties readProperties = new ReadProperties();

    private void openBrowser() throws InterruptedException {
        IS_BROWSER_OPENED = true;

        BasicConfigurator.configure();

        driverManager = BrowserFactory.getManager(readProperties.getPropertyValue("BROWSER_NAME"));
        webDriver = driverManager.getWebDriver();

        // Initializing EventFiringWebDriver using WebDriver instance
        eventFiringWebDriver = new EventFiringWebDriver(webDriver);

        eventListener = new WebEventListener();
        eventFiringWebDriver.register(eventListener);

        eventFiringWebDriver.manage().timeouts().pageLoadTimeout(Integer.parseInt(readProperties.getPropertyValue("DEFAULT_ELEMENT_WAIT_TIMEOUT")), TimeUnit.SECONDS);
        eventFiringWebDriver.get(readProperties.getPropertyValue("BASE_URL"));

        EnvironmentXml createAndWriteEnvironment = new EnvironmentXml();
        Map<String, String> envMap = Map.ofEntries(
                Map.entry("BrowserName", eventFiringWebDriver.getCapabilities().getBrowserName()),
                Map.entry("BrowserVersion", eventFiringWebDriver.getCapabilities().getVersion()),
//                Map.entry("DriverVersion", driverManager.getWebDriverVersion()),
                Map.entry("Platform", String.valueOf(eventFiringWebDriver.getCapabilities().getPlatform())),
                Map.entry("Environment", "OPT")
        );
        createAndWriteEnvironment.createAllureEnvironmentXml(envMap);

        waitForLoad(eventFiringWebDriver);
    }

    public TestBase() {
//        try {
//            if (!IS_BROWSER_OPENED) {
//                openBrowser();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            tearBrowser();
//        }

        //finalize()
    }

    @BeforeSuite
    public void setUp() throws InterruptedException {
        openBrowser();
    }

    @AfterSuite
    public void tearBrowser() {
        driverManager.quitDriver();
    }

    @Step("Open browser: {0}")
    public void openBaseURL(String BASEURL) {
        eventFiringWebDriver.get(BASEURL);
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) Objects.requireNonNull(driver1)).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(eventFiringWebDriver, Integer.parseInt(readProperties.getPropertyValue("DEFAULT_ELEMENT_WAIT_TIMEOUT")));
        wait.until(pageLoadCondition);
    }
}
