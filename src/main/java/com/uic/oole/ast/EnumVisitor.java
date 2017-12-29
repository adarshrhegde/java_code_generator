package com.uic.oole.ast;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.parser.CClass;

import java.util.List;

/**
 * Class Name: Enum Visitor, visits are the enum declarations in a class
 */
public class EnumVisitor extends VoidVisitorAdapter<CClass> {
    /**
     * Enum Constructor:: JLS
     * https://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.4.2
     * The constructor of an Enum can't be declared either public or protected
     * @param n
     * @param arg
     */
    @Override
    public void visit(EnumDeclaration n, CClass arg) {
        List<ConstructorDeclaration> constructorList = n.getConstructors();
        for(ConstructorDeclaration constructor: constructorList){
            if((n.isEnumDeclaration() && n.getModifiers().contains(Modifier.PROTECTED) || (n.isEnumDeclaration() && n.getModifiers().contains(Modifier.PUBLIC)))){
                ErrorInformation.removeNode(n,"Compile time error for the constructor of an enum, the constructor cannot be " +
                        "declared public or protected");
            }
        }
        super.visit(n, arg);
    }
}
