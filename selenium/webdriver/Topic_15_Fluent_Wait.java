package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_15_Fluent_Wait {
    WebDriver driver;

    FluentWait<WebDriver> webDriverFluentWait;
    FluentWait<WebElement> elementFluentWait;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Commands() {
        driver.get("url");
        WebElement element = driver.findElement(By.cssSelector(""));
        webDriverFluentWait = new FluentWait<>(driver);
        elementFluentWait = new FluentWait<>(element);

        webDriverFluentWait.withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .withMessage("Fail Fluent")
                .until(ExpectedConditions.visibilityOf(element));

        webDriverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return true;
            }
        });

        elementFluentWait.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(TimeoutException.class)
                .ignoring(NoSuchElementException.class)
                .until(e -> {
                    e.click();
                    return true;
                });
    }

    @Test
    public void TC_02_Fluent() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        elementFluentWait = new FluentWait<>(driver.findElement(By.cssSelector("body>div")))
                .withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(100));

        //New Function<>()
//        elementFluentWait.until(new Function<WebElement, Boolean>() {
//            @Override
//            public Boolean apply(WebElement webElement) {
//                return webElement.getText().endsWith(":00");
//            }
//        });

        //Lambda
        elementFluentWait.until(e -> e.getText().endsWith(":00"));
    }

    @Test
    public void TC_03_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div>button")).click();

        webDriverFluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(600))
                .ignoring(NoSuchElementException.class);

        String text = webDriverFluentWait.until(d -> {
            WebElement e = d.findElement(By.cssSelector("div>h4"));
            return e.getText();
        });

        Assert.assertEquals(text, "Hello World!");
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