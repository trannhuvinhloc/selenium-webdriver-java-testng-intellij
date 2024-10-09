package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

public class Topic_15_findElement_Command {
    WebDriver driver;
    String url = "https://www.facebook.com";

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_findElement_Not_Found() {
        driver.get(url);
        System.out.println("Start time: " + new Date().toString());
        driver.findElement(By.cssSelector("button#login"));
        System.out.println("End time: " + new Date().toString());
    }

    @Test
    public void TC_02_findElement_1_Found() {
        driver.get(url);
        System.out.println("Start time: " + new Date().toString());
        driver.findElement(By.cssSelector("button[name='login']"));
        System.out.println("End time: " + new Date().toString());

    }

    @Test
    public void TC_03_findElement_Many_Found() {
        driver.get(url);
        System.out.println("Start time: " + new Date().toString());
        driver.findElement(By.tagName("input"));
        System.out.println("End time: " + new Date().toString());
    }

    @Test
    public void TC_04_findElements_Not_Found() {
        driver.get(url);
        System.out.println("Start time: " + new Date().toString());
        System.out.println(driver.findElements(By.cssSelector("button#login")).size());
        System.out.println("End time: " + new Date().toString());
    }

    @Test
    public void TC_05_findElements_1_Found() {
        driver.get(url);
        System.out.println("Start time: " + new Date().toString());
        System.out.println(driver.findElements(By.cssSelector("button[name='login']")).size());
        System.out.println("End time: " + new Date().toString());
    }

    @Test
    public void TC_06_findElements_Many_Found() {
        driver.get(url);
        System.out.println("Start time: " + new Date().toString());
        System.out.println(driver.findElements(By.tagName("input")).size());
        System.out.println("End time: " + new Date().toString());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}