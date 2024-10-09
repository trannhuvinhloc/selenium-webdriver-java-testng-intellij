package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic02_Assert {
    String abg, testY = "yehs";

    @Test
    public void TC1() {
        Assert.assertTrue(true);
        Assert.assertFalse(false);
        Assert.assertTrue(true, "test fail");

        Assert.assertEquals("", "abcs");
        Assert.assertEquals(4, 9);
        Assert.assertEquals("", "login", "test fail");

        Assert.assertNotEquals(9.7, 10.13, "fail ne");

        Assert.assertNull(abg, "test fail ne");
        Assert.assertNotNull(testY);
    }
}
