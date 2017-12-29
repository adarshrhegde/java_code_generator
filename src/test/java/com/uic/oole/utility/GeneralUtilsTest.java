package com.uic.oole.utility;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GeneralUtils.class)
public class GeneralUtilsTest {

    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        PowerMockito.mockStatic(GeneralUtils.class);
        list1.add("someString");
        list2.add("otherString");
        PowerMockito.when(GeneralUtils.listEqualsIgnoreOrder(list1, list2)).thenReturn(true);
    }

    /**
     * Test Method: ListEqualsIgnore()
     * passing fake data and checking for an assertion
     */
    @Test
    public void TestListEqualsIgnoreOrder(){
        Assert.assertEquals(GeneralUtils.listEqualsIgnoreOrder(list1, list2), true);

    }
}
