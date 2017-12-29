package com.uic.oole.ast;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Set;

/**
 * Class Name: Import Visitor
 */
public class ImportVisitor extends VoidVisitorAdapter<Set<String>> {
    /**
     * Import:: JLS
     * The visit method visits all the import declarations in each node
     * and checks are put it place to handle import statement and throws an error
     * if it doesn't satisfy any of the JLS rules
     * @param n
     * @param keys
     */
    @Override
    public void visit(ImportDeclaration n, Set<String> keys) {

        String importName = n.getNameAsString();
        /**
         * check if the imports are a part of the java package or one of the classes in the
         * project
         */
        if(!importName.contains("java.lang") && !importName.contains("java.util") &&
                !importName.contains("java.io") && !importName.contains("java.awt")
                && !importName.contains("java.swing") && !checkImport(keys, importName)){
            ErrorInformation.removeNode(n,"Incorrect import declaration");
        }
        super.visit(n, keys);
    }

    private boolean checkImport(Set<String> importSet, String importName){
        if(null == importSet || importSet.size() < 1)
            return true;
        for(String importFromSet : importSet){
            if(importFromSet.equals(importName) || importFromSet.contains(importName))
                return true;
        }
        return false;
    }
}
