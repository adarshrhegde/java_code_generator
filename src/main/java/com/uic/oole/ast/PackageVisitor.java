package com.uic.oole.ast;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Class Name: Package  Visitor
 */
public class PackageVisitor extends VoidVisitorAdapter<StringBuilder> {
    /**
     * Visits the package declaration node and appends it to the String
     * Builder called package name
     * @param n
     * @param packageName
     */
    @Override
    public void visit(PackageDeclaration n, StringBuilder packageName) {
        packageName.append(n.getName().asString());
        super.visit(n, packageName);
    }
}
