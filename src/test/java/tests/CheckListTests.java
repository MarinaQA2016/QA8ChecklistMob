package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckListsHelper;
import pages.CurrentCheckListHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class CheckListTests extends TestBase{
    CheckListsHelper checkListsPage;
    CurrentCheckListHelper currentCheckList;

    @BeforeMethod
    public void initTests(){
        checkListsPage = PageFactory.initElements(driver, CheckListsHelper.class);
        currentCheckList = PageFactory.initElements(driver, CurrentCheckListHelper.class);

        checkListsPage.waitUntilPageIsLoaded();
    }

    @Test
     public void applTest(){
        Assert.assertEquals("Check List", checkListsPage.getTitle());

    }
    @Test
    public void addNewList()  {
        int quantityBegin = checkListsPage.getQuantityLists();
        checkListsPage.createNewCheckList("CheckListEmpty");
        currentCheckList.waitUntilPageIsLoaded();
        currentCheckList.backToCheckListsPage();
        checkListsPage.waitUntilPageIsLoaded();
        checkListsPage.createNewCheckList("CheckListNext");
        currentCheckList.waitUntilPageIsLoaded();
        currentCheckList.backToCheckListsPage();
        checkListsPage.waitUntilPageIsLoaded();
        int quantityEnd = checkListsPage.getQuantityLists();

        Assert.assertEquals(quantityBegin+2,quantityEnd);
        Assert.assertTrue(checkListsPage.isLastCheckListTitle("CheckListNext"));
    }

    @Test
    public void addNewCheckListAndRotate(){
        int quantityBegin = checkListsPage.getQuantityLists();
        checkListsPage.createNewCheckList("CheckListForRotation");
        currentCheckList.waitUntilPageIsLoaded();
        currentCheckList.rotateScreenLandScape();
        currentCheckList.waitUntilPageIsLoaded();
        currentCheckList.backToCheckListsPage();
        checkListsPage.waitUntilPageIsLoaded();
        int quantityEnd = checkListsPage.getQuantityLists();

        Assert.assertEquals(quantityBegin+1,quantityEnd);
        Assert.assertTrue(checkListsPage.isLastCheckListTitle("CheckListForRotation"));

        checkListsPage.rotateScreenPortrate();
        checkListsPage.waitUntilPageIsLoaded();
    }
    @Test
    public void addNewNotEmptyCheckListAndRotate(){
        checkListsPage.createNewCheckList("CheckListForRotation");
        currentCheckList.waitUntilPageIsLoaded();
        currentCheckList.addNewItem("ItemTest");
        currentCheckList.checkFirstItem();
        currentCheckList.rotateScreenLandScape();
        currentCheckList.navigateBack();
        currentCheckList.waitUntilPageIsLoaded();

        Assert.assertTrue(currentCheckList.isFirstItemChecked());

        currentCheckList.backToCheckListsPage();
        checkListsPage.rotateScreenPortrate();
        checkListsPage.waitUntilPageIsLoaded();
    }


}
