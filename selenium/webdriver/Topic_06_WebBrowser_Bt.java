package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebBrowser_Bt {
    WebDriver driver;
    String url = "http://live.techpanda.org/";

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Verify_Url() {
        driver.get(url);
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(1);

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        sleepInSecond(1);

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void TC_02_Verify_Tittle() {
        driver.get(url);
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(1);

        Assert.assertEquals(driver.getTitle(),"Customer Login");

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        sleepInSecond(1);

        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate() {
        driver.get(url);
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //sleepInSecond(1);

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //sleepInSecond(1);

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        //sleepInSecond(1);

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        //sleepInSecond(1);

        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_04_Verify_PageSource() {
        driver.get(url);
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
      //  sleepInSecond(1);

        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //sleepInSecond(1);

        Assert.assertTrue(driver.getPageSource().contains("Please enter the following information to create your account."));

    }

    public void sleepInSecond(long s) {
        try {
            Thread.sleep(s * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}