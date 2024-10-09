package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Alert {
    WebDriver driver;
    String url = "https://automationfc.github.io/basic-form/index.html";
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Commands() {
        driver.get(url);
        Alert alertSwitch = driver.switchTo().alert();

        Alert alertEx = explicitWait.until(ExpectedConditions.alertIsPresent());

        alertSwitch.sendKeys("abc");
        alertEx.getText();
        alertEx.dismiss();
        alertSwitch.accept();
    }

    @Test
    public void TC_02_Accept() {
        driver.get(url);

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_03_Confirm() {
        driver.get(url);
        WebElement jsBar = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")), result = driver.findElement(By.cssSelector("p#result"));

        jsBar.click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.accept();
        Assert.assertEquals(result.getText(), "You clicked: Ok");

        jsBar.click();
        Alert alert2 = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert2.dismiss();
        Assert.assertEquals(result.getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_04_Prompt() {
        driver.get(url);
        WebElement jsBar = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")), result = driver.findElement(By.cssSelector("p#result"));

        jsBar.click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.accept();
        Assert.assertEquals(result.getText(), "You entered:");

        jsBar.click();
        Alert alert2 = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert2.dismiss();
        Assert.assertEquals(result.getText(), "You entered: null");

        jsBar.click();
        Alert alert3 = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert3.sendKeys("Loc Tran");
        alert3.accept();
        Assert.assertEquals(result.getText(), "You entered: Loc Tran");
    }

    @Test
    public void TC_05_Authenication() {
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        String user = "admin", pass = "admin";
        driver.get("http://" + user + ":" + pass + "@the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_06_Authenication_New_Page() {
        driver.get("https://the-internet.herokuapp.com");
        String user = "admin", pass = "admin";

        String urlOriginal = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        String[] urlNew = urlOriginal.split("//");

        driver.get(urlOriginal);
        driver.get(urlNew[0] + "//" + user + ":" + pass + "@" + urlNew[1]);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
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