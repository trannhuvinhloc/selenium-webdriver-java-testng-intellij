package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_08_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Jqueryui() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        selectCustomDropdown("span#speed-button", "ul#speed-menu div", "Medium");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");

        selectCustomDropdown("span#files-button", "ul#files-menu div", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "ui.jQuery.js");

        selectCustomDropdown("span#number-button", "ul#number-menu div", "11");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "11");

        selectCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Prof.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");
    }

    public void selectCustomDropdown(String parentCss, String childItemCss, String itemValue) {
        driver.findElement(By.cssSelector(parentCss)).click();

        for (WebElement item : explicitWait.until(ExpectedConditions
                                            .presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))))
            if (item.getText().equals(itemValue)) {
                item.click();
                break;
            }
    }

    @Test
    public void TC_02_ReactJs() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectCustomDropdown("div[role='listbox']", "div.item", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Stevie Feliciano");

        selectCustomDropdown("div[role='listbox']", "div.item", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Matt");
    }

    @Test
    public void TC_03_VueJs() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectCustomDropdown("div.btn-group", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

        selectCustomDropdown("div.btn-group", "ul.dropdown-menu a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectEditableDropdown("input.search", "span.text", "Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Argentina");

        selectEditableDropdown("input.search", "span.text", "Benin");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Benin");
    }

    public void selectEditableDropdown(String parentCss, String childCss, String itemVal) {
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemVal);

        for (WebElement item : explicitWait.until(ExpectedConditions
                                            .presenceOfAllElementsLocatedBy(By.cssSelector(childCss))))
            if (item.getText().equals(itemVal)) {
                item.click();
                break;
            }
    }

    @Test
    public void TC_03_Multiple_Select() {
        driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

        String[] s = {"a,b"};
        selectMultiItemInDropdown("//li[@class='selected']//input", "v", s);

        Assert.assertTrue(isItemSelected(s));
    }

    public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
        // 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
        driver.findElement(By.xpath(parentXpath)).click();

        // 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công

        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

        // Duyệt qua hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
        for (WebElement childElement : allItems) {
            // "January", "April", "July"
            for (String item : expectedValueItem) {
                if (childElement.getText().equals(item)) {
                    // 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)

                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
                    sleepInSeconds(1);

                    // 4: click vào item cần chọn
                    childElement.click();
                    sleepInSeconds(1);

                    List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
                    System.out.println("Item selected = " + itemSelected.size());
                    if (expectedValueItem.length == itemSelected.size()) {
                        break;
                    }
                }
            }
        }
    }

    public boolean isItemSelected(String[] months) {
        List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
        int numberItemSelected = itemSelected.size();

        String allItemSelectedText = driver.findElement(By.xpath("(//span[text()='January']/ancestor::div[contains(@class,'ms-parent multiple-select')]/button/span")).getText();
        System.out.println("Text đã chọn = " + allItemSelectedText);

        if (numberItemSelected <= 3 && numberItemSelected > 0) {
            boolean status = true;
            for (String item : months) {
                if (!allItemSelectedText.contains(item)) {
                    status = false;
                    return status;
                }
            }
            return status;
        } else if (numberItemSelected >= 12) {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
        } else if (numberItemSelected > 3) {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
        } else {
            return false;
        }
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