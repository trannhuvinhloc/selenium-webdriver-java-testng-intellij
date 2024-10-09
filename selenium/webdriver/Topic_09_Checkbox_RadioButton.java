package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_09_Checkbox_RadioButton {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_KendoUI() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        sleepInSeconds(5); // accept cookies

        By checkboxHeat = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::span/input");
        By checkboxRear = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");

        selectCheckboxRadio(checkboxHeat);
        deselectCheckbox(checkboxRear);

        Assert.assertTrue(driver.findElement(checkboxHeat).isSelected());
        Assert.assertFalse(driver.findElement(checkboxRear).isSelected());

        //Radio button
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By radioPetrol = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input");
        By radioDiesel = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");

        Assert.assertTrue(driver.findElement(radioPetrol).isSelected());

        selectCheckboxRadio(radioDiesel);

        Assert.assertFalse(driver.findElement(radioPetrol).isSelected());
        Assert.assertTrue(driver.findElement(radioDiesel).isSelected());
    }

    public void selectCheckboxRadio(By locator) {
        if (!driver.findElement(locator).isSelected()) driver.findElement(locator).click();
    }

    public void deselectCheckbox(By locator) {
        if (driver.findElement(locator).isSelected()) driver.findElement(locator).click();
    }

    @Test
    public void TC_02_Material_Angular() {
        driver.get("https://material.angular.io/components/radio/examples");
        sleepInSeconds(5);
        By radio = By.cssSelector("input[value='Autumn']");

        selectCheckboxRadio(radio);

        Assert.assertTrue(driver.findElement(radio).isSelected());
        Assert.assertEquals(driver.findElement(By.cssSelector("radio-ng-model-example>div")).getText(), "Your favorite season is: Autumn");

        //Checkbox
        driver.get("https://material.angular.io/components/checkbox/examples");

        By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");
        By imCheckbox = By.xpath("//label[text()=\" I'm a checkbox \"]/preceding-sibling::div/input");

        Assert.assertFalse(driver.findElement(imCheckbox).isSelected());

        selectCheckboxRadio(indeterminateCheckbox);

        Assert.assertFalse(driver.findElement(imCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
    }

    @Test
    public void TC_03_Select_All() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("input.form-checkbox"));

        // check all items
        for (WebElement item : allCheckboxs) {
            if (!item.isSelected()) item.click();
            else Assert.assertFalse(item.isSelected());
            Assert.assertTrue(item.isSelected());
        }

        //Check 1 item
        driver.navigate().refresh();
        allCheckboxs = driver.findElements(By.cssSelector("input.form-checkbox"));

        for (WebElement item : allCheckboxs) {
            if (!item.isSelected() && item.getAttribute("value").equals("Heart Attack")) {
                item.click();
                break;
            }
        }

        for (WebElement item : allCheckboxs) {
            if (item.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(item.isSelected());
            } else Assert.assertFalse(item.isSelected());
        }

    }

    @Test
    public void TC_04_Ubuntu() {
        driver.get("https://login.ubuntu.com");

        By radio = By.cssSelector("input#id_new_user");
        By checkbox = By.cssSelector("input#id_accept_tos");

        forceSelectCheckboxRadio(radio);
        forceSelectCheckboxRadio(checkbox);

        Assert.assertTrue(driver.findElement(radio).isSelected());
        Assert.assertTrue(driver.findElement(checkbox).isSelected());
    }

    public void forceSelectCheckboxRadio(By locator) {
        if (!driver.findElement(locator).isSelected())
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(locator));
    }

    @Test
    public void TC_05_Google_Form() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By radio = By.cssSelector("div[data-value='Hà Nội']");
        List<WebElement> checkboxList = driver.findElements(By.cssSelector("div[data-answer-value^='Quảng']"));

        System.out.println("Numer list: " + checkboxList.size());
        if (driver.findElement(radio).getAttribute("aria-checked").equals("false")) driver.findElement(radio).click();
        for (WebElement item : checkboxList) if (item.getAttribute("aria-checked").equals("false")) item.click();

        Assert.assertEquals(driver.findElement(radio).getAttribute("aria-checked"), "true");

        for (WebElement item : checkboxList) Assert.assertEquals(item.getAttribute("aria-checked"), "true");
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