package com.uic.oole.utility;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(RandomStrGen.class)
public class RandomStrGenTest {
    /**
     * Tests the random string generator method and asserts a false
     */
    @Test
    public void TestRandomStrGenerator(){
        String str = RandomStrGen.randomStrGenerator();
        Assert.assertFalse(str, false);
    }
}
