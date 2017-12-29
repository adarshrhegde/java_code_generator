package com.uic.oole.ast;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.uic.oole.parser.CClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@PrepareForTest(InnerClassVisitor.class)
public class InnerClassVisitorTest {

    @Mock
    private InnerClassVisitor innerClassVisitor;
    private CClass cClass;
    private ClassOrInterfaceDeclaration classOrInterfaceDeclaration;

    @Before
    public void setUp() throws Exception{
        innerClassVisitor = mock(InnerClassVisitor.class);
        classOrInterfaceDeclaration = new ClassOrInterfaceDeclaration();
        cClass = mock(CClass.class);
    }

    /**
     * Test Method: Visit
     * tests the visit method and checks whether its being
     * invoked
     */
    @Test
    public void testVisitor(){
        verify(innerClassVisitor, atLeast(0)).visit(classOrInterfaceDeclaration, cClass);
    }
}
