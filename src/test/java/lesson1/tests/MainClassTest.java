package lesson1.tests;


import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("getLocalNumber возвращает значение 14", 14, new MainClass().getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("getClassNumber возвращает значение больше 45", new MainClass().getClassNumber() > 45);
    }

    @Test
    public void testGetClassString() {
        String actualString = new MainClass().getClassString();
        Assert.assertTrue("getClassString возвращает строку содержащую hello или Hello",
                actualString.contains("hello") || actualString.contains("Hello"));
    }
}
