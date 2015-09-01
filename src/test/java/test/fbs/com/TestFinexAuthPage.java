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

    //����������� ����� ����� �������� ������
    @BeforeClass
    @Parameters({"url", "cabinet_user_name", "cabinet_user_password"})
    public static void getParameters(String url, String cabinet_user_name, String cabinet_user_password) {
        urlParam = url;
        cabinetUserName = cabinet_user_name;
        cabinetUserPassword = cabinet_user_password;
    }

    //����������� ��� ������ �� ������
    @AfterClass

    //����������� ����� ������ �������
    @BeforeMethod
    public static void pause() throws InterruptedException {
        Thread.sleep(1000L);
    }

    //����������� ����� ������� ������
    //@AfterMethod

    @Test(description = "����������� ��������� �� ��������")
    public void vaidateElementsOnPage() {
        Config.driver.findElement(By.id("LoginForm_login")); //���� ����� ������
        Config.driver.findElement(By.id("LoginForm_password")); //���� ����� ������
        Config.driver.findElement(By.name("yt0")); //������ Login
        Config.driver.findElements(By.linkText("Pendaftaran")); //������ ��� �����������
        Config.driver.findElements(By.linkText("Saya lupa password")); //������ �� �������������� ������
    }

    @Test(description = "������� �� �������� ����� � �������")
    public static void openCabinetURL() throws InterruptedException {
        Config.driver.get(urlParam);
        Config.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        assertEquals("FINEX", Config.driver.getTitle());
    }

    @Test(description = "������ ��������������")
    public void authTry() {
        Config.driver.findElement(By.id("LoginForm_login")).sendKeys(cabinetUserName);
        Config.driver.findElement(By.id("LoginForm_password")).sendKeys(cabinetUserPassword);
        Config.driver.findElement(By.name("yt0")).click();
    }

    @Test(description = "�������� �� �������� ����")
    public void logonSuccess() {
        Config.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        assertEquals("FINEX", Config.driver.getTitle());
        String loginAs =  Config.driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]")).getText();
        assertEquals("Anda login sebagai |", loginAs.substring(0, loginAs.indexOf('|') + 1));
    }

    @Test(description = "�������� �� ������ ���")
    public void loginEmpty() {
        WebElement missingLogin = Config.driver.findElement(By.id("LoginForm_login_em_"));
        assertEquals("Login/E-mail tidak boleh kosong.", missingLogin.getText());
    }

    @Test(description = "�������� �� ������ ������")
    public void passwordEmpty() {
        WebElement missingPassword = Config.driver.findElement(By.id("LoginForm_password_em_"));
        assertEquals("Password tidak boleh kosong.", missingPassword.getText());
    }

    @Test(description = "�������� �������� ����� ��� ������")
    public void loginOrPasswordWrong() {
        assertEquals("Login atau password yang anda masukkan tidak sesuai", Config.driver.findElement(By.id("LoginForm_password_em_")).getText());
    }


    @Test(description = "������� ����� �����")
    public void clearLoginPasswordFields() {
        Config.driver.findElement(By.id("LoginForm_login")).clear();
        Config.driver.findElement(By.id("LoginForm_password")).clear();
    }
}
