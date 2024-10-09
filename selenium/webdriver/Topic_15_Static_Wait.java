package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Static_Wait {
    WebDriver driver;
    String url = "https://automationfc.github.io/dynamic-loading/";
    By buttonBy = By.cssSelector("div>button"), textBy = By.cssSelector("div>h4");

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_5_Seconds() {
        driver.get(url);

        driver.findElement(buttonBy).click();
        sleepInSeconds(5);

        Assert.assertEquals(driver.findElement(textBy).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Less_Than_5_Seconds() {
        driver.get(url);

        driver.findElement(buttonBy).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(textBy).getText(), "Hello World!");
    }

    @Test
    public void TC_03_More_Than_5_Seconds() {
        driver.get(url);

        driver.findElement(buttonBy).click();
        sleepInSeconds(7);

        Assert.assertEquals(driver.findElement(textBy).getText(), "Hello World!");
    }

    public void sleepInSeconds(long s) {
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