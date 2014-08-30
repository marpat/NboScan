/**
 *
 * @author Marcel Patek
 * @version 1
 * @since June 22 2013
 */

package scan;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ParseDir {

    public static ArrayList DirExt(String path_nbo, String suff, String file_name) {
        String files;

        File folder = new File(path_nbo);
        File[] listOfFiles = folder.listFiles();
        ArrayList dirfiles = new ArrayList();

        // Debug print
        // System.out.println("Printing list of files: ");

        //End debug print
        String f2move = "^(.+)?" + file_name + "\\w?" + suff + ".+";
       // System.out.println("Regex: " + f2move + "\n");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();
                //System.out.println(files.matches("test.+" + suff.toString() +".+"));
                try {
                    Pattern regex = Pattern.compile(f2move, Pattern.MULTILINE);
                    Matcher regexMatcher = regex.matcher(files);
                    if (regexMatcher.find()) {
                        dirfiles.add(files);
                    } else {
                        // Match attempt failed
                    }
                } catch (PatternSyntaxException ex) {
                    // Syntax error in the regular expression
                }
            }
        }

        //Print it
        //System.out.println("Printing list of extensions: "); //print
        // System.out.println("Final list of ext going to MoveDel: " + dirfiles); // print
        return dirfiles;
    }
}