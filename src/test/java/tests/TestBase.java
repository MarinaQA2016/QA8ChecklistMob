package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CheckListsHelper;
import util.LogLog4j;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    public AppiumDriver driver;
    CheckListsHelper checkListsPage;
    public static LogLog4j log4j = new LogLog4j();

    @BeforeMethod(alwaysRun = true)
    public void startUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1.0");
        capabilities.setCapability("appPackage", "com.flt.checklist");
        capabilities.setCapability("appActivity","com.flt.checklist.MainActivity");
        capabilities.setCapability("automationName","Uiautomator2");
        capabilities.setCapability("app",
                "C:/Marina/TelRan/Auto/Groups/QAHaifa8/QA8ChecklistMob/apk/easy_checklist.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        checkListsPage = PageFactory.initElements(driver, CheckListsHelper.class);
        checkListsPage.waitUntilPageIsLoaded();
        checkListsPage.rotateScreenPortrate();
        checkListsPage.waitUntilPageIsLoaded();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
