package utilities.listener;

import com.codoid.products.exception.FilloException;
import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pages.TestBase;
import utilities.common.ScreenShots;
import utilities.filereading.files.excel.FilloExcelWrite;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestListener extends TestBase implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public String screenshot() {
        String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
        return new ScreenShots().embedScreenshotBYTES(date);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", webDriver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        saveTextLog(getTestMethodName(iTestResult) + " passed and screenshot taken!");
//        if (webDriver != null) {
            new ScreenShots().embedScreenshotBYTES(getTestMethodName(iTestResult));
//        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        if (webDriver instanceof WebEventListener) {
            new ScreenShots().embedScreenshotBYTES(getTestMethodName(iTestResult));
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}