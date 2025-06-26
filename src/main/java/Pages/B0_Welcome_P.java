package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import static Handler.synchronization_methods.waitForElement;
import static Listeners.testNg_listeners_simple.PassMessege;
import static Listeners.testNg_listeners_simple.failledMessege;


public class B0_Welcome_P {
    private WebDriver d;

//    private static final Logger LOGGER = Logger.getLogger(testNg_listeners_simple.class);
    public B0_Welcome_P(WebDriver d) {
        this.d = d;
    }

    private  By SearchArea = By.id("prompt-textarea");
    private By CopyButton = By.xpath("(//div[@class=\"text-gray-400 flex self-end lg:self-center justify-center lg:justify-start mt-0 -ml-1 visible\"]//span)[1]");
    private By Enteremail = By.xpath("//input[@inputmode=\"email\"]");
    private By ContinueButton = By.xpath("//button[text()=\"Continue\"]");
    private  By password = By.id("password");


    public void GetUrl() {
        d.get("https://chat.openai.com/auth/login");
    }

    public void Send_email(String email) {
        try {
            waitForElement(d, Enteremail);
            d.findElement(Enteremail).sendKeys(email);
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
            waitForElement(d, password);
            d.findElement(password).sendKeys(Password);
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + PassMessege + "|| Data: " + Password);
        } catch (Exception e) {
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + failledMessege + "|| Data: " + Password);
            throw e;
        }
    }



}

