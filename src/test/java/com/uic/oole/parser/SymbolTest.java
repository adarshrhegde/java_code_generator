package com.uic.oole.parser;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * Test Class Name: SymbolTest
 */
@PrepareForTest(Symbol.class)
public class SymbolTest {
    /**
     * Testing the classe getter and setter methods
     */
    @Test
    public void testMethods(){
        Symbol symbol = new Symbol();
        symbol.setLocalIdentifier(2);
        symbol.setParameterIdentifier(1);
        Assert.assertEquals(symbol.getLocalIdentifier(), 2);
        Assert.assertEquals(symbol.getParameterListIdentifier(), 1);
    }
}
