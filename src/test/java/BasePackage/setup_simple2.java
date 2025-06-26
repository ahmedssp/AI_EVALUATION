package BasePackage;


import Pages.A0_login_P;
import Pages.B0_login_P;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class setup_simple2 {
    protected WebDriver d;
    protected A0_login_P Login_P_Obj;
    protected B0_login_P Login_P_Obj2;


    @BeforeMethod
    public void start() {

        WebDriverManager.edgedriver().setup();

        d = new EdgeDriver();
        d.navigate().to("https://www.bing.com/search?q=Bing%20AI&showconv=1&form=MG0AUO");

        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        d.manage().window().maximize();
        Login_P_Obj = new A0_login_P(d);
        Login_P_Obj2 = new B0_login_P(d);

    }
    @AfterMethod
    public void quit(ITestResult result) {

        if (d != null) {
            d.quit();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public  void  ttte()  {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

