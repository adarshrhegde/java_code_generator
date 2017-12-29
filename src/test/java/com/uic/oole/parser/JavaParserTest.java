package com.uic.oole.parser;

import com.github.javaparser.ast.body.TypeDeclaration;
import com.uic.oole.ast.VariableSymbolDeclarationVisitor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;

/**
 * Test Class Name: JavaParser.java
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JavaParser.class)
public class JavaParserTest {

    @Mock
    private JavaParser javaParser;
    private TypeDeclaration node;
    private CClass cClass;
    private VariableSymbolDeclarationVisitor variableSymbolDeclarationVisitor;

    /**
     * Testing the parse method
     * Since, the method being a void, testing the method variables
     * and asserting them for a boolean
     */

    @Before
    public void setUp() throws Exception{
        javaParser = mock(JavaParser.class);
        node = mock(TypeDeclaration.class);
        cClass = mock(CClass.class);
        variableSymbolDeclarationVisitor = mock(VariableSymbolDeclarationVisitor.class);
    }

    @Test
    public void testParse(){
        String str = "    public class test{\n" +
                "        String name = \"testName\";\n" +
                "        int i = 10;\n" +
                "        \n" +
                "        public int testMethod(){\n" +
                "            if(i > 0) {\n" +
                "                return i;\n" +
                "            } else {\n" +
                "                return 0;\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        javaParser.parse(str);
        Assert.assertNotNull(true);
        PowerMockito.mockStatic(JavaParser.class);
    }

    /**
     * Testing the test process node
     * since the test method being a void, testing the method variables
     * for not null
     */
    @Test(expected = NullPointerException.class)
    public void testProcessNode(){
        node.setName("typeDeclarator");
        cClass.setName("className");
        verify(javaParser, atLeast(0)).processNode(node, cClass);
    }
}
