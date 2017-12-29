package com.uic.oole.utility;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.mockito.Mockito.mock;

@PrepareForTest(PropertiesUtils.class)
public class PropertiesUtilTest {
    @Mock
    PropertiesUtils propertiesUtilTest = mock(PropertiesUtils.class);

    @Before
    public void setUp()throws Exception{
        propertiesUtilTest.setProperty("test", "code");
    }

    /**
     * Testing the properties method and asserting whether the setters
     * and getters are equal
     */
    @Test
    public void testProperties(){
        String actual = "code";
        String result = propertiesUtilTest.getProperties().getProperty("test");
        Assert.assertEquals(result, actual);
    }
}
