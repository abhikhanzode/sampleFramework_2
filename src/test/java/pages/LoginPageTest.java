package pages;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pages.commonmodule.Login;
import pages.commonmodule.Logout;

import java.util.Map;

public class LoginPageTest extends TestBase {

    private Login login;
    private Logout logout;

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify valid user are allowed to access")
    @Test(dataProviderClass = DataProviderList.class, dataProvider = "ValidLogin")
    public void signIn(Map<String, String> testDataSet) throws InterruptedException {
        login = new Login(eventFiringWebDriver);
        waitForLoad(eventFiringWebDriver);
        login.enterUserName(testDataSet.get("UserName"));
        login.loginClick();
        login.enterPassword(testDataSet.get("Password"));
        login.loginClick();

//        logout = new Logout(eventFiringWebDriver);
//        logout.doHoverOnUserName();
//        logout.doLogout();
    }
}
