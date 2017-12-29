package com.uic.oole.utility;

import javafx.util.Pair;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;

public class GeneralUtils {

    public static <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    public static boolean storeFileToFileSystem(Pair<String,String> fileNameAndContent){

        String path = PropertiesUtils.getProperties().getProperty("generatedCodeLocation");
        File baseDirectory;
        String[] packages = fileNameAndContent.getKey().toString().split("\\.(?=[^.]*\\.)");
        for(String packageName : packages){

            path += "\\" + packageName;
            baseDirectory = new File(path);

            if(!packageName.contains(".java")) {
                if (!baseDirectory.exists())
                    baseDirectory.mkdir();
            }else {

                try {
                    if(!baseDirectory.exists())
                        baseDirectory.createNewFile();
                    FileUtils.writeStringToFile(baseDirectory,fileNameAndContent.getValue(), Charset.defaultCharset());

                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}


