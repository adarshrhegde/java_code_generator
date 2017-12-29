package com.uic.oole.ast;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.parser.CClass;

import java.util.List;

/**
 * Class Name: SuperClassVisitor
 */
public class SuperClassVisitor extends VoidVisitorAdapter<CClass> {
    /**
     * visits nods of type class and does conditional checking to make super that
     * a super class does extend from another super class
     * @param n
     * @param arg
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration n, CClass arg) {
        NodeList<ClassOrInterfaceType> listOfClasses = n.getExtendedTypes();
        super.visit(n, arg);
    }
}
