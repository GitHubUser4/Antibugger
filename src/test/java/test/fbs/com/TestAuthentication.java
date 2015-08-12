package test.fbs.com;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.*;

import javax.lang.model.element.Element;
import java.util.concurrent.TimeUnit;

/**
 * Created by s.popov on 11.08.2015.
 */
public class TestAuthentication {
    static String urlParam;
    static String cabinetUserName;
    static String cabinetUserPassword;

    @BeforeClass
    @Parameters({"url", "cabinet_user_name", "cabinet_user_password"})
    public static void getParameters(String url, String cabinet_user_name, String cabinet_user_password) {
        urlParam = url;
        cabinetUserName = cabinet_user_name;
        cabinetUserPassword = cabinet_user_password;
    }

    //¬ыполн€етс€ при выходе из класса
    @AfterClass

    //¬ыполн€етс€ перед каждым методом
    @BeforeMethod
    public static void pause() throws InterruptedException {
        Thread.sleep(1000L);
    }

    //¬ыполн€етс€ после каждого метода
    //@AfterMethod

    @Test(description = "переход на страницу входа в кабинет")
    public static void openCabinetURL() throws InterruptedException {
        Config.driver.get(urlParam);
        Config.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        Assert.assertEquals("Personal area - Log in", Config.driver.getTitle());
    }

    @Test(description = "поптка аутентификации")
    public void authTry() {
        Config.driver.findElement(By.id("LoginForm_login")).sendKeys(cabinetUserName);
        Config.driver.findElement(By.id("LoginForm_password")).sendKeys(cabinetUserPassword);
        Config.driver.findElement(By.name("yt0")).click();
    }

    @Test(description = "проверка на успешный вход")
    public void logonSuccess() {
        Config.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Assert.assertEquals("Personal area - Trader area", Config.driver.getTitle());
    }

    @Test(description = "проверка на пустое им€")
    public void loginEmpty() {
        WebElement missingLogin = Config.driver.findElement(By.xpath("//*[@id='LoginForm_login_em_']"));
        Assert.assertEquals("This field is blank. Please, fill it in", missingLogin.getText());
        //WebElement element = Config.driver.findElement(By.xpath("//*[@id='LoginForm_login_em_'][contains(.,'This field is blank. Please, fill it in')]"));
        //Assert.assertTrue("Element not found", element != null);
    }

    @Test(description = "проверка на пустой пароль")
    public void passwordEmpty() {
        WebElement missingPassword = Config.driver.findElement(By.xpath("//*[@id='LoginForm_password_em_']"));
        Assert.assertEquals("This field is blank. Please, fill it in", missingPassword.getText());
    }

    @Test(description = "проверка неверный логин или пароль")
    public void loginOrPasswordWrong() {
        Assert.assertEquals("The login and password combination you have entered is incorrect", Config.driver.findElement(By.id("LoginForm_password_em_")).getText());
    }


    @Test(description = "очистка полей ввода")
    public void clearLoginPasswordFields() {
        Config.driver.findElement(By.id("LoginForm_login")).clear();
        Config.driver.findElement(By.id("LoginForm_password")).clear();
    }
}
