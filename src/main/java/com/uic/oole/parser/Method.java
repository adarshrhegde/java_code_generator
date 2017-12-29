package com.uic.oole.parser;

import java.util.*;

/**
 * Methods are uniquely both a symbol and a scope, 
 * since the can be referenced as symbols (a.f()),
 * and may contain symbols.
 */
public class Method extends Symbol implements Scope{
    private LinkedHashMap<String, Symbol> parameters = new LinkedHashMap<String, Symbol>();
    private Scope owner;
    private Scope body;
    private boolean abstractMethod;
    private boolean hasReturnStatement;
    private Map<String, Symbol> locals = new HashMap<String, Symbol>();
    private Map<String, Symbol> initializedVariables = new HashMap<String, Symbol>();

    public Method(){
        this.abstractMethod = false;
        this.hasReturnStatement = false;
    }

    public boolean getAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean abstractMethod) {
        this.abstractMethod = abstractMethod;
    }

    public boolean getHasReturnStatement() {
        return hasReturnStatement;
    }

    public void setHasReturnStatement(boolean hasReturnStatement) {
        this.hasReturnStatement = hasReturnStatement;
    }

    /**
     * Constructs a new symbol-table representation of a Method.
     * @param returnType The return type of this method representation
     * @param name the name of this method representation including "()",
     * 			   but without the rest of the parameter list.
     * @param owner The name of the class that has this method.
     */
    public Method(String returnType, String name, Scope owner){
        //methods are fields
        super(name, returnType, true);
        this.owner=owner;
    }

    @Override public String getScopeName(){
        return this.name;
    }

    @Override public Scope getEnclosingScope(){
        return owner;
    }

    @Override public void define(Symbol sym){
        locals.put(sym.getName(), sym);
    }

    @Override public void initialize(Symbol sym){
        initializedVariables.put(sym.getName(), sym);
    }

    @Override public Symbol lookup(String name){
        if(parameters.containsKey(name)){
            return parameters.get(name);
        }else if(locals.containsKey(name)){
            return locals.get(name);
        }else{
            return this.getEnclosingScope().lookup(name);
        }
    }

    @Override public Symbol lookupLocally(String name){
        if(parameters.containsKey(name)){
            return parameters.get(name);
        }else{
            return locals.get(name);
        }
    }

    @Override public boolean hasBeenInitialized(String name){
        if(initializedVariables.containsKey(name) || parameters.containsKey(name)){
            return true;
        }else{
            return this.getEnclosingScope() == null ? false : this.getEnclosingScope().hasBeenInitialized(name);
        }
    }


    @Override public Set<Symbol> getInitializedVariables(){
        return new HashSet<Symbol>(this.initializedVariables.values());
    }

    public void addParameter(Symbol parameter){
        parameters.put(parameter.getName(), parameter);
    }

    public List<Symbol> getParameterList(){
        return new ArrayList<Symbol>(parameters.values());
    }

    public List<String> getParameterListDefinition(){
        List<Symbol> parameterList = getParameterList();
        List<String> parameterListDefinition = new ArrayList<String>();
        for(Symbol var: parameterList){
            parameterListDefinition.add(var.getType());
        }
        return parameterListDefinition;
    }

    @Override
    public String toString() {
        return "Method{" +
                "parameters=" + parameters +
                ", owner=" + owner +
                ", body=" + body +
                ", locals=" + locals +
                ", initializedVariables=" + initializedVariables +
                '}';
    }

    /**
     * @return the full name of this method including return type and argument list.
     */
    public String fullName(){
        String fullName = this.getType().toString() + " ";
        fullName += name;
        fullName = fullName.substring(0, fullName.length()-1);
        boolean hasParameter = false;
        for(Symbol parameter : parameters.values()){
            fullName += parameter.getType() + ", ";
            hasParameter=true;
        }
        if(hasParameter){
            fullName = fullName.substring(0, fullName.length()-2);
        }
        fullName += ")";
        return fullName;
    }

    public Set<String> getVariableNamesInScope(){
        Set<String> variables = new HashSet<>();
        for(Map.Entry<String, Symbol> entry : initializedVariables.entrySet()){
            variables.add(entry.getValue().getName());
        }
        return variables;
    }

    /**
     * @return an asm Method representation of this method.
     */
}