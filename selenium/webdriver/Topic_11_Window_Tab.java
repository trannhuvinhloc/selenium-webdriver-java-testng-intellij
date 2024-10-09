package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_11_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Two_Tabs() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentID = driver.getWindowHandle();
        String parentTitle = driver.getTitle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        switchToOtherWindow(parentID);
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Window handles");
        sleepInSeconds(1);

        driver.switchTo().window(parentID);
        sleepInSeconds(1);

        Assert.assertEquals(driver.getTitle(), parentTitle);

        closeAllOthersWindow();
    }

    public void switchToOtherWindow(String currentWindow) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String id : allWindows)
            if (!id.equals(currentWindow)) {
                driver.switchTo().window(id);
                sleepInSeconds(1);
                break;
            }
    }

    public void closeAllOthersWindow() {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String id : allWindows) {
            driver.switchTo().window(id);
            if (!id.equals(currentWindow)) {
                driver.close();
                sleepInSeconds(1);
            }
        }
    }

    @Test
    public void TC_01_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentID = driver.getWindowHandle();
        String parentTitle = driver.getTitle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        switchWindowByTitle("Google");
        driver.switchTo().window(parentID);
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        switchWindowByTitle("Facebook – log in or sign up");
        switchWindowByTitle(parentTitle);
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();

        switchWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        driver.switchTo().window(parentID);
        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();

        switchWindowByTitle("Lazada - Mua Sắm Hàng Chất Giá Tốt Online");
        switchWindowByTitle(parentTitle);

        Assert.assertEquals(driver.getTitle(), parentTitle);

        closeAllOthersWindow();
    }

    public void switchWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            driver.switchTo().window(id);
            sleepInSeconds(1);
            if (driver.getTitle().equals(title)) break;
        }
    }

    @Test
    public void TC_02_Window() {
        driver.get("http://live.techpanda.org/");

        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");
        driver.findElement(By.cssSelector("button[title='Compare']")).click();

        switchToOtherWindow(parentID);

        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "COMPARE PRODUCTS");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();

        driver.switchTo().window(parentID);
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        driver.switchTo().alert().accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_03_Cambridge() {
        driver.get("https://dictionary.cambridge.org");

    }

    @Test
    public void TC_04_Harvard() {
        driver.get("https://courses.dce.harvard.edu/");

    }

    @Test
    public void TC_05_Command() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.switchTo().window("abc");
        driver.switchTo().defaultContent();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://tiki.vn");

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://facebook.com");
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