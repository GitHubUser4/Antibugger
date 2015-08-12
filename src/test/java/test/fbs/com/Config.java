package test.fbs.com;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by s.popov on 11.08.2015.
 */
public class Config {
    public static ChromeDriver driver;
    WebElement element;

    /*
    @BeforeSuite
    public void beforeSuite()
    {

        System.out.println("Before Suite");
    }

    @AfterSuite
    public void afterSuite()
    {
        System.out.println("After Suite");
    }

    @BeforeGroups
    public void beforeGreoups()
    {
        System.out.println("Before Groups");
    }

    @AfterGroups
    public void afterGroups()
    {
        System.out.println("After Groups");
    }
    */


    @BeforeTest
    public static void openBrowser(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public static void closeBrowser() {
        driver.quit();
    }
}
