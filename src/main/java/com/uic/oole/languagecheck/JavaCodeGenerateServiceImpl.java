package com.uic.oole.languagecheck;

import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;
import com.uic.oole.utility.*;

import javafx.util.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Name: JavaCodeGenerateService implementation
 * This is a service that will be used in generating a java code
 */
public class JavaCodeGenerateServiceImpl implements CodeGenerateService {

    ApplicationConstraints ac = new ApplicationConstraints();
    XmlFileReader xmlFileReader = new XmlFileReader(ac);

    public final String START_SYMBOL = "<compilation unit>";
    int count = 0;
    public static Map<String,Integer> keyCallCount = new HashMap<>();
    static public Map<String,List<String>> identifier = new HashMap<>();

    static public List<String> interfacelist = new ArrayList<> ();
    static public List<String> methodlist = new ArrayList<>();
    static public List<String> variablelist = new ArrayList<>();
    static public List<String> classlist = new ArrayList<>();
    static public List<String> packageList = new ArrayList<>();
    static public List<String> finalclasslist = new ArrayList<>();
    static public List<String> integerlist = new ArrayList<>();
    static boolean finalclass = false;

    public String getStartSymbol(){
        return START_SYMBOL;
    }

    private CClass cClass;

    private Method method;

    private IdentifierType identifierType;

    static String generatedClassName = "className";

    private List<String> keyStack = new ArrayList<>();
    Random random = new Random();


    public JavaCodeGenerateServiceImpl() {
        identifierType = IdentifierType.unknown;
    }

    public JavaCodeGenerateServiceImpl(CClass cClass, Method method, IdentifierType identifierType) {
        this.cClass = cClass;
        this.method = method;
        this.identifierType = identifierType;
    }

    public CClass getcClass() {
        return cClass;
    }

    public Method getMethod() {
        return method;
    }

    public IdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(IdentifierType identifierType) {
        this.identifierType = identifierType;
    }

    /**
     * generate code for the non terminal "key" using the grammar map
     * @param key
     * @param grammarMap
     * @return
     */
    @Override
    public String generateCodeForNonTerminal(String key, Map<String, List<String>> grammarMap) {

        if ((key.equals ("<identifier>")) || (key.equals ("<input character>" ))) {
            if(identifierType == IdentifierType.variableIdentifier)
                return ProgramGeneratorUtils.generateIdentifierForVariables(this);
            else
                return RandomStrGen.randomStrGenerator();
        } else if(key.equals("<block>") && identifierType.equals(IdentifierType.methodBody)){
            if(!identifierType.getType().equals(IdentifierType.Type.voidType)) {
                String body = "{ " + identifierType.getType().getValue();
                String defaultValue = identifierType.getType().getDefaultValue();
                identifierType = IdentifierType.variableIdentifier;
                String identifierName = generateCodeForNonTerminal("<identifier>", grammarMap);
                body += " " + identifierName + "=" + defaultValue + "; return " + identifierName + "; }";
                return body;
            } else {
                return "{}";
            }

        } else if(key.equals("<return>") && identifierType.equals(IdentifierType.returnStatement)){

            return "return " + identifierType.getType().getDefaultValue() + ";";
        }

        if (key == "<class declaration with final>")
            finalclass = true;

        if ((key .equals ("<variableIdentifier>"))) {
            String randgen = RandomStrGen.randomStrGenerator();
            return randgen;
        }

        if ((key .equals ("<intIdentifier>"))) {
            String randgen = RandomStrGen.randomStrGenerator();
            integerlist.add(randgen);
            identifier.put("integerIdentifier", integerlist);
            return randgen;
        }

        if ((key .equals ("<digits>"))) {
            Integer randgen = RandomIntGen.randomIntGenerator();
            return randgen.toString();
        }

        if ((key .equals ("<identifier>")) || (key.equals ("<input character>" ))) {
            String randgen = RandomStrGen.randomStrGenerator();
            variablelist.add(randgen);
            identifier.put("variable",variablelist);
            return randgen;
        }

        if ((key .equals ("<classIdentifier>"))) {
            String randgen= new StringBuilder().append(ac.getClassNamePrefix()).append(RandomStrGen.randomStrGenerator()).toString();
            classlist.add(randgen);
            identifier.put("class",classlist);
            generatedClassName = randgen;
            return randgen;
        }

        if ((key .equals ("<finalClassIdentifier>"))) {
            String randgen = new StringBuilder().append(ac.getClassNamePrefix()).append(RandomStrGen.randomStrGenerator()).toString();
            finalclasslist.add(randgen);
            identifier.put("finalclass",finalclasslist);
            generatedClassName = randgen;
            return randgen;
        }

        if ((key .equals ("<packageIdentifier>"))) {
            String randgen = RandomStrGen.randomStrGenerator();
            packageList.add(randgen);
            identifier.put("package",packageList);
            return randgen;
        }

        if ((key .equals ("<interfaceIdentifier>"))) {
            String randgen = RandomStrGen.randomStrGenerator();
            interfacelist.add(randgen);
            identifier.put("interface",interfacelist);
            return randgen;
        }

        if ((key .equals ("<methodIdentifier>"))) {
            String randgen = RandomStrGen.randomStrGenerator();
            methodlist.add(randgen);
            identifier.put("method", methodlist);
            return randgen;
        }

        if((key .equals ("<prevDeclaredClass>"))) {
            try {
                if (classlist.size() > 1)
                    return "extends " + classlist.get(classlist.size() - 2);
                else
                    return "";
            }

            catch (Exception e){
                return "";
            }
        }

        if((key .equals ("<prevDeclaredInterface>"))) {
            try {
                if (interfacelist.size() > 1) {
                    return "implements " + interfacelist.get(interfacelist.size() - 2);
                }
                else
                    return "";
            }
            catch (Exception | Error e){
                return "";
            }
        }

        if((key .equals ("<simple type name>"))) {
            try {
                return generatedClassName;
                /*if (finalclass == true) {
                    finalclass = false;
                    System.out.println("Simple name" + finalclasslist.get(finalclasslist.size() - 1));
                    return finalclasslist.get(finalclasslist.size() - 1);
                } else {
                    System.out.println("Simple name" + classlist.get(classlist.size() - 1));
                    return classlist.get(classlist.size() - 1);
                }*/
            }catch (Exception | Error e){
                return "";
            }
        }

        List<String> rhsList = grammarMap.get(key.trim());
        if(rhsList == null){
            System.out.println("No grammar rule defined for >>" + key);
        }

        /**
         * get a random number for choosing any one of the values associated with the key
         */
        int number = random.nextInt(rhsList.size()) + 0;
        updateAndGetCountForRecursiveCalls(key);

        /**
         * obtain a mapping for the key randomly from the grammar of mappings
         Eg <class-declaration> -> <method-declaration> | <variable-declaration>, choose one from method-declaration and
         variable-declaration
         */
        String rhs = rhsList.get(number);
        // if a item can't be recursively traversed further then return
        if(!rhs.contains("<") && !rhs.contains(">"))
            return rhs;

        /**
         * Match the pattern <new_key_name> and <new_key_name>?
         */
        String patternString = "(<.*?>)(\\?)?";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(rhs);
        StringBuffer stringBuffer = new StringBuffer();

        while(matcher.find()) {

            StringBuilder str = new StringBuilder("");
            for(int i = matcher.start(); i <= matcher.end()-1; i++){
                if(rhs.charAt(i) != '?')
                    str.append(rhs.charAt(i));
            }

            /**
             * recursive call for <new_key_name>
             */
            String replacement = generateCodeForNonTerminal(str.toString(),grammarMap);
            matcher.appendReplacement(stringBuffer, replacement);
        }

        matcher.appendTail(stringBuffer);

        //System.out.println("Recursive call for " + key + " produces " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    /**
     * This method calculates the number of recursive calls made for a <non-terminal> and also increments it
     * This information is being maintained to help in limiting the amount of code generated
     * @param key
     * @return
     */
    public int updateAndGetCountForRecursiveCalls(String key) {

        int count;
        if(keyCallCount.containsKey(key)){
            count = keyCallCount.get(key);
            keyCallCount.put(key,new Integer(++count));
        } else {
            keyCallCount.put(key, 1);
            count = 1;
        }
        return count;
    }
}