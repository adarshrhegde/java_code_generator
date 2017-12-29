package com.uic.oole.parser;

import java.util.Set;

public interface Scope {

    String getScopeName();

    Scope getEnclosingScope();

    void define(Symbol symbol);

    void initialize(Symbol symbol);

    Symbol lookup(String name);

    Symbol lookupLocally(String name);

    boolean hasBeenInitialized(String name);

    Set<Symbol> getInitializedVariables();

    static CClass getEnclosingCClass(Scope scope) {
        while (!(scope instanceof CClass)) {
            scope = scope.getEnclosingScope();
        }
        return (CClass) scope;
    }

    static Method getEnclosingMethod(Scope scope) {
        while (!(scope instanceof Method)) {
            scope = scope.getEnclosingScope();
        }
        return (Method) scope;
    }
}
