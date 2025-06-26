package Pages;

import Handler.synchronization_methods;
import Listeners.testNg_listeners_simple;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import java.time.Duration;

import static Handler.synchronization_methods.waitForElement;
import static Handler.synchronization_methods.waitFor_url_containing;
import static Listeners.testNg_listeners_simple.PassMessege;
import static Listeners.testNg_listeners_simple.failledMessege;

public class A1_Welcome_P {
    private WebDriver d;
    private static final Logger LOGGER = Logger.getLogger(testNg_listeners_simple.class);
    private By Click_logout_filed = By.id("logoutButton");
    ;

    public A1_Welcome_P(WebDriver d) {
        this.d = d;
    }

    private By Text_Welcome_filed = By.xpath("//*[contains(text(),\"مرحبا\")]");
    private By select_ListOf_location_filed = By.id("select_site");
    private By Wlecomemessege_filed = By.xpath("//*[contains(text(),\"مرحبا\")]");
    private By submitSiteBotton_filed = By.id("submitSite");
    private By logoutButton_Filed = By.id("logoutButton");

//    public void statusButton_click(int index_status) {
//        JavascriptExecutor j = (JavascriptExecutor) d;
//        j.executeScript("arguments[0].click();", d.findElement(statusButton_filed));
//    }
//    public void Select_gender(String GENDER) {
//
//        Select objSelect = new Select(d.findElement(Genderlist_filed));
//        objSelect.selectByVisibleText(GENDER);
//        try {
//            Alert alert = d.switchTo().alert();
//            String alertText = alert.getText();
//            System.out.println("Alert data: " + alertText);
//            alert.accept();
//        } catch (NoAlertPresentException e) {
//            e.printStackTrace();
//        }
//    }

    public void select_ListOf_location_filed(String Location) {

        try {
            synchronization_methods.waitForElement(d, select_ListOf_location_filed);
            Select location_list = new Select(d.findElement(select_ListOf_location_filed));
            location_list.selectByVisibleText(Location);
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + PassMessege + "|| Data: " + Location);
        } catch (Exception e) {
            try {
                //we need to select the option by JavascriptExecutor , so we need to locate element directly
                synchronization_methods.waitForElement(d, select_ListOf_location_filed);
                ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView(true);", d.findElement(By.id("select2-select_site-result-l1ss-8")));
                ((JavascriptExecutor) d).executeScript("arguments[0].click();", d.findElement(By.id("select2-select_site-result-l1ss-8")));
            } catch (Exception e2) {
                Reporter.log(new Object() {
                }.getClass().getEnclosingMethod().getName() + failledMessege + "|| Data2: " + Location);
                ;
                throw e2;
            }
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + failledMessege + "|| Data: " + Location);
            throw e;
        }
    }



    public A0_login_P logoutButton() {

        try {
            synchronization_methods.waitForElement(d, logoutButton_Filed);
            d.findElement(logoutButton_Filed).click();
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + PassMessege);
            return new A0_login_P(d);
        } catch (Exception e) {
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + failledMessege);
            throw e;
        }
    }

    public void Select_online( ) {
        FluentWait<WebDriver> wait = new FluentWait<>(d)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable( d.findElement(By.xpath("//div[@class=\"MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation8 MuiPopover-paper css-1h05jzn\"]"))));
        ((JavascriptExecutor) d).executeScript("arguments[0].click();", d.findElement(By.xpath("(//span[@class=\"MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-yb0lig\"])[2]")));
        d.findElement(By.xpath("(//span[@class=\"MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-yb0lig\"])[2]")).click();

    }

    public boolean getcurrenturl_valid(String messege) {

        try {

            waitFor_url_containing(d,"http://visitor-lpr.test/ar/dashboard/check-site");

            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + PassMessege);
            System.out.println(d.getCurrentUrl().toLowerCase().contains(messege));
            return d.getCurrentUrl().toLowerCase().contains(messege);

        } catch (Exception e) {
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + failledMessege);
            throw e;
        }

    }


    public void click_chat_icon() throws InterruptedException {
        Thread.sleep(5000);
        FluentWait<WebDriver> wait = new FluentWait<>(d)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOf( d.findElement(By.xpath("(//*[@id=\"Layer\"])[3]"))));
        d.findElement(By.xpath("(//*[@id=\"Layer\"])[3]")).click();  //click chat

    }

    public void click_list() {
        waitForElement(d, By.xpath("//*[@id=\"btnType\"]"));
        d.findElement(By.xpath("//*[@id=\"btnType\"]")).click(); //click list
        Assert.assertEquals(d.getCurrentUrl(),"http://ai-platform.wakeb.tech/chat");

    }

    public void search(String search_word) throws InterruptedException {
        d.findElement(By.xpath("//textarea[@aria-invalid=\"false\"]")).sendKeys(search_word);
        d.findElement(By.xpath("//textarea[@aria-invalid=\"false\"]")).sendKeys(Keys.ENTER);
        Thread.sleep(9000);
    }
}
