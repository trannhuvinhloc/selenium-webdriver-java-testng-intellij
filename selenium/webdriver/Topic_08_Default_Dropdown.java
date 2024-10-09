package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Topic_08_Default_Dropdown {
    WebDriver driver;
    String url = "https://demo.nopcommerce.com";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Dropdown_Command() {
        driver.get(url + "/register");
        Select day = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));

        day.getAllSelectedOptions();
        day.getFirstSelectedOption(); //**
        day.getOptions(); //**
        WebElement e = day.getWrappedElement();

        day.deselectAll();
        day.deselectByIndex(2);
        day.deselectByValue("abc");
        day.deselectByVisibleText("bcd");

        int i = day.hashCode();
        boolean b = day.equals(e);
        boolean mu = day.isMultiple(); //**

        day.selectByIndex(2);
        day.selectByValue("tye");
        day.selectByVisibleText("mnb"); //**
    }

    @Test
    public void TC_02_Register() {
        driver.get(url);
        String firstname = "Loc", lastname = "Tran", email = getEmail(), pass = "1234567";
        String day = "25", month = "October", year = "1999";

        driver.findElement(By.cssSelector("a.ico-register")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstname);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastname);

        Select dayOption = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
        dayOption.selectByVisibleText(day);

        Assert.assertFalse(dayOption.isMultiple());
        Assert.assertEquals(dayOption.getOptions().size(), 32);
        Assert.assertEquals(dayOption.getFirstSelectedOption().getAttribute("value"), day);

        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(pass);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(pass);

        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
        driver.findElement(By.cssSelector("a.register-continue-button")).click();
        driver.findElement(By.cssSelector("a.ico-account")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastname);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), firstname);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), firstname);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), firstname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
    }

    public String getEmail() {
        return "loctran" + new Random().nextInt(99999) + "@gmail.com";
    }

    @Test
    public void TC_03_Select_Multiple() {
        driver.get("https://automationfc.github.io/basic-form/");

        List<String> testing = List.of("Manual", "Mobile", "Security", "Perfomance");

        Select select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
        Assert.assertTrue(select.isMultiple());

        for (String value : testing)
            select.selectByVisibleText(value);

        List<WebElement> selectedOption = select.getAllSelectedOptions();
        Assert.assertEquals(selectedOption.size(), 4);

        List<String> actualValues = new ArrayList<>();

        for (WebElement option : selectedOption)
            actualValues.add(option.getText());

        Assert.assertEquals(actualValues, testing);
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