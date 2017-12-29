package com.uic.oole.ast;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.parser.CClass;

/**
 * Class name: InnerClassVisitor
 */
public class InnerClassVisitor extends VoidVisitorAdapter<CClass>{
    /**
     * The Class or interface declarations are visited and checks for all inner classes in them
     * and also checks for nested classes that are not static and inner classes that are static
     * https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     * @param n
     * @param arg
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration n, CClass arg) {
        if(n.isInnerClass()){
            new VoidVisitorAdapter<Void>(){
                /**
                 * Inner Class:: JLS
                 * Visits all the variables in the inner class and checks whether the variables are static
                 * and not final and throws a compile time error if it fails in the condition
                 * @param n
                 * @param arg
                 */
                @Override
                public void visit(VariableDeclarationExpr n, Void arg) {
                    if(n.getModifiers().contains(Modifier.STATIC) && n.getModifiers().contains(Modifier.FINAL)){
                        ErrorInformation.removeNode(n,"Inner classes can't have static methods");
                    }
                    super.visit(n, arg);
                }
            };
        }
        if(n.isNestedType() && !(n.getModifiers().contains(Modifier.STATIC))){
            ErrorInformation.removeNode(n, "Compile time error for nested class, nested classes must be static");
        }

        if(n.isInnerClass() && n.getModifiers().contains(Modifier.STATIC)){
            ErrorInformation.removeNode(n, "Compile time error, Inner classes can't be static");
        }
        super.visit(n, arg);
    }
}
