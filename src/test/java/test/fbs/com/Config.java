package test.fbs.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by s.popov on 11.08.2015.
 */

public class Config {
    public static WebDriver driver;
    //WebElement element;

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
    @Parameters("browser")
    public static void openBrowser(String driverType){
        if (driverType.equalsIgnoreCase("Chrome"))
            driver = new ChromeDriver(); else
        if (driverType.equalsIgnoreCase("Firefox"))
            driver = new FirefoxDriver(); else
        if (driverType.equalsIgnoreCase("Opera"))
            driver = new OperaDriver(); else
        if (driverType.equalsIgnoreCase("Safari"))
            driver = new SafariDriver(); else
        if (driverType.equalsIgnoreCase("IE"))
            driver = new InternetExplorerDriver(); else
        {
            System.out.println("Wrong driver type");
            driver = null;
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public static void closeBrowser() {
        driver.quit();
    }
}
