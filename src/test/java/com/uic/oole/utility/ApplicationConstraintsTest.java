package com.uic.oole.utility;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(ApplicationConstraints.class)
public class ApplicationConstraintsTest {

    @Test
    public void testConstraintsFile(){
        ApplicationConstraints ac = new ApplicationConstraints();
        ac.setTypeString("String");
        ac.setTypeShort("short");
        ac.setTypeDouble("double");
        ac.setTypeObject("Object");
        ac.setTypeLong("long");
        ac.setTypeInt("Int");
        ac.setTypeFloat("float");
        ac.setTypeChar("char");
        ac.setTypeByte("byte");
        ac.setMaxInheritanceDepth(10);
        ac.setTotalLOC(10000);
        ac.setMaxNoOfParametersPerMethod(5);
        ac.setRecursionProbability(0.2);
        ac.setMaxNoOfMethodsPerInterface(6);
        ac.setMaxNoOfMethodsPerClass(10);
        ac.setMaxNestedIfs(2);
        ac.setMaxNoOfClassFields(2);
        ac.setMaxAllowedMethodCalls(3);
        ac.setNoOfInterfaces(2);
        ac.setNoOfInheritanceChains(2);
        ac.setAllowIndirectRecursion("no");
        ac.setAllowArray("yes");
        ac.setIntMaxValue(10);
        ac.setClassNamePrefix("no");

        Assert.assertEquals(ac.getTypeString(),"String");
        Assert.assertEquals(ac.getTypeShort(), "short");
        Assert.assertEquals(ac.getTypeDouble(), "double");
        Assert.assertEquals(ac.getTypeObject(), "Object");
        Assert.assertEquals(ac.getTypeLong(), "long");
        Assert.assertEquals(ac.getTypeInt(), "Int");
        Assert.assertEquals(ac.getTypeFloat(), "float");
        Assert.assertEquals(ac.getTypeChar(), "char");
        Assert.assertEquals(ac.getTypeByte(), "byte");
        Assert.assertEquals(ac.getMaxInheritanceDepth(), 10);
        Assert.assertEquals(ac.getTotalLOC(), 10000);
        Assert.assertEquals(ac.getMaxNoOfParametersPerMethod(), 5);
        Assert.assertEquals(ac.getMaxNoOfParametersPerMethod(), 5);
        Assert.assertEquals(ac.getMaxNoOfMethodsPerInterface(), 6);
        Assert.assertEquals(ac.getMaxNestedIfs(), 2);
        Assert.assertEquals(ac.getMaxNoOfClassFields(), 2);
        Assert.assertEquals(ac.getMaxAllowedMethodCalls(), 3);
        Assert.assertEquals(ac.getNoOfInterfaces(), 2);
        Assert.assertEquals(ac.getNoOfInheritanceChains(), 2);
        Assert.assertEquals(ac.getAllowIndirectRecursion(), "no");
        Assert.assertEquals(ac.getAllowArray(), "yes");
        Assert.assertEquals(ac.getIntMaxValue(), 10);
        Assert.assertEquals(ac.getClassNamePrefix(), "no");
    }
}
