package com.uic.oole.languagecheck;

import com.github.javaparser.ast.Node;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;

import java.util.Map;

public interface LanguageValidationService {

    void validateMethodOverriding(String className, Map<String,CClass> classMap, Map<String,Node> classNodeMap);

    void validateReturnStatement(CClass cClass, Method method, Node parent, Node returnNode);
}
