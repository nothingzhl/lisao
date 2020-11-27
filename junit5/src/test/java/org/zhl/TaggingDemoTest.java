package org.zhl;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * @author zhanghanlin
 */
@Tag("fast")
@Tag("model")
public class TaggingDemoTest {

    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

}
