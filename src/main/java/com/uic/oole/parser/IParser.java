package com.uic.oole.parser;

import javafx.util.Pair;

/**
 * IParser to parse code generated in any language
 */
public interface IParser {

    /**
     * parse input string containing code
     * @param classString
     */
    Pair<String,String> parse(String classString);

}
