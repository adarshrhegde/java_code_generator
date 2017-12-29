package com.uic.oole.ast;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Symbol;
import com.uic.oole.utility.ASTUtils;

/**
 * Class Name: VariableSymbolDeclaration
 * visits the variables node and does conditional checking based
 * on the variable declaration JSL rules
 */
public class VariableSymbolDeclarationVisitor extends VoidVisitorAdapter<CClass>{

    @Override
    public void visit(VariableDeclarator n, CClass cClass) {
        if(ASTUtils.getParentNodeType(n) == ASTUtils.NodeType.classOrInterface) {
            String name = n.getName().toString();
            Symbol symbol = new Symbol(name, n.getType().toString(), false);
            cClass.getSymbolTable().put(name, symbol);
            /**
             * Field Declaration::
             *Checks for variables that are not re-declared in the global scope
             */
            if(name.length() > 1){
				//ErrorInformation.removeNode(n, "There are more than 1 vars of the same name");
            }
            super.visit(n, cClass);
        }
    }

    @Override
    public void visit(VariableDeclarationExpr n, CClass cClass){
        /**
         * Access Modifier::
         *checks the variables signature and makes sure that there are no more than one access
         * modifier or else there will be a compile time error
         */
        if((n.getModifiers().contains(Modifier.PRIVATE) && n.getModifiers().contains(Modifier.PUBLIC) ||
                (n.getModifiers().contains(Modifier.PUBLIC) && n.getModifiers().contains(Modifier.PROTECTED)) ||
                (n.getModifiers().contains(Modifier.PROTECTED) && n.getModifiers().contains(Modifier.PRIVATE)))){
			ErrorInformation.removeNode(n, "Variables can't have more than once access modifier in their signature");
        }
        super.visit(n, cClass);
    }
}
