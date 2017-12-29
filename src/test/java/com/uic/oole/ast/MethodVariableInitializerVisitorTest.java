package com.uic.oole.ast;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@PrepareForTest(MethodVariableInitializerVisitor.class)
public class MethodVariableInitializerVisitorTest {
    @Mock
    private MethodVariableInitializerVisitor methodVariableInitializerVisitor;
    private CClass cClass;
    private Method method;
    private VariableDeclarator variableDeclarator;
    private BinaryExpr binaryExpr;
    private UnaryExpr unaryExpr;

    @Before
    public void setUp(){
        methodVariableInitializerVisitor = mock(MethodVariableInitializerVisitor.class);
        cClass = mock(CClass.class);
        method = mock(Method.class);
        variableDeclarator = new VariableDeclarator();
        binaryExpr = new BinaryExpr();
        unaryExpr = new UnaryExpr();
    }
    /**
     * Test Method: Visit
     * checks whether the method is invoked
     */
    @Test
    public void testVisitor(){
        Pair<CClass, Method> pair = new Pair<>(cClass, method);
        verify(methodVariableInitializerVisitor, atLeast(0)).visit(unaryExpr, pair);
        verify(methodVariableInitializerVisitor, atLeast(0)).visit(binaryExpr, pair);
        verify(methodVariableInitializerVisitor, atLeast(0)).visit(variableDeclarator, pair);
    }
}
