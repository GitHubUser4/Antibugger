package test.fbs.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.*;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

/**
 * Created by s.popov on 11.08.2015.
 */
public class TestFBSRegPage extends Assert implements WebForm{
    static String urlParam;
    static String registrationAccountType;
    static String registrationCurrency;
    static String registrationUserName;
    static String registrationUserPassword;
    static String registrationUserPasswordRepeat;
    static String registrationUserEmail;
    static String registrationUserPhone;
    static Integer registrationPartnerCode;
    static boolean registrationAgreeWithTerms;
    static boolean registrationAgreeWithRisks;
    static WebElement generalWebElement;
    static Select generalSelect;



    //����������� ����� ����� �������� ������
    @BeforeClass
    @Parameters({"url", "registration_account_type", "registration_currency", "registration_user_name", "registration_user_password",
            "registration_user_password_repeat", "registration_user_email", "registration_user_phone", "registration_partner_code",
            "registration_agree_with_terms", "registration_agree_with_risks"})
    public static void getParameters(String url, String registration_account_type, String registration_currency, String registration_user_name, String registration_user_password,
                                     String registration_user_password_2, String registration_user_email, String registration_user_phone, Integer registration_partner_code,
                                     boolean registration_agree_with_terms, boolean registration_agree_with_risks) {
        urlParam = url;
        registrationAccountType = registration_account_type;
        registrationCurrency = registration_currency;
        if (registration_user_name.equalsIgnoreCase("AUTO")) registrationUserName = DataCreator.generateUserName(); else
        registrationUserName = registration_user_name;
        registrationUserPassword = registration_user_password;
        registrationUserPasswordRepeat = registration_user_password_2;
        if (registration_user_email.equalsIgnoreCase("AUTO")) registrationUserEmail = DataCreator.generateUserEmail(); else
            registrationUserEmail = registration_user_email;
        if (registration_user_phone.equalsIgnoreCase("AUTO")) registrationUserPhone = DataCreator.generateUserPhone(); else
            registrationUserPhone = registration_user_phone;
        registrationPartnerCode = registration_partner_code;
        registrationAgreeWithTerms = registration_agree_with_terms;
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

    @Test(description = "����������� ��������� �� �������� �����������")
    public void vaidateElementsOnPage() {
        Config.driver.findElement(By.xpath("//*[@id=\"registration-form-step-first\"]/div[1]/div[1]/div[1]")).isDisplayed();  //�������� ������ �����
        Config.driver.findElement(By.xpath("//*[@id=\"registration-form-step-first\"]/div[1]/div[2]/div[1]")).isDisplayed(); //�������� ������ ������
        Config.driver.findElement(By.id("RegistrationForm_name")).isDisplayed(); //���� ����� �����
        Config.driver.findElement(By.id("RegistrationForm_password")).isDisplayed(); //���� ����� ������
        Config.driver.findElement(By.id("RegistrationForm_password2")).isDisplayed(); //���� ����� ������������� ������
        Config.driver.findElement(By.id("RegistrationForm_email")).isDisplayed(); //���� ����� email`a
        Config.driver.findElement(By.xpath("//input[@name='RegistrationForm[cellphone]']")).isDisplayed(); //���� ����� ������ ��������
        Config.driver.findElement(By.id("RegistrationForm_agree_with_terms")).isDisplayed(); //������� ������������� �������
        Config.driver.findElement(By.id("RegistrationForm_agree_with_risks")).isDisplayed(); //������� ������������� ������
    }

    @Test(description = "������� �� �������� �����������")
    public static void openRegURL() throws InterruptedException {
        Config.driver.get(urlParam);
        Config.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        Assert.assertEquals("Personal area - Registration", Config.driver.getTitle());
    }

    @Test(description = "���������� ����� �����")
    public void dataFilling() {
        //generalWebElement = Config.driver.findElement(By.xpath("//*[@id=\"registration-form-step-first\"]/div[1]/div[1]/div[1]"));
        Config.driver.findElement(By.xpath("//*[@id=\"RegistrationForm_tariffId\"]/optgroup[1]")).click();
        //generalSelect = new Select(generalWebElement);
        //generalSelect.selectByVisibleText(registrationAccountType);
        generalWebElement = Config.driver.findElement(By.xpath("//*[@id=\"registration-form-step-first\"]/div[1]/div[2]/div[1]/span[1]"));
        generalSelect = new Select(generalWebElement);
        generalSelect.selectByVisibleText(registrationCurrency);
        Config.driver.findElement(By.id("RegistrationForm_name")).sendKeys(registrationUserName);
        Config.driver.findElement(By.id("RegistrationForm_password")).sendKeys(registrationUserPassword);
        Config.driver.findElement(By.id("RegistrationForm_password2")).sendKeys(registrationUserPasswordRepeat);
        Config.driver.findElement(By.id("RegistrationForm_email")).sendKeys(registrationUserEmail);
        Config.driver.findElement(By.xpath("//input[@name='RegistrationForm[cellphone]']")).sendKeys(registrationUserPhone);
        Config.driver.findElement(By.id("RegistrationForm_agree_with_terms")).sendKeys(registrationUserEmail);
        if (registrationPartnerCode != null)
        {
            Config.driver.findElement(By.id("RegistrationForm_name")).click();
            Config.driver.findElement(By.id("RegistrationForm_partner_id")).sendKeys(registrationPartnerCode.toString());
        }
        if (registrationAgreeWithTerms)
            Config.driver.findElement(By.id("RegistrationForm_agree_with_terms")).click();
        if (registrationAgreeWithRisks)
            Config.driver.findElement(By.id("RegistrationForm_agree_with_risks")).click();
    }

    @Test(description = "�������� �� ������ ���")
    public void centRegistration() {
        System.out.println("��������������� �������� ����");
    }

    @Test(groups = {"positive", "reg"})
    public void microRegistration() {
        System.out.println("��������������� ����� ����");
    }

    @Test(groups = {"positive", "reg"})
    public void standartRegistration() {
        System.out.println("��������������� ����������� ����");
    }

    @Test(groups = {"positive", "reg"}, description = "sasda")
    public void zeroSpreadRegistration() {
        System.out.println("��������������� Zero Spread ����");
    }

    @Test(groups = {"positive", "reg"})
    public void unlimSpreadRegistration() {
        System.out.println("��������������� ����������� ����");
    }

}
