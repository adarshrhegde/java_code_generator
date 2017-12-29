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

@PrepareForTest(SuperClassVisitor.class)
public class SuperClassVisitorTest {
    @Mock
    private SuperClassVisitor superClassVisitor;
    private CClass cClass;

    @Before
    public void setUp() throws Exception{
        superClassVisitor = mock(SuperClassVisitor.class);
        cClass = mock(CClass.class);
    }

    /**
     * Test Method: Visit
     * Checks whether the method is being invoked
     */
    @Test
    public void testVisit(){
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = new ClassOrInterfaceDeclaration();
        verify(superClassVisitor, atLeast(0)).visit(classOrInterfaceDeclaration, cClass);
    }
}
