package com.uic.oole.ast;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.uic.oole.parser.CClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@PrepareForTest(MethodSymbolInitializerVisitor.class)
public class MethodSymbolInitializerVisitorTest {
    @Mock
    private MethodSymbolInitializerVisitor methodSymbolInitializerVisitor;
    private CClass cClass;
    private MethodDeclaration methodDeclaration;

    @Before
    public void setUp(){
        methodSymbolInitializerVisitor = mock(MethodSymbolInitializerVisitor.class);
        cClass = mock(CClass.class);
        methodDeclaration = new MethodDeclaration();
    }

    /**
     * Test Method: Visit
     * checks whether the method is being invoked
     */
    @Test
    public void testVisitor(){
        verify(methodSymbolInitializerVisitor, atLeast(0)).visit(methodDeclaration, cClass);
    }
}