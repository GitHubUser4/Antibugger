package test.fbs.com;

import org.testng.ITestContext;
import org.testng.annotations.*;

/**
 * Created by s.popov on 11.08.2015.
 */
public class TestAuthentication {


    @BeforeMethod
    public void beforeMethod(ITestContext context)
    {
        String cabinetUserName = context.getCurrentXmlTest().getParameter("cabinet_user_name");
        String cabinetUserPassword = context.getCurrentXmlTest().getParameter("cabinet_user_password");
    }

        /*
    @AfterMethod
    public void afterMethod()
    {
        System.out.println("After Auth Method");
    }

    */

    @Test(description="переход на главную страницу кабинета")
    public void openCabinetURL()
    {
        driver.get("http://www.store.demoqa.com")
    }


    @Test(description="поптка аутентификации")
    public void authTry()
    {
        System.out.println("Неверный логин");
    }

    @Test(description="проверка на успешный вход")
    public void logonSuccess()
    {
        System.out.println("Неверный пароль");
    }

    @Test(description="проверка на ошибку входа")
    public void logonSuccess()
    {
        System.out.println("Неверный пароль");
    }
}
