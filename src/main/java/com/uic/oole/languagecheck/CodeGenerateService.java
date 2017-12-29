package com.uic.oole.languagecheck;

import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface CodeGenerateService {

    String generateCodeForNonTerminal(String nonTerminal, Map<String, List<String>> grammarMap);

    String getStartSymbol();
}
