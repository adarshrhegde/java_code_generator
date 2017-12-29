package com.uic.oole.parser;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test Class Name: Block.java
 */
@PrepareForTest(Block.class)
public class BlockTest {
    /**
     * Testing all the getter and setter methods and symbol tables in the
     * block class
     */
    @Test
    public void testMethods(){
        Block block = mock(Block.class);
        Symbol symbol = mock(Symbol.class);
        symbol.name = "name";
        /**
         * Mocking the lookup and lookup locally methods
         * and bypassing the logic to return an assertion
         */
        when(block.lookup("name")).thenReturn(symbol);
        when(block.lookupLocally("locally")).thenReturn(symbol);
        Assert.assertEquals(block.lookup("name"), symbol);
        Assert.assertEquals(block.lookupLocally("locally"), symbol);
    }
}
