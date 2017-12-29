package com.uic.oole.ast;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.languagecheck.CodeGenerateService;
import com.uic.oole.languagecheck.JavaCodeGenerateServiceImpl;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;
import com.uic.oole.parser.Symbol;
import com.uic.oole.utility.ASTUtils;
import com.uic.oole.utility.GrammarMapGenerator;
import com.uic.oole.utility.IdentifierType;
import javafx.util.Pair;

/**
 * Class Name: MethodVariableInitializerVisitor
 * visits the nodes and does conditional checking based on its JLS rules
 */
public class MethodVariableInitializerVisitor extends VoidVisitorAdapter<Pair<CClass, Method>>{
    /**
     * vistis the nodes of type variable declarator and does conditional checking
     * to meet its JSL rules
     * @param var
     * @param pair
     */
    @Override
    public void visit(VariableDeclarator var,  Pair<CClass, Method> pair) {
        String name =var.getName().toString();
        CClass cClass = pair.getKey();
        Method method = pair.getValue();
        Symbol methodSymbol = new Symbol(name, var.getType().toString(), true);
        method.define(methodSymbol);

        if(var.getInitializer().isPresent()) {
            Pair<Boolean,String> checkInitializerResult = ASTUtils.checkInitializer(cClass, method, var.getInitializer().get());

            if (!checkInitializerResult.getKey()) {
                /**
                 * Variable being used before initialization
                 */
                ErrorInformation.removeNode(var, "Variable being used before initialization");
                CodeGenerateService codeGenerateService = new JavaCodeGenerateServiceImpl(cClass,method, IdentifierType.variableIdentifier);

                /**
                 * regenerate code for incorrect variable declaration using symbol tables of class/method
                 */
                String regeneratedVariableIdentifier = codeGenerateService.generateCodeForNonTerminal("<identifier>", GrammarMapGenerator.grammarMap);

                Node initializer = var.getInitializer().get();
                var.replace(initializer,JavaParser.parseExpression(regeneratedVariableIdentifier));
            }

            /**
             * add initialized variables to the initialized set. Used to check if variables are used before initialization
             */
            if(!method.hasBeenInitialized(var.getNameAsString()))
                method.initialize(methodSymbol);
        }
        super.visit(var, pair);
    }

    /**
     * visits the nodes of type binary expression and does conditional checking to
     * meet its JLS rules
     * @param n
     * @param pair
     */
    @Override
    public void visit(BinaryExpr n, Pair<CClass, Method> pair) {
        CClass cClass = pair.getKey();
        Method method = pair.getValue();

        VoidVisitorAdapter<Void> visitorAdapter = new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(NameExpr node, Void arg) {
                Pair<Boolean,String> checkInitializerResult = ASTUtils.checkInitializerForName(cClass, method, node);
                if(!checkInitializerResult.getKey()){

                    CodeGenerateService codeGenerateService = new JavaCodeGenerateServiceImpl(cClass,method, IdentifierType.variableIdentifier);
                    /**
                     * regenerate code for incorrect binary expression using symbol tables of class/method
                     */
                    String regeneratedVariableIdentifier = codeGenerateService.generateCodeForNonTerminal("<identifier>", GrammarMapGenerator.grammarMap);

                    if(regeneratedVariableIdentifier.equals("")){
                        /*Node parent = n.getParentNode().get();
                        if(parent.getParentNode().isPresent()) {
                            Node grandParent = parent.getParentNode().get();
                            grandParent.remove(parent);
                        }*/
                    }
                    else {
                        //n.replace(node, JavaParser.parseExpression(regeneratedVariableIdentifier));
                    }
                }
                super.visit(node, arg);
            }
        };
        n.accept(visitorAdapter,null);
        super.visit(n, pair);
    }

    /**
     * visits the nodes of type unary expression and does conditional checking to
     * satisfy its JLS rules
     * @param n
     * @param pair
     */
    @Override
    public void visit(UnaryExpr n, Pair<CClass, Method> pair) {
        CClass cClass = pair.getKey();
        Method method = pair.getValue();

        VoidVisitorAdapter<Void> visitorAdapter = new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(NameExpr node, Void arg) {
                Pair<Boolean,String> checkInitializerResult = ASTUtils.checkInitializerForName(cClass, method, node);
                if(!checkInitializerResult.getKey()){

                    CodeGenerateService codeGenerateService = new JavaCodeGenerateServiceImpl(cClass,method, IdentifierType.variableIdentifier);
                    // regenerate code for incorrect unary expression using symbol tables of class/method
                    String regeneratedVariableIdentifier = codeGenerateService.generateCodeForNonTerminal("<identifier>", GrammarMapGenerator.grammarMap);

                    n.replace(node,JavaParser.parseExpression(regeneratedVariableIdentifier));
                }
                super.visit(node, arg);
            }
        };
        n.accept(visitorAdapter,null);
        super.visit(n, pair);
    }
}