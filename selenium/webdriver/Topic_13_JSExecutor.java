package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_JSExecutor extends Topic_13_JSExecutor_Functions {

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_() {
        jsExecutor.executeScript("window.location = 'http://live.techpanda.org'");
        sleepInSecond(1);
        Assert.assertEquals((String) jsExecutor.executeScript("return document.domain"), "live.techpanda.org");

        hightlightElement("//a[text()='Mobile']");
        jsExecutor.executeScript("arguments[0].click()",driver.findElement(By.xpath("//a[text()='Mobile']")));

        jsExecutor.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.cssSelector("form#newsletter-validate-detail")));
        hightlightElement("//form[@id='newsletter-validate-detail']");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}