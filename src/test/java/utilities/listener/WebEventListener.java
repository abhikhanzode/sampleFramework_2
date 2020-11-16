package utilities.listener;

import common.GenericFunction;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import pages.TestBase;
import utilities.common.ScreenShots;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WebEventListener extends TestBase implements WebDriverEventListener {

    private String originalValue;
    private Logger logger;

    public WebEventListener() {
//        System.setProperty("filename", WebEventListener.class.getName());
        logger = Logger.getLogger(WebEventListener.class);
    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
//        System.out.println("Before Navigated to URL: " + driver.getCurrentUrl());
        logger.info("Before Navigated to URL: " + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
//        System.out.println("After Navigated to URL: " + driver.getCurrentUrl());
        logger.info("After Navigated to URL: " + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//        System.out.println("Finding element: " + by.toString());
//        logger.info("Finding element: " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

        try {
            JavascriptExecutor js = (JavascriptExecutor) eventFiringWebDriver;
            js.executeScript("arguments[0].setAttribute('style','outline: dashed 2px red;');", element);
            if (element.getAttribute("style") != null) {
                Thread.sleep(500);
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
//        System.out.println("Element Found and highlighted: " + by.toString());
//        logger.info("Element Found and highlighted: " + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
//        System.out.println("beforeClickOn");
        String strlastElement = (element.toString().split("->")[1]);
        strlastElement.substring(0, strlastElement.length() - 1);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
//        System.out.println("after click on");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
//        if (element.getAttribute("type").equals("text") || element.getAttribute("type").equals("password") || element.getAttribute("type").equals("email")) {
//            originalValue = element.getText();
//            if (GenericFunction.isNullOrEmpty(originalValue)) {
//                originalValue = element.getAttribute("value");
//            }
//        }
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
//        if (element.getAttribute("type").equals("text") || element.getAttribute("type").equals("password") || element.getAttribute("type").equals("email")) {
//            String changedValue = element.getAttribute("value");
//            if (GenericFunction.isNullOrEmpty(changedValue)) {
//                originalValue = element.getAttribute("value");
//            }
////            System.out.println("Entered Value :" + changedValue);
//        }
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
//        SimpleDateFormat sm = new SimpleDateFormat("MM_dd_HH_mm_ss");
//        String d = sm.format(new Date());
//        new ScreenShots().embedScreenshotBYTES( d);
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {

    }
}
