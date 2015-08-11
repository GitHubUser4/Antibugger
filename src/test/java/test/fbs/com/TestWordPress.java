package test.fbs.com;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by s.popov on 11.08.2015.
 */
public class TestWordPress {
    private static ChromeDriver driver;
    WebElement element;

    @BeforeSuite(alwaysRun = true)
    //public void setupBeforeSuite(ITestContext context) {
        public static void openBrowser(){
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

    @AfterSuite(alwaysRun = true)
    public void setupAfterSuite() {
        driver.close();
    }


    @Test(description="Launches the WordPress site")
    public void launchSite() throws InterruptedException {
        driver.get("http://demo.opensourcecms.com/wordpress/wp-login.php");
        driver.wait(10000L);
        Assert.assertEquals(driver.getTitle(), "Demo | Just another WordPress site");
    }

    @Test(description="Verifies the post")
    public void verifyBlogPost() {
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='nav']/a")), "Lost your password");
    }

}
