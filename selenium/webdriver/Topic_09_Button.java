package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Danang_Reg() {
        driver.get("https://qa.danang.gov.vn/reg");

        WebElement regButton = driver.findElement(By.cssSelector("input#button2"));

        Assert.assertFalse(regButton.isEnabled());
        Assert.assertEquals(Color.fromString(regButton.getCssValue("background-color")).asHex().toUpperCase(), "#A0A0A0");

        driver.findElement(By.cssSelector("input#chinhSach")).click();

        Assert.assertTrue(regButton.isEnabled());
        Assert.assertEquals(Color.fromString(regButton.getCssValue("background-color")).asHex().toUpperCase(), "#EF5A00");
    }

    @Test
    public void TC_02_Fahasa_Login() {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        WebElement loginBtn = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        Assert.assertFalse(loginBtn.isEnabled());
        Assert.assertEquals(Color.fromString(loginBtn.getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("0908775784");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        Assert.assertTrue(loginBtn.isEnabled());
        Assert.assertEquals(Color.fromString(loginBtn.getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
    }

    @Test
    public void TC_03_Consensus_Reg() {
        driver.get("https://play.goconsensus.com/u5d5156df");

        WebElement continueBtn = driver.findElement(By.cssSelector("button[data-testid='lead form continue']"));

        Assert.assertFalse(continueBtn.isEnabled());
        Assert.assertEquals(Color.fromString(continueBtn.getCssValue("background-color")).asHex().toUpperCase(), "#673AB7");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}