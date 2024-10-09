package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver(); //**
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); //**
        driver.manage().window().maximize(); //**
    }

    @Test
    public void TC_01_() {
        driver.findElement(By.id("id1")); //**
        driver.findElements(By.id("id1")); //**

        driver.close(); //*
        driver.get("auto.com"); //**
        driver.quit(); //**

        driver.getCurrentUrl(); //*
        driver.getPageSource();
        driver.getTitle();
        driver.getWindowHandle(); //*
        driver.getWindowHandles(); //*

        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("cook");
        driver.manage().getCookieNamed("cook");
        driver.manage().getCookies(); //*
        driver.manage().addCookie(new Cookie("a", "b"));
        driver.manage().deleteCookie(new Cookie("cook", "kei"));

        driver.manage().logs().get(LogType.BROWSER); //* Beta
        driver.manage().logs().getAvailableLogTypes();

        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(2));

        driver.manage().window().fullscreen();
        driver.manage().window().getPosition();
        driver.manage().window().getSize();
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.manage().window().setPosition(new Point(2, 3));
        driver.manage().window().setSize(new Dimension(100, 222));

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.navigate().to("loc.com");

        driver.switchTo().alert().accept(); //*
        driver.switchTo().alert().dismiss(); //*
        driver.switchTo().alert().getText(); //*
        driver.switchTo().alert().sendKeys("text"); //*

        driver.switchTo().activeElement().sendKeys("abg");

        driver.switchTo().defaultContent();

        driver.switchTo().frame(2); //*
        driver.switchTo().frame("name"); //*
        driver.switchTo().frame(driver.findElement(By.id(""))); //*
        driver.switchTo().parentFrame();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.switchTo().window(driver.getWindowHandle()); //*
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}