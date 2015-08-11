package test.fbs.com;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

/**
 * Created by s.popov on 11.08.2015.
 */
public class Config {
    private static ChromeDriver driver;
    WebElement element;

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

    /*
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

    @BeforeClass
    public void beforeClass()
    {
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClass()
    {
        System.out.println("After Class");
    }

}
