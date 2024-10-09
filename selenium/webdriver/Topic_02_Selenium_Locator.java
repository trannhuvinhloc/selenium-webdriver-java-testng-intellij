package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");

    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("Vinh Loc");
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("LastName")).sendKeys("Tran");
    }

    @Test
    public void TC_04_LinkText() {
        driver.findElement(By.linkText("Shopping cart")).click();
    }

    @Test
    public void TC_05_Partial_LinkText() {
        driver.findElement(By.partialLinkText("viewed products")).click();

    }

    @Test
    public void TC_06_Tagname() {
        System.out.println(driver.findElements(By.tagName("form")).size());
    }

    @Test
    public void TC_07_CSS_Selector() {
        driver.findElement(By.cssSelector("input[id='Email']"))
                .sendKeys("loc@gmail.com");
    }

    @Test
    public void TC_08_XPath() {
        driver.findElement(By.xpath("//input[@id='Newsletter']")).click();
    }

    @Test
    public void TC_09_XPath_Function() {
        driver.findElement(By.xpath("//button[text()='Register']")).click();
    }

    @Test
    public void TC_10_RelativeLocator_v4() {
        driver.findElement(RelativeLocator.with(By.tagName("input"))
                        .below(By.id("Password"))
                        .above(driver.findElement(By.id("register-button"))))
                .sendKeys("passne");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}