package com.uic.oole.utility;

import com.uic.oole.languagecheck.JavaCodeGenerateServiceImpl;
import com.uic.oole.parser.CClass;
import com.uic.oole.parser.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * All the utils for generating the program from the grammar
 */
public class ProgramGeneratorUtils {

    static Random random = new Random();

    public static String generateIdentifierForVariables(JavaCodeGenerateServiceImpl javaCodeGenerateService){

        if(javaCodeGenerateService.getMethod() != null && javaCodeGenerateService.getcClass() != null){
                return getVariableNameInScope(javaCodeGenerateService);
        } else
            return RandomStrGen.randomStrGenerator();
    }

    private static String getVariableNameInScope(JavaCodeGenerateServiceImpl javaCodeGenerateService) {
        CClass cClass = javaCodeGenerateService.getcClass();
        Method method = javaCodeGenerateService.getMethod();

        Set<String> variableNames = cClass.getVariableNamesInScope();
        List<String> variableNameList = new ArrayList<>(variableNames);
        for(String str : method.getVariableNamesInScope())
            variableNameList.add(new String(str));
        if(variableNameList.size() == 0)
            return RandomStrGen.randomStrGenerator();
        int num = random.nextInt(variableNameList.size());
        return variableNameList.get(num);
    }
}
