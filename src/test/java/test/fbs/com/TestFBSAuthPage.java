package test.fbs.com;

import org.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import javax.lang.model.element.Element;
import java.util.concurrent.TimeUnit;

/**
 * Created by s.popov on 11.08.2015.
 */
public class TestFBSAuthPage extends Assert implements WebForm {
    static String urlParam;
    static String cabinetUserName;
    static String cabinetUserPassword;

    //Выполняется перед всеми методами класса
    @BeforeClass
    @Parameters({"url", "cabinet_user_name", "cabinet_user_password"})
    public static void getParameters(String url, String cabinet_user_name, String cabinet_user_password) {
        urlParam = url;
        cabinetUserName = cabinet_user_name;
        cabinetUserPassword = cabinet_user_password;
    }

    //Выполняется при выходе из класса
    @AfterClass

    //Выполняется перед каждым методом
    @BeforeMethod
    public static void pause() throws InterruptedException {
        Thread.sleep(1000L);
    }

    //Выполняется после каждого метода
    //@AfterMethod

    @Test(description = "верификация элементов на странице")
    public void vaidateElementsOnPage() {
        Config.driver.findElement(By.id("LoginForm_login")).isDisplayed(); //окно ввода логина
        Config.driver.findElement(By.id("LoginForm_password")).isDisplayed(); //окно ввода пароля
        Config.driver.findElement(By.name("yt0")).isDisplayed(); //кнопка Login
        Config.driver.findElement(By.linkText("I forgot my password")).isDisplayed(); //ссылка на восстановление пароля
        Config.driver.findElement(By.linkText("Register an account")).isDisplayed(); //ссылка на регистрацию аккаунта
    }

    @Test(description = "переход на страницу входа в кабинет")
    public static void openCabinetURL() throws InterruptedException {
        Config.driver.get(urlParam);
        Config.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        assertEquals("Personal area - Log in", Config.driver.getTitle());
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
        assertEquals("Personal area - Trader area", Config.driver.getTitle());
    }

    @Test(description = "проверка на пустое имя")
    public void loginEmpty() {
        WebElement missingLogin = Config.driver.findElement(By.xpath("//*[@id='LoginForm_login_em_']"));
        assertEquals("This field is blank. Please, fill it in", missingLogin.getText());
        //WebElement element = Config.driver.findElement(By.xpath("//*[@id='LoginForm_login_em_'][contains(.,'This field is blank. Please, fill it in')]"));
        //Assert.assertTrue("Element not found", element != null);
    }

    @Test(description = "проверка на пустой пароль")
    public void passwordEmpty() {
        WebElement missingPassword = Config.driver.findElement(By.xpath("//*[@id='LoginForm_password_em_']"));
        assertEquals("This field is blank. Please, fill it in", missingPassword.getText());
    }

    @Test(description = "проверка неверный логин или пароль")
    public void loginOrPasswordWrong() {
        assertEquals("The login and password combination you have entered is incorrect", Config.driver.findElement(By.id("LoginForm_password_em_")).getText());
    }


    @Test(description = "очистка полей ввода")
    public void clearLoginPasswordFields() {
        Config.driver.findElement(By.id("LoginForm_login")).clear();
        Config.driver.findElement(By.id("LoginForm_password")).clear();
    }
}
