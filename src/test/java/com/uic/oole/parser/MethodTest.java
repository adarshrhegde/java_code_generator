package com.uic.oole.parser;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static junit.framework.TestCase.fail;

@PrepareForTest(Method.class)
public class MethodTest {
    /**
     * Tests the getter and setter methods of the object
     */
    @Test
    public void testMethods(){
        Method method = new Method();
        method.setAbstractMethod(true);
        method.name = "symbol";
        method.setLocalIdentifier(1);
        method.setParameterIdentifier(2);
        Assert.assertTrue(method.getAbstractMethod());
        Assert.assertEquals(method.getParameterListIdentifier(), 2);
        Assert.assertEquals(method.getLocalIdentifier(), 1);
    }

    @Test(expected = NullPointerException.class)
    public void testLookUp(){
        Method method = new Method();
        Symbol sym = method.lookup("symbol");
        fail(sym.toString());
    }
}
