package com.uic.oole.ast;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Class Name: Error Information
 * Has a Set that holds all the error that are being sent to
 * The getter error nodes returns all the errors from the add method
 */
public class ErrorInformation {

    public static Set<Node> errorNodes = new HashSet<>();

    public static void removeNode(Node n, String errorMsg){
        /**
         * The node doesn't conform to JLS rules. Remove the error node
         */
        errorNodes.add(n);



    }

    public static void deleteErrorNodes(){
        for(Node node : errorNodes) {
            if(node != null && node.getParentNode().isPresent()) {
                Node parentNode = node.getParentNode().get();
                parentNode.remove(node);
            }
        }
    }

    public static Set<Node> getErrorNodes(){
        return errorNodes;
    }
}
