package webdriver;

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

public class Topic_11_Popup {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Not_In_DOM() {
        driver.get("https://ngoaingu24h.vn/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        By popupBy = By.xpath("//h2[text()='Đăng nhập']/parent::div");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertTrue(driver.findElement(popupBy).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("loctran");
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("loctran");
        driver.findElement(By.cssSelector("form button.dialog-button")).click();

        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#notistack-snackbar")))
                .getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        sleepInSeconds(2);
        driver.findElement(By.cssSelector("button.close-btn")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(popupBy).size(), 0);
    }

    @Test
    public void TC_02_Fixed_In_DOM_Kyna() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        Assert.assertTrue(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("loc@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Fixed_Not_In_DOM() {
        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        By registerPopupBy = By.cssSelector("div[role='dialog']>div");

        Assert.assertTrue(driver.findElement(registerPopupBy).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("button.btn-close")).click();

        Assert.assertEquals(driver.findElements(registerPopupBy).size(), 0);
    }

    @Test
    public void TC_04_Fixed_Not_In_DOM_Facebook() {
        driver.get("https://www.facebook.com");

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        By popupBy = By.xpath("//div[text()='Sign Up']/ancestor::div[@class='_8ien']");

        Assert.assertTrue(driver.findElement(popupBy).isDisplayed());

        driver.findElement(By.cssSelector("button[name='websubmit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.uiContextualLayer div[id]")).getText(), "What's your name?");
        sleepInSeconds(1);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(1);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(popupBy).size(), 0);
    }

    @Test
    public void TC_05_Random_In_DOM() {
        driver.get("https://vnk.edu.vn/");

        WebElement popupAd = driver.findElement(By.cssSelector("div.popmake"));
        if (popupAd.isDisplayed()) {
            driver.findElement(By.cssSelector("button.popmake-close")).click();
            sleepInSeconds(1);
        }
        Assert.assertFalse(popupAd.isDisplayed());

        driver.findElement(By.xpath("//a[text()='Lịch khai giảng']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div>h1")).getText(), "Lịch Khai Giảng Tháng 08");
    }

    @Test
    public void TC_06_Random_Not_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");
        //  By ad = By.xpath("//div[contains(@class,'lepopup-popup-container')]/div[contains(@class,'lepopup-form ')][1]");

//        if (!driver.findElements(ad).isEmpty() && driver.findElement(ad).isDisplayed()) {
//            driver.findElement(By.cssSelector("")).click();
//            sleepInSeconds(1);
//        }

    }

    @Test
    public void TC_07_Random_In_DOM_Dehieu() {
        driver.get("https://dehieu.vn/");

        WebElement popup = driver.findElement(By.cssSelector("div.modal-content"));
        if (popup.isDisplayed()) {
            driver.findElement(By.cssSelector("button.close")).click();
            sleepInSeconds(1);
        }

        Assert.assertFalse(popup.isDisplayed());
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