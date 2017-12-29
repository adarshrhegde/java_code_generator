package com.uic.oole.ast;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.uic.oole.parser.CClass;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Visitor class for class constructors
 * Checks if multiple constructors have same parameters
 */
public class ConstructorDeclarationVisitor extends VoidVisitorAdapter<CClass> {

    @Override
    public void visit(ConstructorDeclaration n, CClass cClass) {
        List<String> constructorParameters = n.getParameters().stream().map((s) -> s.getType().toString()).collect(Collectors.toList());
        List<List<String>> classConstructors = cClass.getConstructorList();

        boolean match = false;
        for (List<String> constructor : classConstructors) {

            if (constructor.containsAll(constructorParameters) && constructorParameters.containsAll(constructor)) {
                ErrorInformation.removeNode(n, "Multiple constructors cannot have same parameters");
                match = true;
            }

        }
        if(!match || (null != classConstructors && classConstructors.size() == 0))
            cClass.addConstructorParameters(constructorParameters);

        super.visit(n, cClass);
    }
}
