package org.zhl.string;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RKAlgTest {

    @Test
    void testFind() {
        RKAlg rkAlg = new RKAlg();
        boolean b = rkAlg.find("adcdefg", "ef");
        assertTrue(b);

        boolean b1 = rkAlg.find("abcdefg", "eg");
        assertFalse(b1);
    }
}