package com.uic.oole.utility;

import com.uic.oole.languagecheck.JavaCodeGenerateServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static junit.framework.TestCase.fail;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProgramGeneratorUtils.class)
public class ProgramGeneratorUtilsTest {

    @Mock
    private JavaCodeGenerateServiceImpl javaCodeGenerateService;
    ProgramGeneratorUtils programGeneratorUtils;
    Method method;
    Class[] parameters;
    String methodName = "getVariableNameInScope";

    @Before
    public void setUp() throws Exception {
        javaCodeGenerateService = mock(JavaCodeGenerateServiceImpl.class);
        PowerMockito.mockStatic(ProgramGeneratorUtils.class);
        when(ProgramGeneratorUtils.generateIdentifierForVariables(javaCodeGenerateService)).thenReturn("string");
       /* parameters = new Class[1];
        parameters[0] = java.lang.Object.class;
        method = programGeneratorUtils.getClass().getDeclaredMethod(methodName, parameters);
        method.setAccessible(true);*/
    }

    /**
     * Test Method: GenerateIdentifierForVariables
     * generates an identifier for variables and asserts true when
     * the actual matches with the expected
     */
    @Test
    public void testGenerateIdentifierForVariables(){
        Assert.assertEquals(ProgramGeneratorUtils.generateIdentifierForVariables(javaCodeGenerateService), "string");
    }

    /**
     * Test Method: GetVariableNameInScope
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test(expected = NullPointerException.class)
    public void testGetVariableNameInScope() throws InvocationTargetException, IllegalAccessException {
        parameters = new Class[1];
        parameters[0] = JavaCodeGenerateServiceImpl.class;
        String result = (String )method.invoke(methodName ,parameters);
        fail(result);
    }
}
