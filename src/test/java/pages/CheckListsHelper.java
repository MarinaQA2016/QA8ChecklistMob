package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.TestBase;

import java.util.List;

public class CheckListsHelper extends PageBase {
    @FindBy(id = "com.flt.checklist:id/add_shopping_list")
    WebElement addCheckListButton;
    @FindBy(xpath = "//*[@resource-id='android:id/custom']/*")
    WebElement nameCheckListField;
    @FindBy(id = "android:id/button1")
    WebElement oKButton;
    @FindBy(className = "android.widget.TextView")
    WebElement titleCheckLists;
    @FindBy(id = "com.flt.checklist:id/list_title")
    List<WebElement> namesChLList;

    public CheckListsHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(addCheckListButton,10);
        waitUntilElementIsVisible(titleCheckLists,5);
    }

    public void createNewCheckList(String name){
        addCheckListButton.click();
        waitUntilElementIsClickable(nameCheckListField,5);
        this.fillField(nameCheckListField, name);
        //driver.navigate().back();
        oKButton.click();
    }

    public int getQuantityLists() {
        return namesChLList.size();
    }

    public boolean isLastCheckListTitle(String title) {
        int size = this.getQuantityLists();
        boolean res = false;
        if(size != 0 && namesChLList.get(size-1).getText().equals(title)) {res = true;}
        return res;
    }

    public String getTitle() {
        return titleCheckLists.getText();
    }


}
