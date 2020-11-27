package org.zhl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

/**
 * @author zhanghanlin
 * 假设测试
 */
@Log4j2
public class AssumptionsDemoTest {

    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        log.info("ci is running");
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        log.info("dev is running");
    }

    @Test
    void testEachEnv() {
        assumeTrue(true);
        assertEquals(2,2);
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> assertEquals(2, 2));
        assertEquals("a string", "a string");
    }

}
