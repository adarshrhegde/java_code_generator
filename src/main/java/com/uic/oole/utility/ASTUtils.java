package com.uic.oole.utility;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;
import javafx.util.Pair;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for all the generic and standard implementations that
 * can be used across the application
 */
public class ASTUtils {

    public enum NodeType {
        classOrInterface, method, unknown
    }

    public static NodeType getParentNodeType(Node node){
        if(node instanceof ClassOrInterfaceDeclaration)
            return NodeType.classOrInterface;
        else if(node instanceof MethodDeclaration)
            return NodeType.method;
        else
            return node.getParentNode() == null ? NodeType.unknown : getParentNodeType(node.getParentNode().get());
    }

    /**
     * check if variable in the right hand side of a declaration is initialized
     * @param cClass
     * @param method
     * @param expr
     * @return
     */
    public static Pair<Boolean,String> checkInitializer(CClass cClass, Method method, Expression expr){
        List<Node> simpleNames = getAllChildNodesOfType(expr, SimpleName.class);
        StringBuilder errors = new StringBuilder();
        for(Node node : simpleNames){
            if(!method.hasBeenInitialized(node.toString()) && !NumberUtils.isNumber(node.toString()) &&
                    !node.toString().equals("true") && !node.toString().equals("false"))
                errors.append("Variable not initialized : " + node.toString() + "\n");
            return new Pair<>(false,errors.toString());
        }
        return new Pair<>(true,errors.toString());
    }

    public static Pair<Boolean,String> checkInitializerForName(CClass cClass, Method method, NameExpr name){
        String errors = "";
        if(!method.hasBeenInitialized(name.getNameAsString()) && !NumberUtils.isNumber(name.getNameAsString()) &&
                    !name.getNameAsString().equals("true") && !name.getNameAsString().equals("false"))

            return new Pair<Boolean, String>(false,errors);
        else
            return new Pair<Boolean, String>(true,errors);
    }

    public static <T> List<Node> getAllChildNodesOfType(Node n, Class<T> type){

        List<Node> childList = new ArrayList<>();
        if(type.isInstance(n)){
            childList.add(n);
        }
        for(Node child : n.getChildNodes()){
            childList.addAll(getAllChildNodesOfType(child,type));
        }
        return childList;
    }
}
