package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic08_Parameter {
    WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browserName) {
        openBrowser(browserName);

        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    private void openBrowser(String browserName) {
        switch (browserName) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browserName);
        }
    }

    private String getUrl(String environment) {
        switch (environment) {
            case "dev":
                return "http://dev.techpanda.org/";
            case "live":
                return "http://live.techpanda.org/";
            case "uat":
                return "http://uat.techpanda.org/";
            default:
                throw new IllegalStateException("Unexpected value: " + environment);
        }
    }

    @Parameters({"environment"})
    @Test
    public void TC1_Login(@Optional("live") String envi) {
        driver.get(getUrl(envi) + "index.php/customer/account/login/");

        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
        driver.findElement(By.xpath("//*[@id='send2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
