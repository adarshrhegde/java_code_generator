package com.uic.oole.parser;

public class Symbol {
    //The name of this symbol.
    protected String name;

    //The type of this symbol.
    private String type;

    //A flag representing whether or not this Symbol is a field.
    private boolean isField;

    //The unique identifier of this Symbol if it is a local.
    //Used exclusively in code generation.
    private int localIdentifier = -1;

    //The unique identifier of this symbol if it is a parameter.
    //Used exclusively in code generation.
    private int parameterListIdentifier = -1;


    public Symbol(){}



    public boolean isParameter(){
        return parameterListIdentifier!=-1;
    }

    public void setParameterIdentifier(int parameterListIdentifier){
        this.parameterListIdentifier=parameterListIdentifier;
    }

    public int getParameterListIdentifier(){
        return parameterListIdentifier;
    }

    public boolean isField(){
        return isField;
    }

    /**
     * @return true if this symbol is a local variable and
     *         it has been assigned a unique identifier.
     */
    public boolean hasLocalIdentifier(){
        return localIdentifier!=-1;
    }

    public int getLocalIdentifier(){
        assert localIdentifier >= 0;
        assert !isField;
        return localIdentifier;
    }

    public void setLocalIdentifier(int localIdentifier){
        assert !isField;
        this.localIdentifier = localIdentifier;
    }

    public Symbol(String name, boolean isField) {
        this.name = name;
        this.isField = isField;
    }

    public Symbol(String name, String type, boolean isField) {
        this.name = name;
        this.isField = isField;
        this.type = type;
    }

    public String getType(){return type;}

    public String getName() { return name; }

    @Override
    public String toString() {
        return "Symbol{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", isField=" + isField +
                ", localIdentifier=" + localIdentifier +
                ", parameterListIdentifier=" + parameterListIdentifier +
                '}';
    }
}