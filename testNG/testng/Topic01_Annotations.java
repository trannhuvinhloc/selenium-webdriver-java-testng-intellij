package testng;

import org.testng.annotations.*;

public class Topic01_Annotations {
    @BeforeSuite(alwaysRun = true)
    public void getBeforeSuite() {
        System.out.println("Before Suite");
    }

    @AfterSuite(alwaysRun = true)
    public void getAfterSuite() {
        System.out.println("After Suite");
    }

    @BeforeTest //**
    public void getBeforeTest() {
        System.out.println("Before Test");
    }

    @AfterTest //**
    public void getAfterTest() {
        System.out.println("After Test");
    }

    @BeforeGroups
    public void getBeforeGroup() {
        System.out.println("Before Groups");
    }

    @AfterGroups
    public void getAfterGroups() {
        System.out.println("After Groups");
    }

    @BeforeClass //**
    public void getBeforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass //**
    public void getAfterClass() {
        System.out.println("After Class");
    }

    @BeforeMethod
    public void getBeforeMethod() {
        System.out.println("Before Method");
    }

    @AfterMethod
    public void getAfterMethod() {
        System.out.println("After Method");
    }

//    @Test(groups = "logout", priority = 3) //**
    public void testCase1() {
        System.out.println("Test case 1");
    }

    @Test(groups = "loginn", priority = 3)
    public void testCase2() {
        System.out.println("Test case 2");
    }

    @Test(groups = "loginn", priority = 1, enabled = false) //**
    public void testCase4() {
        System.out.println("Test case 4");
    }

    @Test(groups = "loginn", priority = 2)
    public void testCase5() {
        System.out.println("Test case 5");
    }

    @Test(groups = "logout", priority = 1, description = "Test case 3 for log out")
    public void testCase3() {
        System.out.println("Test case 3 - logout");
    }


}
