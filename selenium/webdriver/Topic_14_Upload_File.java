package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_14_Upload_File {
    WebDriver driver;
    String url = "https://blueimp.github.io/jQuery-File-Upload/";

    String boardImage = "board.jpg";
    String haLongImage = "ha_long.jpg";
    String lanternsImage = "lanterns.jpg";
    String schoolAloneImage = "school_alone.jpg";
    String sonDoongImage = "son_doong.jpg";

    String boardFilePath = getFilePath(boardImage);
    String haLongFilePath = getFilePath(haLongImage);
    String lanternsFilePath = getFilePath(lanternsImage);
    String schoolAloneFilePath = getFilePath(schoolAloneImage);
    String sonDoongFilePath = getFilePath(sonDoongImage);


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_() {
        driver.get(url);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(boardFilePath + "\n" + haLongFilePath + "\n" + lanternsFilePath + "\n" + schoolAloneFilePath + "\n" + sonDoongFilePath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + boardImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + haLongImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + lanternsImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + schoolAloneImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + sonDoongImage + "']")).isDisplayed());

        clickUploadBtn();

        Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + boardImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + haLongImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + sonDoongImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + schoolAloneImage + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + lanternsImage + "']")).isDisplayed());
    }

    public String getFilePath(String fileName) {
        String projectPath = System.getProperty("user.dir");
        return projectPath + File.separator + "fileUpload" + File.separator + fileName;
    }

    public void clickUploadBtn() {
        List<WebElement> allUploadBtn = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement e : allUploadBtn) {
            e.click();
            sleepInSeconds(1);
        }
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