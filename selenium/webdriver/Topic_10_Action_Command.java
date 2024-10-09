package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Action_Command {
    WebDriver driver;
    String url = "";
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Command() {
        driver.get(url);
        WebElement we = driver.findElement(By.cssSelector("h1#id"));

        action.click().clickAndHold().build().perform();
        action.click(we);

        action.clickAndHold(we);

        action.contextClick();
        action.contextClick(we);

        action.doubleClick();
        action.doubleClick(we);

        action.dragAndDrop(we, we);
        action.dragAndDropBy(we, 5, 99);

        action.getActiveKeyboard();
        action.getActivePointer();
        action.getActiveWheel();
        action.getSequences();

        action.keyDown(Keys.CONTROL);
        action.keyDown(we, Keys.COMMAND);

        action.keyUp(Keys.ALT);
        action.keyUp(we, Keys.F5);

        action.moveByOffset(4, 8);
        action.moveToElement(we);
        action.moveToElement(we, 9, 11);
        action.moveToLocation(77, 33);

        action.pause(3000);
        action.pause(Duration.ofSeconds(6));

        action.release();
        action.release(we);

        action.scrollByAmount(8, 4);
        action.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(we), 10, 100);
        action.scrollToElement(we);

        action.sendKeys("test");
        action.sendKeys(Keys.ENTER);
        action.sendKeys(we, Keys.TAB);

        action.setActiveKeyboard("abc");
        action.setActivePointer(PointerInput.Kind.TOUCH, "screen");
        action.setActiveWheel("nbc");

        action.tick();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}