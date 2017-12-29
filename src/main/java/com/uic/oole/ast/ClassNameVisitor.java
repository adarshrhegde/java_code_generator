package com.uic.oole.ast;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.languagecheck.JavaCodeGenerateServiceImpl;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.ClassType;

import java.util.ArrayList;
import java.util.List;


/**
 * Visitor class that has methods to visit all the necessary nodes
 */
public class ClassNameVisitor extends VoidVisitorAdapter<CClass> {

    @Override
    public void visit(SimpleName n, CClass arg) {
        arg.setName(n.asString());
        super.visit(n, arg);
    }

    /**
     * Final Class:: JLS
     * This visit method visits the class or interface declaration nodes and checks the classes
     * that have both final and abstract as their modifiers.
     * @param n
     * @param cClass
     */
    public void visit(ClassOrInterfaceDeclaration n, CClass cClass){
        List<ClassOrInterfaceType> classOrInterfaceType = n.getImplementedTypes();
        /**
         * Determines the type of the class whether it's an abstract class, an interface or a class
         */
        if(!n.isInterface() && !n.isAbstract()) {

            n.addConstructor(Modifier.PUBLIC);
            List<String> variableList = new ArrayList<>();
            variableList.addAll(JavaCodeGenerateServiceImpl.integerlist);
            variableList.addAll(JavaCodeGenerateServiceImpl.variablelist);
            for (String var : variableList) {
                n.addField("int", var, Modifier.PUBLIC);
            }
        }

        if(n.isInterface()){
            cClass.setClassType(ClassType.interfaceType);
        }else if(n.isAbstract()){
            cClass.setAbstractClass(true);
        }else {
            cClass.setClassType(ClassType.classType);
        }
        /**
         * Final Class:: JLS
         * Checks for an interface which has both final and abstract in its signature
         * and throws a compile error
         */
        if(n.isInterface() && classOrInterfaceType.contains(Modifier.FINAL) && classOrInterfaceType.contains(Modifier.ABSTRACT)){

            ErrorInformation.removeNode(n,"Interface definition cannot contain both final and abstract");
        }

        /**
         * Final Class:: JLS
         * Checks all the classes that contain final and abstract in their signature
         * and throws a compile time error
         *
         */
        if(n.isClassOrInterfaceDeclaration() && classOrInterfaceType.contains(Modifier.ABSTRACT) && classOrInterfaceType.contains(Modifier.FINAL)){
            ErrorInformation.removeNode(n,"Class definition cannot cannot contain both final and abstract");
        }
        /**
         * Final Class:: JLS
         * Scans for final class type and checks whether its being extended from
         */
        if(n.isClassOrInterfaceDeclaration() && n.getExtendedTypes().contains(Modifier.FINAL)){
            ErrorInformation.removeNode(n,"A final class cannot be extended from");
        }

        /**
         * Constructor::JLS
         * Checks whether the name of the constructor matches with the class name of not
         */
        List<ConstructorDeclaration> constructors = n.getConstructors();
        for(ConstructorDeclaration constructor : constructors){
            if(!n.getName().toString().equals(constructor.getName().toString())){
                ErrorInformation.removeNode(n,"Compile time error for constructor name, the constructor name should match with the class name");
            }
        }
        super.visit(n, cClass);
    }
}
