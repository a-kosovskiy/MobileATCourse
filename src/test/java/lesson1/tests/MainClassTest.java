package lesson1.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        Assertions.assertEquals(14, new MainClass().getLocalNumber(),
                "getLocalNumber возвращает значение 14");
    }

    @Test
    public void testGetClassNumber() {
        Assertions.assertTrue(new MainClass().getClassNumber() > 45,
                "getClassNumber возвращает значение больше 45");
    }

    @Test
    public void testGetClassString() {
        String actualString = new MainClass().getClassString();
        Assertions.assertTrue(actualString.contains("hello") || actualString.contains("Hello"),
                "getClassString возвращает строку содержащую hello или Hello");
    }
}
