package com.uic.oole.utility;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RandomIntGen.class)
public class RandomIntGenTest {

    @Before
    public void setUp() throws Exception{
        PowerMockito.mockStatic(RandomIntGen.class);
        PowerMockito.when(RandomIntGen.randomIntGenerator()).thenReturn(10);
    }

    /**
     * Test Method: RandomIntGenerator
     * the randomIntGenerator method is called and asserts whether the return
     * value matches with the input value or not
     */
    @Test
    public void testRandomIntGenerator(){
        Assert.assertEquals(RandomIntGen.randomIntGenerator(), Integer.valueOf(10));
    }
}
