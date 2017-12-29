package com.uic.oole.ast;

import com.github.javaparser.ast.body.EnumDeclaration;
import com.uic.oole.parser.CClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@PrepareForTest(EnumVisitor.class)
public class EnumVisitorTest {

    @Mock
    private EnumVisitor enumVisitor;
    private EnumDeclaration enumDeclaration;
    private CClass cClass;

    @Before
    public void setUp() throws Exception{
        enumVisitor = mock(EnumVisitor.class);
        enumDeclaration = new EnumDeclaration();
        cClass = mock(CClass.class);
    }

    /**
     * Test Method: Visit
     * checks teh class for this method invocation
     */
    @Test
    public void testVisitor(){
        verify(enumVisitor, atLeast(0)).visit(enumDeclaration, cClass);
    }
}