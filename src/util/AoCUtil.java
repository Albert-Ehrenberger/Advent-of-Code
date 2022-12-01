package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class AoCUtil {

    private AoCUtil(){}

    public static List<List<String>> groupStrings(List<String> strings){
        List<List<String>> groupedStrings = new ArrayList<>();
        List<String> group = new ArrayList<>();
        for (String string: strings) {
            String  cleanString = string.replace("/n", "");
            if (cleanString.isBlank() || cleanString.isEmpty()){
                groupedStrings.add(group);
                group = new ArrayList<>();
            } else {
                group.add(cleanString);
            }
        }
        return groupedStrings;
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private static void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData(String fileName) throws IOException {
        ClassLoader classLoader = AoCUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        String data = readFromInputStream(inputStream);
        System.out.println(data);
    }
}
