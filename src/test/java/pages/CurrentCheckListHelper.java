package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
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
    @FindBy(id = "com.flt.checklist:id/add_item_edit")
    WebElement addEditField;
    @FindBy(id = "com.flt.checklist:id/item_check")
    WebElement checkFirstItemIcon;


    public CurrentCheckListHelper(WebDriver driver) {
        super(driver);
    }

    public CurrentCheckListHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(backIcon,10);
        waitUntilElementIsClickable(addItemButton,5);
        return this;
    }

    public void backToCheckListsPage() {
        backIcon.click();
    }


    public CurrentCheckListHelper addNewItem(String name) {
        fillField(addEditField,name);
        waitUntilElementIsClickable(addItemButton,10);
        addItemButton.click();
        return this;
    }

    public void checkFirstItem() {
        waitUntilElementIsClickable(checkFirstItemIcon,5);
        checkFirstItemIcon.click();
    }

    public boolean isFirstItemChecked() {
        return driver.findElement(By.id("com.flt.checklist:id/item_check")).getAttribute("checked").equals("true");
    }
}
