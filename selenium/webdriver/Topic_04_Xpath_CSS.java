package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Xpath_CSS {
    WebDriver driver;
    String urlRegister = "https://alada.vn/tai-khoan/dang-ky.html";

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Empty() {
        driver.get(urlRegister);

        driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get(urlRegister);

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Tran Loc");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("233@344@4");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("233@344@4");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("01234567890");

        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void TC_03_Incorrect_Confirm_Email() {
        driver.get(urlRegister);

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Tran Loc");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("tranloc@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("tranlc@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("01234567890");

        driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void TC_04_Incorrect_Password() {
        driver.get(urlRegister);

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Tran Loc");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("tranloc@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("tranloc@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("01234567890");

        driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void TC_05_Incorrect_Confirm_Password() {
        driver.get(urlRegister);

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Tran Loc");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("tranloc@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("tranloc@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("1234567");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("01234567890");

        driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("label#txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

    }

    @Test
    public void TC_06_Invalid_Number() {
        driver.get(urlRegister);

        WebElement phone = driver.findElement(By.cssSelector("input#txtPhone"));

        driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Tran Loc");
        driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("tranloc@gmail.com");
        driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("tranloc@gmail.com");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("1234567");
        driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("1234567");
        phone.sendKeys("090878766329");

        driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

        WebElement phoneError = driver.findElement(By.cssSelector("label#txtPhone-error"));
        Assert.assertEquals(phoneError.getText(), "Số điện thoại phải từ 10-11 số.");

        phone.clear();
        phone.sendKeys("0906");


        Assert.assertEquals(phoneError.getText(), "Số điện thoại phải từ 10-11 số.");

        phone.clear();
        phone.sendKeys("123");

        Assert.assertEquals(phoneError.getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}