package com.uic.oole.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * reads the grammar file and provides the grammar expressions
 * that is passed into the compilation unit
 */
public class GrammarMapGenerator {

    public static Map<String,List<String>> grammarMap = new HashMap<>();

    public static void buildGrammarMap(){

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(("java_grammar.txt")));
            String s = null;
            Set<String> optionalConstructs = new HashSet<>();
            /**
             * maintain a set of optional constructs in the grammar. Eg <expression>?
             */

            while((s = reader.readLine()) != null){
                if(s == "" || s == null || s.length() == 0)
                    continue;
                String[] statement = s.split("::=");
                List<String> rhs = new ArrayList<>();
                String rhsExpression = statement[1].trim();

                if(rhsExpression.contains("|")) {
                    String[] pipeSeparatedArray = rhsExpression.split("\\|");
                    for(String exp : pipeSeparatedArray){
                        rhs.add(exp.trim());
                    }
                } else {
                    rhs.add(rhsExpression.trim());
                }

                for (String exp : rhs){
                    checkOptionalConstructs(exp,optionalConstructs);
                }
                if(optionalConstructs.contains(statement[0].trim())){
                    rhs.add(" ");
                }
                //System.out.println(statement[0].trim() + "==" + rhs);
                grammarMap.put(statement[0].trim(), rhs);
            }
            //.out.println(grammarMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkOptionalConstructs(String exp, Set<String> optionalConstructs) {

        String patternString = "(<.*?>)(\\?)";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(exp);

        while(matcher.find()) {
            StringBuilder str = new StringBuilder("");
            for(int i = matcher.start(); i <= matcher.end()-2; i++){
                str.append(exp.charAt(i));
            }
            optionalConstructs.add(str.toString());
        }
    }
}
