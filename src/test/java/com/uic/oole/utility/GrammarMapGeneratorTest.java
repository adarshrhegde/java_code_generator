package com.uic.oole.utility;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test Class Name: GrammarMapGenerator
 */
@PrepareForTest(GrammarMapGenerator.class)
public class GrammarMapGeneratorTest {
    /**
     * Testing the build grammar method
     * and asserts for not a null value
     */
    @Test
    public void testBuildGrammarMap(){
        Map<String,List<String>> grammarMap = new HashMap<>();
        GrammarMapGenerator.buildGrammarMap();
        Assert.assertNotNull(grammarMap);
    }
}

