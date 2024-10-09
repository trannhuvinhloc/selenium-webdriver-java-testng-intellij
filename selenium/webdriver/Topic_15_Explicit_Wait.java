package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_15_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    String systemPath = System.getProperty("user.dir");

    String haLongFile = "ha_long.jpg", sonDoongFile = "son_doong.jpg", boardFile = "board.jpg";

    String haLongFilePath = systemPath + File.separator + "fileUpload" + File.separator + haLongFile;
    String sonDoongFilePath = systemPath + File.separator + "fileUpload" + File.separator + sonDoongFile;
    String boardFilePath = systemPath + File.separator + "fileUpload" + File.separator + boardFile;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Dynamic_Loading_Invisible_5s() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div>button")).click();
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div>img")))));
        Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Dynamic_Loading_Invisible_Less_Than_5s() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div>button")).click();
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div>img"))));
        Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_03_Dynamic_Loading_Invisible_More_Than_5s() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(7));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div>button")).click();
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div>img"))));
        Assert.assertEquals(driver.findElement(By.cssSelector("div>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_04_Dynamic_Loading_Visible() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(6));
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div>button")).click();
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div>h4"))).getText(), "Hello World!");
    }

    @Test
    public void TC_05_Ajax_Loading() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer span")).getText(), "No Selected Dates to display.");
        driver.findElement(By.xpath("//a[text()='9']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("body>div.RadAjax"))));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer span")).getText(), "Monday, September 9, 2024");
    }

    @Test
    public void TC_06_GoFile() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://gofile.io/welcome");

        Assert.assertTrue(explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("div.spinner-border")))));
        driver.findElement(By.cssSelector("a>button")).click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.spinner-border")))));
        driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(haLongFilePath + "\n" + sonDoongFilePath + "\n" + boardFilePath);

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.spinner-border")))));

        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + haLongFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + sonDoongFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + boardFile + "']")).isDisplayed());

        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.mainUploadSuccess div.border-success")))).getText(), "Your files have been successfully uploaded");

        driver.findElement(By.cssSelector("div.mainUploadSuccessLink a")).click();
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.spinner-border")))));

        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + haLongFile + "']/ancestor::div/following-sibling::div/img")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + sonDoongFile + "']/ancestor::div/following-sibling::div/img")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + boardFile + "']/ancestor::div/following-sibling::div/img")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}