package com.uic.oole.parser;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.VoidType;
import com.uic.oole.ast.*;
import com.uic.oole.languagecheck.JavaCodeGenerateServiceImpl;
import com.uic.oole.languagecheck.LanguageValidationService;
import com.uic.oole.languagecheck.LanguageValidationServiceImpl;
import com.uic.oole.utility.GrammarMapGenerator;
import javafx.util.Pair;

import java.util.*;

/**
 * Java parser that takes Java code and generates AST tree for traversal
 * and JLS rule validation
 */
public class JavaParser implements IParser {

    static Map<java.lang.String,CClass> classMap = new HashMap<>();

    static Map<java.lang.String, Node> classNodeMap = new HashMap<>();

    static LanguageValidationService validationService = new LanguageValidationServiceImpl();

    /**
     * parse method to generate AST tree for java code and process for further validation using
     * Visitor pattern
     * @param classString
     */
    @Override
    public Pair<String,String> parse(String classString) {

        CompilationUnit cu = com.github.javaparser.JavaParser.parse(classString);

        NodeList<TypeDeclaration<?>> types = cu.getTypes();

        StringBuilder packageName = new StringBuilder("");
        cu.accept(new PackageVisitor(), packageName);

        cu.accept(new ImportVisitor(), classMap.keySet());

        boolean hasPublicType = false;
        String fileName = "";
        Set<String> imports = new HashSet<>();
        for(TypeDeclaration<?> type : types){

            EnumSet<Modifier> modifiers = type.getModifiers();

            ClassNameVisitor classNameVisitor = new ClassNameVisitor();
            CClass newClass = new CClass();
            /**
             * Ensure only one public type in a file
             */
            if (modifiers.contains(Modifier.PUBLIC)) {
                if(!hasPublicType) {
                    hasPublicType = true;
                    newClass.setIsPublic(true);

                }
                else {
                    modifiers.remove(Modifier.PUBLIC);
                    type.setModifiers(modifiers);
                }
            }
            type.accept(classNameVisitor, newClass);


            ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) type;
            NodeList<ClassOrInterfaceType> nodeList = new NodeList<>();
            nodeList.addAll(classOrInterfaceDeclaration.getExtendedTypes());
            nodeList.addAll(classOrInterfaceDeclaration.getImplementedTypes());

            //
            if(null != nodeList && nodeList.size() > 0) {
                ClassOrInterfaceType superType = nodeList.get(0);

                String superClassName = superType.getName().asString();
                Set<String> classNames = classMap.keySet();
                for (String className : classNames) {
                    if (className.contains(superClassName)) {
                        superClassName = className;

                        CClass parentCclass = classMap.get(superClassName);
                        if(!parentCclass.isPublic()){
                            classOrInterfaceDeclaration.remove(superType);
                        } else {
                            newClass.setSuperClass(parentCclass);
                            imports.add(superClassName);
                        }
                    }
                }
            }

            java.lang.String fullyQualifiedClassName = newClass.getName();
            if(!packageName.toString().equals(""))
                fullyQualifiedClassName = packageName + "." + newClass.getName();

            newClass.setName(fullyQualifiedClassName);
            classMap.put(fullyQualifiedClassName, newClass);
            classNodeMap.put(fullyQualifiedClassName, type);
            if (modifiers.contains(Modifier.PUBLIC)) {
                fileName = fullyQualifiedClassName;
            }
            processNode(type, newClass);
            validationService.validateMethodOverriding(fullyQualifiedClassName,classMap,classNodeMap);

        }
        JavaCodeGenerateServiceImpl.integerlist.clear();
        JavaCodeGenerateServiceImpl.variablelist.clear();

        for(String importName : imports) {
            cu.getImports().add(new ImportDeclaration(new Name(importName), false, false));
        }

        ErrorInformation.deleteErrorNodes();
        final String fileCode = cu.toString();
        if(fileName.equals(""))
            fileName = cu.getPackageDeclaration().get().getName().toString() + "." + types.get(0).getNameAsString();
        fileName += ".java";
        return new Pair<String,String>(fileName,fileCode);
    }

    /**
     * process a type node in the AST tree and invoke visitors
     * @param node
     * @param cClass
     */
    static void processNode(TypeDeclaration node, CClass cClass) {

        ConstructorDeclarationVisitor constructorDeclarationVisitor = new ConstructorDeclarationVisitor();
        node.accept(constructorDeclarationVisitor,cClass);

        if(node.getModifiers().contains(Modifier.ABSTRACT)){
            cClass.setClassType(ClassType.abstractType);
        } else {
            List<MethodDeclaration> methodList = node.getMethods();
            if(methodList.size() > 0 && !methodList.get(0).getBody().isPresent()){
                cClass.setClassType(ClassType.interfaceType);
            }
        }

        VariableSymbolDeclarationVisitor variableSymbolDeclarationVisitor = new VariableSymbolDeclarationVisitor();
        node.accept(variableSymbolDeclarationVisitor, cClass);

        MethodSymbolInitializerVisitor methodSymbolInitializerVisitor = new MethodSymbolInitializerVisitor();
        node.accept(methodSymbolInitializerVisitor,cClass);

        /**
         * removing the recursive traversal of the tree because it is done by visitors
         */
        /*for (Node child : node.getChildNodes()){
            processNode(child, cClass);
        }*/

    }

}
