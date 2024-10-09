package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Implicit_Wait {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(buttonBy).click();

        Assert.assertEquals(driver.findElement(textBy).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Less_Than_5_Seconds() {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(buttonBy).click();

        Assert.assertEquals(driver.findElement(textBy).getText(), "Hello World!");
    }

    @Test
    public void TC_03_More_Than_5_Seconds() {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        driver.findElement(buttonBy).click();

        Assert.assertEquals(driver.findElement(textBy).getText(), "Hello World!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}