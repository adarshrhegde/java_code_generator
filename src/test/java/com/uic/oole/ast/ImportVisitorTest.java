package com.uic.oole.ast;

import com.github.javaparser.ast.ImportDeclaration;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

@PrepareForTest(ImportVisitor.class)
public class ImportVisitorTest {
    /**
     * visits the visitor method and checks the modifier of the constructor and
     * asserts true
     * @throws NoSuchMethodException
     */
    @Test
    public void testVisitor() throws NoSuchMethodException {
        Constructor<ImportDeclaration> constructor = ImportDeclaration.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }
}
