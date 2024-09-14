package Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class SuiteListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("Test failed: " + result.getMethod().getMethodName());
        Reporter.log("Error message: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: handle partial success scenarios
    }

    @Override
    public void onStart(ITestContext context) {
        Reporter.log("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("Test suite finished: " + context.getName());
    }
}
