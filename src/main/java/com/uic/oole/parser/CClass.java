package com.uic.oole.parser;

import java.util.*;

public class CClass {

    private String name;

    private CClass superClass;

    private boolean abstractClass;

    private ClassType classType;

    public List<List<String>> constructorList = new ArrayList<>();

    public Map<String, Symbol> symbolTable = new HashMap<>();

    public Map<String, Method> methodMap = new HashMap<>();

    public void addConstructorParameters(List<String> constructorParameters){
        if(constructorList != null && constructorParameters != null)
            constructorList.add(constructorParameters);
    }


    public List<List<String>> getConstructorList(){
        return constructorList;
    }

    private boolean isPublic;

    public CClass() {
        isPublic = false;
    }

    public boolean getAbstractClass() {
        return abstractClass;
    }

    public void setAbstractClass(boolean abstractClass) {
        this.abstractClass = abstractClass;
    }

    public Map<String, Method> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, Method> methodMap) {
        this.methodMap = methodMap;
    }

    public CClass(String name, CClass superClass, Map<String, Symbol> symbolTable, Map<String, Method> methodMap) {
        this.name = name;
        this.superClass = superClass;
        this.symbolTable = symbolTable;
        this.methodMap = methodMap;
        isPublic = false;
    }

    public CClass(String name, CClass superClass) {
        this.name = name;
        this.superClass = superClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CClass getSuperClass() {
        return superClass;
    }

    public void setSuperClass(CClass superClass) {
        this.superClass = superClass;
    }

    public Map<String, Symbol> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(Map<String, Symbol> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void define(Symbol sym){
        symbolTable.put(sym.getName(),sym);
    }

    public Symbol lookup(String name){
        Symbol symbol = null;
        for(CClass cClass = this; symbol==null && cClass !=null; cClass=cClass.getSuperClass()){
            symbol = cClass.symbolTable.get(name);
        }
        return symbol;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }


    public Symbol lookupLocally(String name){
        return symbolTable.get(name);
    }

    @Override
    public String toString() {
        return "CClass{" +
                "name='" + name + '\'' +
                ", superClass=" + superClass +
                ", abstractClass=" + abstractClass +
                ", classType=" + classType +
                ", symbolTable=" + symbolTable +
                ", methodMap=" + methodMap +
                '}';
    }

    public Set<String> getVariableNamesInScope() {
        Set<String> variableNames = new HashSet<>();
        for(Map.Entry<String,Symbol> entry : symbolTable.entrySet()){
            if(entry.getValue().isField()){
                variableNames.add(entry.getKey());
            }
        }
        return variableNames;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
