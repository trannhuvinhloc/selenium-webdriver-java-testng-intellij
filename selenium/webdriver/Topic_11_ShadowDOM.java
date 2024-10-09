package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_ShadowDOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Shadow() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        WebElement shadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();

        Assert.assertEquals(shadowRoot.findElement(By.cssSelector("span.info")).getText(), "some text");
        Assert.assertFalse(shadowRoot.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        SearchContext shadowRootChild = shadowRoot.findElement(By.cssSelector("div#nested_shadow_host")).getShadowRoot();

        Assert.assertEquals(shadowRootChild.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");
    }

    @Test
    public void TC_02_Shopee() {
        driver.get("https://shopee.vn");

        WebElement shadowHostPopup = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRoot = shadowHostPopup.getShadowRoot();

        if (!shadowRoot.findElements(By.cssSelector("div.home-popup__content")).isEmpty() && shadowRoot.findElement(By.cssSelector("div.home-popup__content")).isDisplayed()) {
            shadowRoot.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSeconds(1);
        }

        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iPhone 11");
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
    }

    @Test
    public void TC_03_() {

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