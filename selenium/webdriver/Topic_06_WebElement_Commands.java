package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_06_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        WebElement wE = driver.findElement(By.id(""));
        List<WebElement> wEList = driver.findElements(By.id(""));

        //WebElement
        wE.sendKeys("text"); //**
        wE.click(); //**
        wE.getText(); //**
        wE.clear(); //**

        wE.findElement(By.tagName(""));
        wE.findElements(By.xpath(""));

        wE.getAccessibleName();
        wE.getAriaRole();
        wE.getText();
        wE.getAttribute("id"); //**
        wE.getCssValue("type"); //**
        wE.getDomAttribute("value");
        wE.getDomProperty("don");

        int y = wE.getLocation().y;
        int x = wE.getLocation().getX();
        boolean equals = wE.getLocation().equals(wE);
        int hash = wE.getLocation().hashCode();
        wE.getLocation().moveBy(5, 8);
        String str = wE.getLocation().toString();

        int height = wE.getRect().height;
        int xRect = wE.getRect().x;
        boolean equals1 = wE.getRect().equals(wE);

        String diStr = wE.getRect().getDimension().toString();
        int hei = wE.getRect().getDimension().height;
        boolean e = wE.getRect().getDimension().equals(wE);
        int w = wE.getRect().getDimension().getWidth();
        int code = wE.getRect().getDimension().hashCode();


        int width = wE.getRect().getWidth();
        int y1 = wE.getRect().getY();
        wE.getRect().getPoint();
        int hashCode = wE.getRect().hashCode();

        wE.getShadowRoot().findElement(By.id(""));
        wE.getShadowRoot().findElements(By.cssSelector(""));

        String sizeStr = wE.getSize().toString();
        wE.getTagName();

        wE.getScreenshotAs(OutputType.BASE64); //*
        wE.getScreenshotAs(OutputType.FILE);
        wE.getScreenshotAs(OutputType.BYTES);

        wE.isDisplayed(); //**
        wE.isEnabled(); //**
        wE.isSelected(); //**

        wE.submit();

        //WebElement List
        int size = wEList.size();
        List<WebElement> eList = driver.findElements(By.id(""));
        wEList.add(wE);
        wEList.clear();
        wEList.addAll(4, eList);
        boolean contains = wEList.contains(wE);
        boolean b = wEList.containsAll(eList);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}