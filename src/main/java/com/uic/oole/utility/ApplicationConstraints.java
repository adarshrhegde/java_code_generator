package com.uic.oole.utility;

import com.uic.oole.languagecheck.JavaCodeGenerateServiceImpl;

/**
 * Application Constraints Class
 * This class is basically an object containing all the constrain variables as specified in the
 * description and their respective getter and setter methods.
 */
public class ApplicationConstraints {
    private int noOfInterfaces;
    private int maxNoOfMethodsPerInterface;
    private int noOfClasses;
    private int maxAllowedMethodCalls;
    private String allowIndirectRecursion;
    private int noOfInheritanceChains;
    private int maxNestedIfs;
    private int minNoOfClassFields;
    private String allowArray;
    private int maxInheritanceDepth;
    private int maxNoOfParametersPerMethod;
    private int minInheritanceDepth;
    private int totalLOC;
    private int maxInterfacesToImplement;
    private int maxRecursionDepth;
    private String classNamePrefix;
    private int maxNoOfClassFields;
    private int maxNoOfMethodsPerClass;
    private double recursionProbability;
    private int minNoOfParametersPerMethod;
    private int intMaxValue;
    private String typeInt;
    private String typeDouble;
    private String typeString;
    private String typeShort;
    private String typeObject;
    private String typeByte;
    private String typeChar;
    private String typeFloat;
    private String typeLong;

    public String getTypeInt() {
        return typeInt;
    }

    public void setTypeInt(String typeInt) {
        this.typeInt = typeInt;
    }

    public String getTypeDouble() {
        return typeDouble;
    }

    public void setTypeDouble(String typeDouble) {
        this.typeDouble = typeDouble;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeShort() {
        return typeShort;
    }

    public void setTypeShort(String typeShort) {
        this.typeShort = typeShort;
    }

    public String getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(String typeObject) {
        this.typeObject = typeObject;
    }

    public String getTypeByte() {
        return typeByte;
    }

    public void setTypeByte(String typeByte) {
        this.typeByte = typeByte;
    }

    public String getTypeChar() {
        return typeChar;
    }

    public void setTypeChar(String typeChar) {
        this.typeChar = typeChar;
    }

    public String getTypeFloat() {
        return typeFloat;
    }

    public void setTypeFloat(String typeFloat) {
        this.typeFloat = typeFloat;
    }

    public String getTypeLong() {
        return typeLong;
    }

    public void setTypeLong(String typeLong) {
        this.typeLong = typeLong;
    }

    public int getNoOfInterfaces() {
        return noOfInterfaces;
    }

    public void setNoOfInterfaces(int noOfInterfaces) {
        this.noOfInterfaces = noOfInterfaces;
    }

    public int getMaxNoOfMethodsPerInterface() {
        return maxNoOfMethodsPerInterface;
    }

    public void setMaxNoOfMethodsPerInterface(int maxNoOfMethodsPerInterface) {
        this.maxNoOfMethodsPerInterface = maxNoOfMethodsPerInterface;
    }

    public int getNoOfClasses() {
        return noOfClasses;
    }

    public void setNoOfClasses(int noOfClasses) {
        this.noOfClasses = noOfClasses;
    }

    public int getMaxAllowedMethodCalls() {
        return maxAllowedMethodCalls;
    }

    public void setMaxAllowedMethodCalls(int maxAllowedMethodCalls) {
        this.maxAllowedMethodCalls = maxAllowedMethodCalls;
    }

    public String getAllowIndirectRecursion() {
        return allowIndirectRecursion;
    }

    public void setAllowIndirectRecursion(String allowIndirectRecursion) {
        this.allowIndirectRecursion = allowIndirectRecursion;
    }

    public int getNoOfInheritanceChains() {
        return noOfInheritanceChains;
    }

    public void setNoOfInheritanceChains(int noOfInheritanceChains) {
        this.noOfInheritanceChains = noOfInheritanceChains;
    }

    public int getMaxNestedIfs() {
        return maxNestedIfs;
    }

    public void setMaxNestedIfs(int maxNestedIfs) {
        this.maxNestedIfs = maxNestedIfs;
    }

    public int getMinNoOfClassFields() {
        return minNoOfClassFields;
    }

    public void setMinNoOfClassFields(int minNoOfClassFields) {
        this.minNoOfClassFields = minNoOfClassFields;
    }

    public String getAllowArray() {
        return allowArray;
    }

    public void setAllowArray(String allowArray) {
        this.allowArray = allowArray;
    }

    public int getMaxInheritanceDepth() {
        return maxInheritanceDepth;
    }

    public void setMaxInheritanceDepth(int maxInheritanceDepth) {
        this.maxInheritanceDepth = maxInheritanceDepth;
    }

    public int getMaxNoOfParametersPerMethod() {
        return maxNoOfParametersPerMethod;
    }

    public void setMaxNoOfParametersPerMethod(int maxNoOfParametersPerMethod) {
        this.maxNoOfParametersPerMethod = maxNoOfParametersPerMethod;
    }

    public int getMinInheritanceDepth() {
        return minInheritanceDepth;
    }

    public void setMinInheritanceDepth(int minInheritanceDepth) {
        this.minInheritanceDepth = minInheritanceDepth;
    }

    public int getTotalLOC() {
        return totalLOC;
    }

    public void setTotalLOC(int totalLOC) {
        this.totalLOC = totalLOC;
    }

    public int getMaxInterfacesToImplement() {
        return maxInterfacesToImplement;
    }

    public void setMaxInterfacesToImplement(int maxInterfacesToImplement) {
        this.maxInterfacesToImplement = maxInterfacesToImplement;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public void setMaxRecursionDepth(int maxRecursionDepth) {
        this.maxRecursionDepth = maxRecursionDepth;
    }

    public String getClassNamePrefix() {
        return classNamePrefix;
    }

    public void setClassNamePrefix(String classNamePrefix) {
        this.classNamePrefix = classNamePrefix;
    }

    public int getMaxNoOfClassFields() {
        return maxNoOfClassFields;
    }

    public void setMaxNoOfClassFields(int maxNoOfClassFields) {
        this.maxNoOfClassFields = maxNoOfClassFields;
    }

    public int getMaxNoOfMethodsPerClass() {
        return maxNoOfMethodsPerClass;
    }

    public void setMaxNoOfMethodsPerClass(int maxNoOfMethodsPerClass) {
        this.maxNoOfMethodsPerClass = maxNoOfMethodsPerClass;
    }

    public double getRecursionProbability() {
        return recursionProbability;
    }

    public void setRecursionProbability(Double recursionProbability) {
        this.recursionProbability = recursionProbability;
    }

    public int getMinNoOfParametersPerMethod() {
        return minNoOfParametersPerMethod;
    }

    public void setMinNoOfParametersPerMethod(int minNoOfParametersPerMethod) {
        this.minNoOfParametersPerMethod = minNoOfParametersPerMethod;
    }

    public int getIntMaxValue() {
        return intMaxValue;
    }

    public void setIntMaxValue(int intMaxValue) {
        this.intMaxValue = intMaxValue;
    }

    public boolean reached(){
        try{
            if(null != JavaCodeGenerateServiceImpl.keyCallCount.get("<interface type list>") && JavaCodeGenerateServiceImpl.keyCallCount.get("<interface type list>") < getMinInheritanceDepth() )return true;
            if (null != JavaCodeGenerateServiceImpl.keyCallCount.get("<interface type list>") && JavaCodeGenerateServiceImpl.keyCallCount.get("<interface type list>") > getNoOfInterfaces()) return false;
            if (null != JavaCodeGenerateServiceImpl.keyCallCount.get("<class declaration with public>") && JavaCodeGenerateServiceImpl.keyCallCount.get("<class declaration with public>") > getNoOfClasses()) return false;
            return true;
        }
        catch(Exception | Error e){
            return true;
        }
    }
}
