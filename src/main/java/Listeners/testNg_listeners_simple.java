package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class testNg_listeners_simple implements ITestListener {
    public static final String PassMessege = TEXT_style("green", "15", "bold", " :Method Executed Successfully.");
    public static final String failledMessege = TEXT_style("red", "15", "bold", " :Method Faild To Execute.");


    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("onTestStart: " + result.getName());
        Reporter.log("onTestStart: " + result.getTestName() + "\n" + result.getName() + "\n" + result.getTestClass() + "\n" + result.getTestContext() + "\n" + result.id());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("onTestSuccess: " + result.getName());
        Reporter.log("onTestSuccess: " + result.getTestName() + "\n" + result.getName() + "\n" + result.getTestClass() + "\n" + result.getTestContext() + "\n" + result.id());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("onTestFailure: " + result.getName());
        Reporter.log("onTestFailure: " + result.getTestName() + "\n" + result.getName() + "\n" + result.getTestClass() + "\n" + result.getTestContext() + "\n" + result.id());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        System.out.println("onTestSkipped: " + result.getName());
        Reporter.log("onTestSkipped: " + result.getTestName() + "\n" + result.getName() + "\n" + result.getTestClass() + "\n" + result.getTestContext() + "\n" + result.id());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        System.out.println("onTestFailedButWithinSuccessPercentage: " + result.getName());
        Reporter.log("onTestFailedButWithinSuccessPercentage: " + result.getTestName() + "\n" + result.getName() + "\n" + result.getTestClass() + "\n" + result.getTestContext() + "\n" + result.id());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        System.out.println("onTestFailedWithTimeout: " + result.getName());
        Reporter.log("onTestFailedWithTimeout: " + result.getTestName() + "\n" + result.getName() + "\n" + result.getTestClass() + "\n" + result.getTestContext() + "\n" + result.id());

    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        System.out.println("onStart: " + context.getName());
        Reporter.log("onStart: " + context.getName() + "\n" + context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("onFinish: " + context.getName());
        Reporter.log("onFinish: " + context.getName());
        Reporter.log("About to end executing Class " + context.getName(), true);
    }

    public static String GetmethodName() {
        return new Object() {
        }.getClass().getEnclosingMethod().getName();
    }

    public static String GetmethodName2() {
        return Thread.currentThread().getStackTrace()[1].getMethodName();
    }

    public static String TEXT_style(String color, String size, String Tikness, String Text) {
        return "<span style='color:" + color + "; font-size:" + size + "px;font-weight:" + Tikness + ";'>" + Text + "</span>";
    }

}
