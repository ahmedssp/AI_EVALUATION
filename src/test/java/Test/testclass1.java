package Test;

import BasePackage.setup_simple;
import Pages.A1_Welcome_P;
import org.testng.annotations.Test;

public class testclass1  extends setup_simple {
    @Test
    public void test1() throws InterruptedException {
        d.navigate().to("http://ai-platform.wakeb.tech/login");


        Login_P_Obj.Send_email("search@gadd.sa");
        Login_P_Obj.Send_Password("12332100");
        A1_Welcome_P A1_Welcome_Obj = Login_P_Obj.click_login();
        A1_Welcome_Obj.click_chat_icon();
        A1_Welcome_Obj.click_list();
        A1_Welcome_Obj.Select_online();
        A1_Welcome_Obj.search("what type of car");

    }
    @Test
    public void test2() throws InterruptedException {
        d.navigate().to("https://chat.openai.com/auth/login");
        Login_P_Obj2.click_login2();

    }
}
