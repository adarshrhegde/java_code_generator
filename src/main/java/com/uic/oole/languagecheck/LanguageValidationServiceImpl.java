package com.uic.oole.languagecheck;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.ClassType;
import com.uic.oole.parser.Method;
import com.uic.oole.parser.Symbol;
import com.uic.oole.utility.GrammarMapGenerator;
import com.uic.oole.utility.IdentifierType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Language Validation Service checks the rules in the JLS
 */
public class LanguageValidationServiceImpl implements LanguageValidationService {
    /**
     * checks if abstract methods in interfaces and abstract classes have been overridden
     * @param cClass
     * @param method
     * @param parent
     * @param returnNode
     */
    @Override
    public void validateReturnStatement(CClass cClass, Method method, Node parent, Node returnNode) {
        if(null == returnNode){
            IdentifierType identifierType = IdentifierType.returnStatement;
            identifierType.getTypeByValue(method.getType());

            CodeGenerateService codeGenerateService = new JavaCodeGenerateServiceImpl(cClass, method, identifierType);
            String returnBlock = codeGenerateService.generateCodeForNonTerminal("<return>", GrammarMapGenerator.grammarMap);

            ReturnStmt stmt = (ReturnStmt) JavaParser.parseStatement(returnBlock);
            BlockStmt block = (BlockStmt) parent;
            block.addStatement(stmt);
        } else {

            List<Symbol> symbolsAvailableInScope = new ArrayList<>();

            symbolsAvailableInScope.addAll(cClass.symbolTable.values());
            symbolsAvailableInScope.addAll(method.getInitializedVariables());
            symbolsAvailableInScope.addAll(method.getParameterList());
            boolean symbolFound = false;

            for(Symbol symbol : symbolsAvailableInScope){
                if(symbol.isField() && symbol.getType().equals(method.getType())){
                    symbolFound = true;
                    String returnStatement = "return " + symbol.getName() + ";";
                    ReturnStmt stmt = (ReturnStmt) JavaParser.parseStatement(returnStatement);
                    parent.replace(returnNode,stmt);
                }
            }

            if(!symbolFound) {
                IdentifierType identifierType = IdentifierType.returnStatement;
                identifierType.getTypeByValue(method.getType());

                CodeGenerateService codeGenerateService = new JavaCodeGenerateServiceImpl(cClass, method, identifierType);
                String returnBlock = codeGenerateService.generateCodeForNonTerminal("<return>", GrammarMapGenerator.grammarMap);

                ReturnStmt stmt = (ReturnStmt) JavaParser.parseStatement(returnBlock);
                parent.replace(returnNode, stmt);
            }
        }
    }

    /**
     * checks if abstract methods in interfaces and abstract classes have been overridden
     * @param className
     * @param classMap
     */
    @Override
    public void validateMethodOverriding(String className, Map<String,CClass> classMap, Map<java.lang.String, Node> classNodesMap) {

        CClass subClass = classMap.get(className);
        Set<String> methodsInSubclass = subClass.getMethodMap().keySet();

        CClass superClass = subClass.getSuperClass();
        if(null != superClass){
            ClassType superClassType = superClass.getClassType();

            /**
             * check if the super type is an interface or abstract class
             */
            if(superClassType.equals(ClassType.interfaceType) || superClassType.equals(ClassType.abstractType)){

                Map<String, Method> methodMap = superClass.getMethodMap();
                Set<String> superMethods = methodMap.keySet();
                String superClassName = superClass.getName();
                /**
                 * iterate over the methods in the super class
                 */
                for(String superMethod : superMethods){
                    Method superClassMethod = methodMap.get(superMethod);
                    /**
                     * check if the super class method is abstract
                     */
                    if(!methodMap.get(superMethod).getAbstractMethod())
                        continue;
                    /**
                     * check if a method of the exact name exists in the subclass
                     */
                    if(methodsInSubclass.contains(superMethod))
                        continue;
                    List<Symbol> parameterList = methodMap.get(superMethod).getParameterList();

                    IdentifierType identifierType = IdentifierType.methodBody;
                    identifierType.getTypeByValue(superClassMethod.getType());
                    CodeGenerateService codeGenerateService = new JavaCodeGenerateServiceImpl(superClass,superClassMethod, identifierType);
                    String methodBlockCode = codeGenerateService.generateCodeForNonTerminal("<block>", GrammarMapGenerator.grammarMap);

                    TypeDeclaration type = (TypeDeclaration) classNodesMap.get(className);
                    MethodDeclaration methodDeclare = type.addMethod(superClassMethod.getName(), Modifier.PUBLIC);
                    for(Symbol symbol : parameterList){
                        methodDeclare.addParameter(symbol.getType(),symbol.getName());
                    }
                    methodDeclare.setType(superClassMethod.getType());
                    BlockStmt blockStmt = com.github.javaparser.JavaParser.parseBlock(methodBlockCode);
                    methodDeclare.setBody(blockStmt);
                    //methodNode.setParentNode(classOrInterfaceType);
                }
            }
        }
    }
}
