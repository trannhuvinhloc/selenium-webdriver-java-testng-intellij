package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_07_Textbox_TextArea {
    WebDriver driver;

    String url = "http://live.techpanda.org";
    String email;
    String pass = "1234567";
    String firstName = "Loc";
    String lastName = "Tran";
    String fullName = firstName + " " + lastName;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Register_Login() {
        driver.get(url);
        email = random_email();

        //Go to Register page

        clickElement(true, "div.footer a[title='My Account']");
        clickElement(true, "a[title='Create an Account']");


        registerFunc(email);

        //Verify register success
        Assert.assertEquals(driver.findElement(By.cssSelector("p.hello>strong")).getText(), "Hello, " + fullName + "!");
        Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(email));

        //Go to product and review
        clickElement(false, "//a[text()='Mobile']");
        clickElement(true, "h2.product-name>a[title='Samsung Galaxy']");
        clickElement(false, "//a[text()='Add Your Review']");

        clickElement(true, "input[id='Quality 1_5'");
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("using it");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("no comment");
        Assert.assertEquals(driver.findElement(By.cssSelector("input#nickname_field")).getAttribute("value"), firstName);

        clickElement(true, "button[title='Submit Review']");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");

        //Log out
        logOut();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.col-main>p")).getText(), "You have logged out and will be redirected to our homepage in 5 seconds.");
        sleepInSeconds(5);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/");

        //Log in
        clickElement(true, "div.footer a[title='My Account']");
        logIn(email);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.hello>strong")).getText(), "Hello, " + fullName + "!");
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(fullName));
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText().contains(email));

        //Go to Account Information and verify data
        clickElement(false, "//a[text()='Account Information']");
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), email);
    }

    public void clickElement(Boolean css, String locator) {
        if (css)
            driver.findElement(By.cssSelector(locator)).click();
        else driver.findElement(By.xpath(locator)).click();
    }

    public void logOut() {
        driver.findElement(By.cssSelector("a[data-target-element='#header-account']")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
    }

    public void registerFunc(String regEmail) {
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(regEmail);
        driver.findElement(By.cssSelector("input#password")).sendKeys(pass);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
    }

    public void logIn(String loginEmail) {
        driver.findElement(By.cssSelector("input#email")).sendKeys(loginEmail);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[title='Login']")).click();
    }

    public String random_email() {
        return "loctran" + new Random().nextInt(99999) + "@gmail.com";
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