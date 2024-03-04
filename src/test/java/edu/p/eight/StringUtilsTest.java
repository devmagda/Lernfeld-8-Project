// StringUtilsTest.java
package edu.p.eight;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void testAdd() {
        assertEquals("ABCDEF", StringUtils.add("ABC", "DEF"));
        assertEquals("34", StringUtils.add(3 + "", 4 + ""));
    }

    @Test
    public void testisEmpty() {
        assertEquals(true, StringUtils.isEmpty(""));
        assertEquals(true, StringUtils.isEmpty(null));
        assertEquals(false, StringUtils.isEmpty("Hello, World!"));
    }
}
