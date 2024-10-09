package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_10_Action_Interaction {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Myntra() {
        driver.get("https://www.myntra.com");

        action.moveToElement(driver.findElement(By.cssSelector("a[data-group='kids']"))).perform();
        driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.breadcrumbs-item>span")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Hover_Fahasa() {
        driver.get("https://www.fahasa.com");

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).moveToElement(driver.findElement(By.cssSelector("a[title='VPP - Dụng Cụ Học Sinh']"))).perform();
        driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Màu Vẽ']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "MÀU VẼ");
    }

    @Test
    public void TC_04_ClickandHold_Multiple() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNum = driver.findElements(By.cssSelector("ol>li"));

        Assert.assertEquals(allNum.size(), 20);

        action.clickAndHold(allNum.get(0)).moveToElement(allNum.get(9)).release().perform();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElements(By.cssSelector("ol>li.ui-selected")).size(), 6);
    }

    @Test
    public void TC_05_ClickandHold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNum = driver.findElements(By.cssSelector("ol>li"));

        Assert.assertEquals(allNum.size(), 20);

        action.keyDown(Keys.CONTROL).click(allNum.get(1)).click(allNum.get(6)).click(allNum.get(10)).click(allNum.get(12)).click(allNum.get(15)).click(allNum.get(18)).release().perform();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElements(By.cssSelector("ol>li.ui-selected")).size(), 6);
    }

    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_07_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        Assert.assertFalse(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());

        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());

        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        driver.findElement(By.cssSelector("li.context-menu-visible.context-menu-hover.context-menu-icon-quit")).click();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "clicked: quit");

        alert.accept();
        sleepInSeconds(1);

        Assert.assertFalse(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());
    }

    @Test
    public void TC_08_DragAndDrop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable")), circle = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(smallCircle, circle).perform();
        sleepInSeconds(1);
        Assert.assertEquals(circle.getText(), "You did great!");
    }

    @Test
    public void TC_09_DragAndDrop_HTML5() {
        driver.get("https://automationfc.github.io/drag-drop-html5");

        WebElement squareA = driver.findElement(By.cssSelector("div#column-a")), squareB = driver.findElement(By.cssSelector("div#column-b"));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");

        action.dragAndDrop(squareA, squareB).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
    }

    @Test
    public void TC_10_Scroll() {
        driver.get("http://live.techpanda.org/index.php/about-magento-demo-store/");

        WebElement newsInput = driver.findElement(By.cssSelector("input#newsletter")),
                searchnput = driver.findElement(By.cssSelector("input#search"));

        action.scrollToElement(newsInput).sendKeys(newsInput, "abc").perform();
        sleepInSeconds(1);
        action.scrollToElement(searchnput).sendKeys(searchnput, "abc").perform();
        sleepInSeconds(1);
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