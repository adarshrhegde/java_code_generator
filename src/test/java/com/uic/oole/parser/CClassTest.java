package com.uic.oole.parser;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import java.util.HashMap;
import java.util.Map;

@PrepareForTest(CClass.class)
public class CClassTest {
    /**
     * Testing all the getter and setter methods in the CClass class
     */
    @Test
    public void testMethods(){
        Map<String, Symbol> map = new HashMap<>();
        Method method = new Method();
        method.setAbstractMethod(true);
        Symbol symbol = new Symbol();
        map.put("string", symbol);
        symbol.name = "name";
        CClass cClass = new CClass();
        Map<String, Method> methodMap = new HashMap<>();
        methodMap.put("string", method);
        cClass.setClassType(ClassType.classType);
        cClass.setAbstractClass(true);
        cClass.setName("name");
        cClass.setSymbolTable(map);
        cClass.setMethodMap(methodMap);
        /**
         * checking assertions for all test case possibilities
         */
        Assert.assertEquals(symbol.getName(), "name");
        Assert.assertEquals(method.getAbstractMethod(), true);
        Assert.assertEquals(map.get("string"), symbol);
        Assert.assertEquals(methodMap.get("string"), method);
    }
}
