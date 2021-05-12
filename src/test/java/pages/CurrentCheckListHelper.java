package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CurrentCheckListHelper extends  PageBase{
    @FindBy(xpath = "//*[@content-desc = 'Navigate up']")
    WebElement backIcon;
    @FindBy(id = "com.flt.checklist:id/add_item")
    WebElement addItemButton;


    public CurrentCheckListHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(backIcon,10);
        waitUntilElementIsClickable(addItemButton,5);
    }

    public void backToCheckListsPage() {
        backIcon.click();
    }


}
