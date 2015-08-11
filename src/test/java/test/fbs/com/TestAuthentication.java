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

    @Test(description="������� �� ������� �������� ��������")
    public void openCabinetURL()
    {
        driver.get("http://www.store.demoqa.com")
    }


    @Test(description="������ ��������������")
    public void authTry()
    {
        System.out.println("�������� �����");
    }

    @Test(description="�������� �� �������� ����")
    public void logonSuccess()
    {
        System.out.println("�������� ������");
    }

    @Test(description="�������� �� ������ �����")
    public void logonSuccess()
    {
        System.out.println("�������� ������");
    }
}
