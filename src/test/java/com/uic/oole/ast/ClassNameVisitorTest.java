package com.uic.oole.ast;

import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.uic.oole.parser.CClass;
import org.junit.Before;
import org.mockito.Mock;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@PrepareForTest(ClassNameVisitor.class)
public class ClassNameVisitorTest {
    /**
     * Tests the Visitor methods and checks whether all
     * the reference methods are being invoked
     */

    @Mock
    private ClassNameVisitor classNameVisitor;
    private ClassOrInterfaceDeclaration classOrInterfaceDeclaration;
    private CClass cClass;

    @Before
    public void setUp(){
        classNameVisitor = mock(ClassNameVisitor.class);
        classOrInterfaceDeclaration = new ClassOrInterfaceDeclaration();
        cClass = new CClass();
    }

    /**
     * Test Method: Visitor
     * checks for method invocations
     */
    @Test
    public void testVisitor(){
        SimpleName simpleName = new SimpleName();
        verify(classNameVisitor, atLeast(0)).visit(classOrInterfaceDeclaration, cClass);
        verify(classNameVisitor, atLeast(0)).visit(simpleName, cClass);
    }
}
