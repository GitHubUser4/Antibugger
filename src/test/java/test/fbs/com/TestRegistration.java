package test.fbs.com;

import org.testng.annotations.*;

/**
 * Created by s.popov on 11.08.2015.
 */
public class TestRegistration {
    /*
    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("Before Reg Method");
    }

    @AfterMethod
    public void afterMethod()
    {
        System.out.println("After Reg Method");
    }
    */

    @Test(groups = {"positive", "reg"})
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

    @Test(groups = {"positive", "reg"})
    public void zeroSpreadRegistration() {
        System.out.println("��������������� Zero Spread ����");
    }

    @Test(groups = {"positive", "reg"})
    public void unlimSpreadRegistration() {
        System.out.println("��������������� ����������� ����");
    }

}
