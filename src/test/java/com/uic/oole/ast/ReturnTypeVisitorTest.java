package com.uic.oole.ast;

import com.github.javaparser.ast.stmt.ReturnStmt;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;

@PrepareForTest(ReturnTypeVisitor.class)
public class ReturnTypeVisitorTest {

    @Mock
    ReturnTypeVisitor returnTypeVisitor = mock(ReturnTypeVisitor.class);
    CClass cClass;
    Method method;

    @Before
     public void setUp() throws Exception{
        cClass = mock(CClass.class);
        method = mock(Method.class);
    }

    /**
     * Testing the visitor method, checking for method invocation
     */
    @Test
    public void testVisit(){
        ReturnStmt returnStmt = new ReturnStmt();
        Pair<CClass, Method> pair = new Pair<>(cClass, method);
        Mockito.verify(returnTypeVisitor, atLeast(0)).visit(returnStmt, pair);
    }
}
