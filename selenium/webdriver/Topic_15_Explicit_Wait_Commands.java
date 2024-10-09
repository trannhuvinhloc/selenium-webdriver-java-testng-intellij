package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Explicit_Wait_Commands {
    WebDriver driver;
    WebDriverWait explicitWait, webDriverWait;
    By demoBy = By.cssSelector("");

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(300));
    }

    @Test
    public void TC_01_WebDriverWait_Command() {
        driver.get("https://www.facebook.com");
        WebElement element = driver.findElement(demoBy);
        List<WebElement> elementList = driver.findElements(demoBy);

        Boolean bo = explicitWait.equals(element);
        int i = explicitWait.hashCode();
        System.out.println(explicitWait.toString());

        explicitWait.pollingEvery(Duration.ofMillis(100));
        explicitWait.withMessage("Test");
        explicitWait.withTimeout(Duration.ofSeconds(1));

        //**
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(demoBy));
        explicitWait.until(ExpectedConditions.visibilityOf(element));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(demoBy));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(elementList));

        //**
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(demoBy));
        explicitWait.until(ExpectedConditions.invisibilityOf(element));
        explicitWait.until(ExpectedConditions.invisibilityOfElementWithText(demoBy, "Text"));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elementList));

        //**
        explicitWait.until(ExpectedConditions.elementToBeClickable(demoBy));
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));

        //*
        explicitWait.until(ExpectedConditions.alertIsPresent());

        explicitWait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(demoBy)));
        explicitWait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(demoBy)));
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(demoBy)));

        explicitWait.until(ExpectedConditions.attributeContains(demoBy, "name", "val"));
        explicitWait.until(ExpectedConditions.attributeContains(element, "name", "val"));
        explicitWait.until(ExpectedConditions.attributeToBe(demoBy, "Text", "val"));
        explicitWait.until(ExpectedConditions.attributeToBe(element, "name", "val"));
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(element, "name"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(element, "name", "val"));
        explicitWait.until(ExpectedConditions.domPropertyToBe(element, "Text", "val"));

        explicitWait.until(ExpectedConditions.elementToBeSelected(demoBy));
        explicitWait.until(ExpectedConditions.elementToBeSelected(element));
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(demoBy, false));

        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(demoBy));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frame1"));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(demoBy)));

        explicitWait.until(ExpectedConditions.titleIs("text"));
        explicitWait.until(ExpectedConditions.titleContains("test"));

        //explicitWait.until(ExpectedConditions.textMatches(demoBy, Pattern.compile()))

        explicitWait.until(ExpectedConditions.textToBe(demoBy, "test"));
        explicitWait.until(ExpectedConditions.textToBePresentInElement(element, "tring"));
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(demoBy, "abc"));
        explicitWait.until(ExpectedConditions.textToBePresentInElementValue(demoBy, "frame1"));
        explicitWait.until(ExpectedConditions.textToBePresentInElementValue(element, "text"));

        explicitWait.until(ExpectedConditions.urlContains("tring"));
        explicitWait.until(ExpectedConditions.urlMatches("abc"));
        explicitWait.until(ExpectedConditions.urlToBe("frame1"));

        //*
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(demoBy));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(demoBy));

        explicitWait.until(ExpectedConditions.stalenessOf(element));

        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("demoBy"));
        explicitWait.until(ExpectedConditions.jsReturnsValue("demoBy"));

        explicitWait.until(ExpectedConditions.numberOfElementsToBe(demoBy, 5));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(demoBy, 1));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(demoBy, 4));

        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(5));
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