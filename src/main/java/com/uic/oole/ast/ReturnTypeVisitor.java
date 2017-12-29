package com.uic.oole.ast;

import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.languagecheck.LanguageValidationService;
import com.uic.oole.languagecheck.LanguageValidationServiceImpl;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;
import javafx.util.Pair;

/**
 * Class Name; Return type visitor, visitor class that visits the return statement and does all the
 * necessary checks to comply the JLS rules
 */
public class ReturnTypeVisitor extends VoidVisitorAdapter<Pair<CClass, Method>>{
    /**
     *visitor method that visits the return statement node
     * @param n
     * @param arg
     */
    @Override
    public void visit(ReturnStmt n, Pair<CClass, Method> arg) {
        String returnVar = n.getExpression().get().toString();
        LanguageValidationService languageValidationService = new LanguageValidationServiceImpl();
        languageValidationService.validateReturnStatement(arg.getKey(),arg.getValue(),n.getParentNode().get(),n);
        super.visit(n, arg);
    }
}