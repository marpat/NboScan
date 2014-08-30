/*
 * FileRead.java, part of the NboScan project
 */
package scan;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * FileRead.java (UTF-8)
 *
 * Jun 3, 2014
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */
public class FileRead {

    public static ArrayList<String> readLinesB(String fileName) throws IOException {
        final ArrayList<String> content = new ArrayList<>();

        // Open the file that is the first
        FileInputStream fstream = new FileInputStream(fileName);
        try ( // Use DataInputStream to read binary NOT text.
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream, ENCODING.name()))) {
            String strLine;
            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Add from file to list
                content.add(strLine);
            }
        }
        return content;

    }

    // PRIVATE
    private final static Charset ENCODING = StandardCharsets.UTF_8;
}
