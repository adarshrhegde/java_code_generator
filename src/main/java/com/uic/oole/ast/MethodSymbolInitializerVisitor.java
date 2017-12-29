package com.uic.oole.ast;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.languagecheck.LanguageValidationService;
import com.uic.oole.languagecheck.LanguageValidationServiceImpl;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.ClassType;
import com.uic.oole.parser.Method;
import com.uic.oole.parser.Symbol;
import javafx.util.Pair;

import java.util.List;

public class MethodSymbolInitializerVisitor extends VoidVisitorAdapter<CClass> {
    /**
     *
     * @param n
     * @param cClass
     */
    @Override
    public void visit(MethodDeclaration n, CClass cClass) {

        String name = n.getName().toString();
        Symbol symbol = new Symbol(name, n.getType().toString(), false);
        Method method = new Method(n.getType().toString(), name, null);
        if(cClass.getClassType().equals(ClassType.interfaceType) ||
                (cClass.getClassType().equals(ClassType.abstractType) && n.getModifiers().contains(Modifier.ABSTRACT))){
            method.setAbstractMethod(true);
        }

        /**
         * add parameters in the method signature to the method object
         */
        List<Parameter> parameters = n.getParameters();
        for(Parameter parameter : parameters){
            method.addParameter(new Symbol(parameter.getNameAsString(), parameter.getType().toString(), true));
        }

        cClass.getSymbolTable().put(name,symbol);
        cClass.getMethodMap().put(name, method);
        MethodVariableInitializerVisitor methodVariableInitializerVisitor = new MethodVariableInitializerVisitor();
        n.accept(methodVariableInitializerVisitor, new Pair<CClass, Method>(cClass, method));
        /**
         * Abstract Class::
         * Looks for the key word 'abstract' in the method signature
         * and returns a boolean, and as we know that if a method is abstract then
         * its class is also an abstract type.
         * Later, this value is checked against the method body to see whether
         * there was any implementation or not
         */
        if((!method.getAbstractMethod() && n.getBody() == null) || (method.getAbstractMethod() && n.getBody().isPresent())){
            ErrorInformation.removeNode(n, "Abstract method cannot have empty body, and empty method body can only be a part of abstract method");
        }
        /**
         * Field Declaration::
         * Checks the method signature to make sure that the method doesn't have more than one access modifier
         * in its signature which would result in a compile time error
         */
        if((n.getModifiers().contains(Modifier.PRIVATE) && n.getModifiers().contains(Modifier.PUBLIC) ||
                (n.getModifiers().contains(Modifier.PUBLIC) && n.getModifiers().contains(Modifier.PROTECTED)) ||
                (n.getModifiers().contains(Modifier.PROTECTED) && n.getModifiers().contains(Modifier.PRIVATE)))){
            ErrorInformation.removeNode(n, "Shouldn't contain multiple access modifiers");
        }


        /**
         * Method Declaration::
         * Gets the method signature and checks whether the method is abstract and also
         * has the keywords such as 'Native', 'Synchronized', 'Final', 'Private', 'StrictFP' and 'Static'.
         * If any of the randomly generated methods come across this type of signature, then a compile time
         * error will occur as you'll notice in the following condition
         */

        if(n.isAbstract() && (n.isNative() || n.isSynchronized() || n.getModifiers().contains(Modifier.FINAL) || n.getModifiers().contains(Modifier.PRIVATE) || n.getModifiers().contains(Modifier.STRICTFP) || n.getModifiers().contains(Modifier.STATIC))){
            ErrorInformation.removeNode(n, "Method Declaration as an abstract method can't have another of the keys words like Native, Sync, Final, Private");
        }

        /**
         * Abstract Class::
         * An abstract method can only be in an abstract class and vice versa or else there will
         * be a compile time error
         */

        if((!n.isAbstract() && cClass.getAbstractClass())){
            ErrorInformation.removeNode(n, "Compile time error in method declaration as abstract method cannot have an concrete class and vice versa");
        }

        String returnType = n.getType().toString();
        if(returnType != null && returnType != "" && returnType != "void"){
            ReturnTypeVisitor returnTypeVisitor = new ReturnTypeVisitor();
            n.accept(returnTypeVisitor, new Pair<>(cClass, method));
            if(!method.getHasReturnStatement() && !method.getAbstractMethod()){
                LanguageValidationService languageValidationService = new LanguageValidationServiceImpl();
                languageValidationService.validateReturnStatement(cClass,method,n.getBody().get(),null);
            }
        }
        super.visit(n, cClass);
    }

    @Override
    public void visit(InstanceOfExpr n, CClass arg) {
        super.visit(n, arg);
    }
}
