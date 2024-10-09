package testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners({listeners.ScreenshotListener.class})
public class Topic16_Dependences {

    @Test
    public void TC01_createAccount() {
        System.out.println("created");
    }

    @Test(dependsOnMethods = "TC01_createAccount")
    public void TC02_viewAccount() {
        System.out.println("view");
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC02_viewAccount")
    public void TC03_update() {
        System.out.println("update");
    }

    @Test(dependsOnMethods = "TC01_createAccount")
    public void TC04_delete() {
        System.out.println("Del");
    }

    @Test
    public void TC05_search() {
        System.out.println("search");
    }

}
