package com.uic.oole.utility;

public enum IdentifierType {
    classIdentifier(Type.voidType), methodIdentifier(Type.voidType), variableIdentifier(Type.voidType), methodBody(Type.voidType), returnStatement(Type.voidType), unknown(Type.voidType);

    private Type type;

    IdentifierType(Type type){
        this.type = type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getTypeByValue(String value){
        for(Type type : Type.values()){
            if(type.returnType.equals(value)) {
                this.type = type;
                return type;
            }
        }
        return Type.voidType;
    }

    public Type getType(){
        return type;
    }

    public enum Type {

        voidType("void",""), intType("int","0"), floatType("float","0.0f"), charType("char","'a'"), shortType("short","0"), doubleType("double","0.0d") ,byteType("byte","0"), booleanType("boolean","true"), longType("long","0L");

        private String returnType;

        private String defaultValue;

        Type(String returnType, String defaultValue){
            this.returnType = returnType;
            this.defaultValue = defaultValue;
        }

        public String getValue(){
            return returnType;
        }

        public String getDefaultValue(){
            return defaultValue;
        }
    }

}
