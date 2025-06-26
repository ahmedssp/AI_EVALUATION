package BasePackage;


import Pages.A0_login_P;
import Pages.B0_login_P;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;


public class setup_simple {
    protected WebDriver d;
    protected A0_login_P Login_P_Obj;
    protected B0_login_P Login_P_Obj2;


    @BeforeMethod
    public void start() {

        WebDriverManager.firefoxdriver().setup();

        d = new FirefoxDriver();
//        d.navigate().to("https://chat.openai.com/auth/login");

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
    public void tt() throws InterruptedException {

        d.findElement(By.id("email")).sendKeys("search@gadd.sa");
        d.findElement(By.id("standard-adornment-password")).sendKeys("12332100");

         while (true){

             new Actions(d).doubleClick( d.findElement(By.xpath("//*[text()=\"Login\"]"))).perform();
             Thread.sleep(200);
             if(d.getCurrentUrl().equals("http://ai-platform.wakeb.tech/")){
                break;
              }
         }
        Assert.assertEquals(d.getCurrentUrl(),"http://ai-platform.wakeb.tech/");

        FluentWait<WebDriver> wait = new FluentWait<>(d)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));

        Thread.sleep(3000);

//        ((JavascriptExecutor) d).executeScript("arguments[0].click();", d.findElement(By.xpath("(//*[@id=\"Layer\"])[3]")));

       wait.until(ExpectedConditions.visibilityOf( d.findElement(By.xpath("(//*[@id=\"Layer\"])[3]"))));
        d.findElement(By.xpath("(//*[@id=\"Layer\"])[3]")).click();  //click chat
        Thread.sleep(5000);

        d.findElement(By.xpath("//*[@id=\"btnType\"]")).click(); //click list
        Assert.assertEquals(d.getCurrentUrl(),"http://ai-platform.wakeb.tech/chat");

        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable( d.findElement(By.xpath("//div[@class=\"MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation8 MuiPopover-paper css-1h05jzn\"]"))));
        ((JavascriptExecutor) d).executeScript("arguments[0].click();", d.findElement(By.xpath("(//span[@class=\"MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-yb0lig\"])[2]")));
        d.findElement(By.xpath("(//span[@class=\"MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-yb0lig\"])[2]")).click();
         d.findElement(By.xpath("//textarea[@aria-invalid=\"false\"]")).sendKeys("what is car");
        d.findElement(By.xpath("//textarea[@aria-invalid=\"false\"]")).sendKeys(Keys.ENTER);

        Thread.sleep(9000);

    }
    @Test
    public  void  ttte() throws InterruptedException {
        d.navigate().to("https://chat.openai.com/auth/login");
        d.findElement(By.xpath("(//div[@class=\"grid gap-x-3 gap-y-2 sm:grid-cols-2 sm:gap-y-0\"]//button)[1]")).click();

        while (true){

            new Actions(d).doubleClick(d.findElement(By.xpath("(//div[@class=\"grid gap-x-3 gap-y-2 sm:grid-cols-2 sm:gap-y-0\"]//button)[1]"))).perform();
            Thread.sleep(200);
            if(d.getCurrentUrl().contains("https://auth0.openai.com/u/login")){
                break;
            }
        }
    }

    @Test
    public  void  gold()  {

    }

}

