package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CheckListAppTest {

public AppiumDriver driver;

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
    }

    @Test
     public void applTest(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("+ element: " + driver
                .findElement(By.id("com.flt.checklist:id/add_shopping_list")).getAttribute("class"));
        System.out.println("Title: " + driver
                .findElement(By.className("android.widget.TextView")).getText());
    }
    @Test
    public void addNewList() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("com.flt.checklist:id/add_shopping_list")).click();
        Thread.sleep(2000);
        WebElement nameField = driver.findElement(By.xpath("//*[@resource-id='android:id/custom']/*"));
        nameField.click();
        nameField.sendKeys("Test");
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@content-desc = 'Navigate up']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver
                .findElement(By.id("com.flt.checklist:id/list_title")).getText().contains("Test"));

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
