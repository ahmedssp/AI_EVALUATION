package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import static Handler.synchronization_methods.waitForElement;
import static Listeners.testNg_listeners_simple.PassMessege;
import static Listeners.testNg_listeners_simple.failledMessege;


public class A0_login_P {
    private WebDriver d;
//    private static final Logger LOGGER = Logger.getLogger(testNg_listeners_simple.class);

    public A0_login_P(WebDriver d) {
        this.d = d;
    }

    private By email_filed = By.id("email");
    private By Password_filed = By.id("standard-adornment-password");
    private By Inter_Botton_filed = By.xpath("//*[text()=\"Login\"]");
    private By TextValidation_filed = By.xpath("//*[text()=\"تسجيل الدخول\"]");
    private By Locked_Msg_filed = By.xpath("//div[@class=\"invalid-feedback\"]//*[contains(.,\"Too many login attempts\")]");


    public void Send_email(String email) {
        try {
            waitForElement(d, email_filed);
            d.findElement(email_filed).sendKeys(email);
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + PassMessege + "|| Data: " + email);
        } catch (Exception e) {
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + failledMessege + "|| Data: " + email);
            throw e;
        }
    }

    public void Send_Password(String Password) {

        try {
            waitForElement(d, Password_filed);
            d.findElement(Password_filed).sendKeys(Password);
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + PassMessege + "|| Data: " + Password);
        } catch (Exception e) {
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + failledMessege + "|| Data: " + Password);
            throw e;
        }
    }

    public  A1_Welcome_P click_login() throws InterruptedException {

        while (true){

            new Actions(d).doubleClick(d.findElement(Inter_Botton_filed)).perform();
            Thread.sleep(200);
            if(d.getCurrentUrl().equals("http://ai-platform.wakeb.tech/")){
                break;
            }
        }
        Assert.assertEquals(d.getCurrentUrl(),"http://ai-platform.wakeb.tech/");
        return new  A1_Welcome_P(d);

    }
}
