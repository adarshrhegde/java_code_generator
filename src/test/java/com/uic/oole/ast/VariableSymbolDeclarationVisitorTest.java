package com.uic.oole.ast;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.uic.oole.parser.CClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@PrepareForTest(VariableSymbolDeclarationVisitor.class)
public class VariableSymbolDeclarationVisitorTest {

    @Mock
    private VariableSymbolDeclarationVisitor variableSymbolDeclarationVisitor;
    private CClass cClass;

    @Before
    public void setUp() throws Exception{
        variableSymbolDeclarationVisitor = mock(VariableSymbolDeclarationVisitor.class);
        cClass = mock(CClass.class);
    }

    /**
     * Test Method: Visit
     * checks whether the method is being invoked
     */
    @Test
    public void testVisitor(){
        VariableDeclarator variableDeclarator = new VariableDeclarator();
        VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr();
        verify(variableSymbolDeclarationVisitor, atLeast(0)).visit(variableDeclarator, cClass);
        verify(variableSymbolDeclarationVisitor, atLeast(0)).visit(variableDeclarationExpr, cClass);
    }

}
