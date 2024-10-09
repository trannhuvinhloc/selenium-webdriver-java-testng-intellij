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
import java.util.Date;

public class Topic_15_Mix_Implicit_Explicit {
    WebDriver driver;
    String url = "https://www.facebook.com/";
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01_Implicit_Not_Found() {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try {
            System.out.println("Start time: " + new Date());
            driver.findElement(By.cssSelector("input#new"));
        } catch (Exception e) {
            System.out.println("End time: " + new Date());
            e.printStackTrace();
        }
    }

    @Test
    public void TC_02_Explicit_Not_Found() {
        driver.get(url);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            System.out.println("Start time: " + new Date());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#new")));
        } catch (Exception e) {
            System.out.println("End time: " + new Date());
            e.printStackTrace();
        }
    }

    @Test
    public void TC_03_Explicit_Not_Found_WebElement_Params() {
        driver.get(url);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            System.out.println("Start time: " + new Date());
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#new"))));
        } catch (Exception e) {
            System.out.println("End time: " + new Date());
            e.printStackTrace();
        }
    }

    @Test
    public void TC_04_Explicit_Less_Implicit() {
        driver.get(url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            System.out.println("Start time: " + new Date());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#new")));
        } catch (Exception e) {
            System.out.println("End time: " + new Date());
            e.printStackTrace();
        }
    }

    @Test
    public void TC_05_Explicit_Equal_Implicit() {
        driver.get(url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            System.out.println("Start time: " + new Date());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#new")));
        } catch (Exception e) {
            System.out.println("End time: " + new Date());
            e.printStackTrace();
        }
    }

    @Test
    public void TC_06_Explicit_More_Implicit() {
        driver.get(url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(7));

        try {
            System.out.println("Start time: " + new Date());
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#new")));
        } catch (Exception e) {
            System.out.println("End time: " + new Date());
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}