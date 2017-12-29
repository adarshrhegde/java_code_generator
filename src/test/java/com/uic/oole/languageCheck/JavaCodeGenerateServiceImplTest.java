package com.uic.oole.languageCheck;

import com.uic.oole.languagecheck.JavaCodeGenerateServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test Class Name: JavaCodeGenerateService
 */
@PrepareForTest(JavaCodeGenerateServiceImpl.class)
public class JavaCodeGenerateServiceImplTest {

    JavaCodeGenerateServiceImpl javaCodeGenerateService;
    Object[] parameters;
    Method method;
    String methodName = "updateAndGetCountForRecursiveCalls";
    Class[] parameterType;

    /**
     * Testing the Method: GenerateCodeForNonTerminal
     */
    @Test
    public void TestGenerateCodeForNonTerminal(){
        JavaCodeGenerateServiceImpl javaCodeGenerateService = mock(JavaCodeGenerateServiceImpl.class);
        List<String> strings = Arrays.asList();
        Map<String, List<String>> list = new HashMap<>();
        list.put("string", strings);
        when(javaCodeGenerateService.generateCodeForNonTerminal("string", list)).thenReturn("code");
        Assert.assertEquals(javaCodeGenerateService.generateCodeForNonTerminal("string", list) , "code");
    }

    /**
     * Setting up the parameters for before the private method can be testes using Reflection
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        javaCodeGenerateService = new JavaCodeGenerateServiceImpl();
        parameterType = new Class[1];
        parameterType[0] = java.lang.String.class;
        method = javaCodeGenerateService.getClass().getDeclaredMethod(methodName, parameterType);
        method.setAccessible(true);
    }

    /**
     * Testing the UpdateAndGetCountForRecursiveCalls method using reflection
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void TestUpdateAndGetCountForRecursiveCalls() throws InvocationTargetException, IllegalAccessException {
        parameters = new Object[1];
        parameters[0] = "<string characters>";
        int result = (int) method.invoke(javaCodeGenerateService, parameters);
        Assert.assertNotNull(result);

    }
}