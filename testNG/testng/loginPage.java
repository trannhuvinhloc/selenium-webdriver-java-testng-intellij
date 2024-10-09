package testng;

import org.testng.annotations.Test;

public class loginPage {
    @Test(groups = {"loginn"})
    public void testCaseLogin() {
        System.out.println("Test case Login");
    }

    @Test(groups = {"loginn","win"})
    public void testCaseLogin2() {
        System.out.println("Test case Login 2");
    }

    @Test(groups = {"logout","mac"})
    public void testCaseLogout() {
        System.out.println("Test case Logout");
    }
}
