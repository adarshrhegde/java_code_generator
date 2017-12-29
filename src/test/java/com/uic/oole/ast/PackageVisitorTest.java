package com.uic.oole.ast;

import com.github.javaparser.ast.PackageDeclaration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@PrepareForTest(PackageVisitor.class)
public class PackageVisitorTest {
    @Mock
    private PackageVisitor packageVisitor;

    @Before
    public void setUp()throws Exception{
        packageVisitor = mock(PackageVisitor.class);
    }

    /**
     * Test Method: Visit
     * checks whether the visit method is being invoked
     */
    @Test
    public void testVisit(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("packageName");
        PackageDeclaration packageDeclaration = new PackageDeclaration();
        verify(packageVisitor, atLeast(0)).visit(packageDeclaration,stringBuilder);
    }
}