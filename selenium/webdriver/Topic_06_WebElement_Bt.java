package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebElement_Bt {
    WebDriver driver;
    String url = "https://automationfc.github.io/basic-form/index.html";

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Verify_Displayed() {
        driver.get(url);

        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Tran Loc");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
        sleepInSeconds(1);

        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
        sleepInSeconds(1);

        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Tran Loc");
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
        sleepInSeconds(1);

        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Element is displayed");
        } else {
            System.out.println("Element is not displayed");
        }
    }

    @Test
    public void TC_02_Verify_Enabled_Disabled() {
        driver.get(url);
        //Enabled
        printlnEnabled("input#mail");
        printlnEnabled("input#under_18");
        printlnEnabled("textarea#edu");
        printlnEnabled("select#job1");
        printlnEnabled("select#job2");
        printlnEnabled("input#development");
        printlnEnabled("input#slider-1");

        //Disabled
        printlnEnabled("input#disable_password");
        printlnEnabled("input#radio-disabled");
        printlnEnabled("textarea#bio");
        printlnEnabled("select#job3");
        printlnEnabled("input#check-disbaled");
        printlnEnabled("input#slider-2");
    }

    public void printlnEnabled(String css) {
        if (driver.findElement(By.cssSelector(css)).isEnabled()) {
            System.out.println(css + " element is enabled");
        } else {
            System.out.println(css + " element is disabled");
        }
    }

    @Test
    public void TC_03_Verify_Selected() {
        driver.get(url);

        printlnSelected("input#under_18");

        printlnSelected("input#java");
        printlnSelected("input#java");
    }

    public void printlnSelected(String css) {
        driver.findElement(By.cssSelector(css)).click();
        if (driver.findElement(By.cssSelector(css)).isSelected())
            System.out.println(css + " element is selected");
        else System.out.println(css + " element is not selected");
    }


    @Test
    public void TC_04_Register_Validation() {
        driver.get("https://login.mailchimp.com/signup/");

        WebElement pass = driver.findElement(By.cssSelector("input#new_password"));

        driver.findElement(By.cssSelector("input#email")).sendKeys("tranloc@gmail.com");
        pass.clear();
        driver.findElement(By.cssSelector("button#create-account-enabled")).submit();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed>span")).isDisplayed());

        driver.findElement(By.cssSelector("label[title='Show Password']")).click();
        pass.sendKeys("123456789");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']/span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed>span")).isDisplayed());

        pass.clear();
        pass.sendKeys("abcdeabcdea");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']/span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed>span")).isDisplayed());

        pass.clear();
        pass.sendKeys("ABCDEABCD");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']/span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed>span")).isDisplayed());

        pass.clear();
        pass.sendKeys("!$%#");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed>span")).isDisplayed());

        pass.clear();
        pass.sendKeys("tranloc");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed>span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed>span")).isDisplayed());

        pass.clear();
        pass.sendKeys("abcDE123@");

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed>span")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed>span")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed>span")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed>span")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']/span")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed>span")).isDisplayed());
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