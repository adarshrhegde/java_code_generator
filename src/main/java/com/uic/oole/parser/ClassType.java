package com.uic.oole.parser;

public enum ClassType {

    classType("Class"), interfaceType("Interface"), abstractType("Abstract");

    private String type;

    ClassType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public ClassType[] getTypes(){
        return ClassType.values();
    }
}
