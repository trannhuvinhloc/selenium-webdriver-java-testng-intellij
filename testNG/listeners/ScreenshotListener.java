package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Fail");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipp");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("When class start");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("When class finish");
    }
}
