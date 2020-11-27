package org.zhl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * @author zhanghanlin
 */
public class TestInterfaceDemoTest implements TestLifecycleLoggerTest{

    @Test
    @RepeatedTest(value = 12,name = "{displayName} {currentRepetition}/{totalRepetitions}")
    void isEqualValue() {
        assertEquals(1, 1, "is always equal");
    }

}
