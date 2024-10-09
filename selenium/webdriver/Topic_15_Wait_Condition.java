package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Wait_Condition {
    WebDriver driver;
    String url = "https://www.facebook.com/";
    WebDriverWait explicitWait;
    By emailBy = By.cssSelector("input[name='reg_email__']");

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void TC_01_Visible() {
        driver.get(url);
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailBy));
    }

    @Test
    public void TC_02_Invisible_Not_In_DOM() {
        driver.get(url);
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']/ancestor::div/div/img")))
                .click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(emailBy));
    }

    @Test
    public void TC_03_Invisible_In_DOM() {
        driver.get(url);
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(emailBy)).click();
        driver.findElement(By.cssSelector("input[name='firstname']")).click();
        driver.findElement(emailBy).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=\"What's your name?\"]")));
    }

    @Test
    public void TC_04_Presence() {
        driver.get(url);
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(emailBy)).click();
        driver.findElement(By.cssSelector("input[name='firstname']")).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=\"What's your name?\"]")));
    }

    @Test
    public void TC_05_Staleness() {
        driver.get(url);
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']/ancestor::div/div/img"))).click();
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(emailBy)));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}