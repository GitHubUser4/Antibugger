package test.fbs.com;

import org.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by s.popov on 11.08.2015.
 */
public class TestFinexAuthPage extends Assert implements WebForm{
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
        Config.driver.findElement(By.id("LoginForm_login")); //окно ввода логина
        Config.driver.findElement(By.id("LoginForm_password")); //окно ввода пароля
        Config.driver.findElement(By.name("yt0")); //кнопка Login
        Config.driver.findElements(By.linkText("Pendaftaran")); //кнопка дял регистрации
        Config.driver.findElements(By.linkText("Saya lupa password")); //ссылка на восстановление пароля
    }

    @Test(description = "переход на страницу входа в кабинет")
    public static void openCabinetURL() throws InterruptedException {
        Config.driver.get(urlParam);
        Config.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        assertEquals("FINEX", Config.driver.getTitle());
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
        assertEquals("FINEX", Config.driver.getTitle());
        String loginAs =  Config.driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]")).getText();
        assertEquals("Anda login sebagai |", loginAs.substring(0, loginAs.indexOf('|') + 1));
    }

    @Test(description = "проверка на пустое имя")
    public void loginEmpty() {
        WebElement missingLogin = Config.driver.findElement(By.id("LoginForm_login_em_"));
        assertEquals("Login/E-mail tidak boleh kosong.", missingLogin.getText());
    }

    @Test(description = "проверка на пустой пароль")
    public void passwordEmpty() {
        WebElement missingPassword = Config.driver.findElement(By.id("LoginForm_password_em_"));
        assertEquals("Password tidak boleh kosong.", missingPassword.getText());
    }

    @Test(description = "проверка неверный логин или пароль")
    public void loginOrPasswordWrong() {
        assertEquals("Login atau password yang anda masukkan tidak sesuai", Config.driver.findElement(By.id("LoginForm_password_em_")).getText());
    }


    @Test(description = "очистка полей ввода")
    public void clearLoginPasswordFields() {
        Config.driver.findElement(By.id("LoginForm_login")).clear();
        Config.driver.findElement(By.id("LoginForm_password")).clear();
    }
}
