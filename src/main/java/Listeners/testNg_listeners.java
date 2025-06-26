package Listeners;

import org.testng.*;
import org.testng.annotations.*;
import org.testng.reporters.IReporterConfig;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * There are different types of Listeners in TestNG.
 * <p>
 * ITestListener
 * ISuiteListener
 * IReporter
 * IAnnotationTransformer
 * IAnnotationTransformer2
 * IMethodIntercepter
 * IHookable
 * IConfigurable
 * IConfigurationListener
 * IExecutionListener
 * IInvokedMethodIntercepter
 * IInvokedMethodIntercepter2
 */

/**
 *
 */

public class testNg_listeners implements ITestListener, IReporter, ISuiteListener, IAnnotationTransformer, IConfigurationListener, IExecutionListener {
    /**
     * @param annotation
     * @param testClass
     * @param testConstructor
     * @param testMethod
     */
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
    }

    /**
     * @param annotation
     * @param testClass
     * @param testConstructor
     * @param testMethod
     */
    @Override
    public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
    }

    /**
     * @param annotation
     * @param method
     */
    @Override
    public void transform(IDataProviderAnnotation annotation, Method method) {
        IAnnotationTransformer.super.transform(annotation, method);
    }

    /**
     * @param annotation
     * @param method
     */
    @Override
    public void transform(IFactoryAnnotation annotation, Method method) {
        IAnnotationTransformer.super.transform(annotation, method);
    }

    /**
     * @param annotation
     * @param testClass
     */
    @Override
    public void transform(IListenersAnnotation annotation, Class<?> testClass) {
        IAnnotationTransformer.super.transform(annotation, testClass);
    }

    /**
     * @param tr
     */
    @Override
    public void onConfigurationSuccess(ITestResult tr) {
        IConfigurationListener.super.onConfigurationSuccess(tr);
    }

    /**
     * @param tr
     * @param tm
     */
    @Override
    public void onConfigurationSuccess(ITestResult tr, ITestNGMethod tm) {
        IConfigurationListener.super.onConfigurationSuccess(tr, tm);
    }

    /**
     * @param tr
     */
    @Override
    public void onConfigurationFailure(ITestResult tr) {
        IConfigurationListener.super.onConfigurationFailure(tr);
    }

    /**
     * @param tr
     * @param tm
     */
    @Override
    public void onConfigurationFailure(ITestResult tr, ITestNGMethod tm) {
        IConfigurationListener.super.onConfigurationFailure(tr, tm);
    }

    /**
     * @param tr
     */
    @Override
    public void onConfigurationSkip(ITestResult tr) {
        IConfigurationListener.super.onConfigurationSkip(tr);
    }

    /**
     * @param tr
     * @param tm
     */
    @Override
    public void onConfigurationSkip(ITestResult tr, ITestNGMethod tm) {
        IConfigurationListener.super.onConfigurationSkip(tr, tm);
    }

    /**
     * @param tr
     */
    @Override
    public void beforeConfiguration(ITestResult tr) {
        IConfigurationListener.super.beforeConfiguration(tr);
    }

    /**
     * @param tr
     * @param tm
     */
    @Override
    public void beforeConfiguration(ITestResult tr, ITestNGMethod tm) {
        IConfigurationListener.super.beforeConfiguration(tr, tm);
    }

    /**
     *
     */
    @Override
    public void onExecutionStart() {
        IExecutionListener.super.onExecutionStart();
    }

    /**
     *
     */
    @Override
    public void onExecutionFinish() {
        IExecutionListener.super.onExecutionFinish();
    }

    /**
     * @param xmlSuites
     * @param suites
     * @param outputDirectory
     */
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        IReporter.super.generateReport(xmlSuites, suites, outputDirectory);
    }

    /**
     * @return
     */
    @Override
    public IReporterConfig getConfig() {
        return IReporter.super.getConfig();
    }

    /**
     * @param suite
     */
    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
    }

    /**
     * @param suite
     */
    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
    }

    /**
     * @param result
     */
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    /**
     * @param result
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    /**
     * @param result
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
    }

    /**
     * @param result
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    /**
     * @param result
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    /**
     * @param result
     */
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    /**
     * @param context
     */
    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    /**
     * @param context
     */
    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
