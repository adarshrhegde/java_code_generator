package com.uic.oole.languageCheck;

import com.github.javaparser.ast.Node;
import com.uic.oole.languagecheck.LanguageValidationServiceImpl;
import com.uic.oole.parser.CClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Test Class Name: LanguageValidationServiceImplementation
 */
@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(LanguageValidationServiceImpl.class)
public class LanguageValidationServiceImplTest {
    /**
     * Setting up the rules to test the method for
     * any exceptions since the method being a void and might
     * throw an exception as it does not have a try catch block
     */
    @Mock
    private LanguageValidationServiceImpl languageValidationService;
    private CClass cClass;
    private Node node;

    @Before
    public void setUp() throws Exception{
        languageValidationService = mock(LanguageValidationServiceImpl.class);
        cClass = mock(CClass.class);
        node = mock(Node.class);
    }
    /**
     * Testing the ValidationMethodOverriding method using
     * JUnit test
     */
    @Test
    public void TestValidateMethodOverriding(){
        String className = "className";
        Map<String,CClass> classMap = new HashMap<>();
        cClass.setName("testClass");
        classMap.put("string", cClass);
        Map<java.lang.String, Node> classNodesMap = new HashMap<>();
        node.toString();
        classNodesMap.put("string", node);
        verify(languageValidationService, atLeast(0)).validateMethodOverriding(className, classMap, classNodesMap);
    }
}

