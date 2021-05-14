package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageBase {
    WebDriver driver;
    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsPresent(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementDisappears(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilElementDisappears(WebElement element, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsArePresent(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilAllElementsAreVisible(List<WebElement> list, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElements(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsVisible(By locator, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsVisible(WebElement element, int time) {

        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllWindowsAreLoaded(int windows,int time){
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillField(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
    }

    public String getAnotherWindowHandle(){
        String firstWindow = driver.getWindowHandle();
        String secondWindow = "";
        for(String wCode: driver.getWindowHandles()) {
            System.out.println("Window: " + wCode);
            if (!wCode.equals(firstWindow)) secondWindow = wCode;
        }
        return secondWindow;
    }

    public void rotateScreenLandScape() {
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void rotateScreenPortrate() {
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void navigateBack(){
        driver.navigate().back();
    }

    public void runBackGround(int time) {
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.runAppInBackground(Duration.ofSeconds(time));
    }

    public void swipeUp() {
        AppiumDriver appDriver = (AppiumDriver) driver;
        TouchAction action = new TouchAction(appDriver);
        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width*0.5);
        int y1 = (int) (size.height * 0.8);
        int y2 = (int) (size.height *0.2);
        action.press(PointOption.point(x,y1))
                .waitAction()
                .moveTo(PointOption.point(x,y2))
                .release()
                .perform();
    }
    public void swipeDown() {
        AppiumDriver appDriver = (AppiumDriver) driver;
        TouchAction action = new TouchAction(appDriver);
        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width*0.5);
        int y1 = (int) (size.height * 0.2);
        int y2 = (int) (size.height *0.8);
        action.press(PointOption.point(x,y1))
                .waitAction()
                .moveTo(PointOption.point(x,y2))
                .release()
                .perform();
    }

    public void swipeUpToElement(By by,int maxTime){
        int counter = 0;
        while (driver.findElements(by).size() == 0 && counter<maxTime){
            swipeUp();
            counter++;
        }

    }
}
