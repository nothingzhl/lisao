package org.zhl.string;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BFAlgTest {

    @Test
    void testFind() {
        BFAlg bfAlg = new BFAlg();
        boolean b = bfAlg.find("adcdefg", "ef");
        assertTrue(b);

        boolean b1 = bfAlg.find("abcdefg", "eg");
        assertFalse(b1);
    }
}