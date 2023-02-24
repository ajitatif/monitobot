package com.monitobot.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
class StringHelperTest {

    @Test
    void shouldReturnEmptyTrimmedString() {
        assertTrue(StringHelper.getTrimmedString(null).isEmpty());
        assertTrue(StringHelper.getTrimmedString("").isEmpty());
        assertTrue(StringHelper.getTrimmedString(" ").isEmpty());
    }

    @Test
    void shouldReturnTrimmedString() {
        assertEquals("this  _", StringHelper.getTrimmedString(" this  _ ").get());
    }
}