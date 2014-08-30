/**
 *
 * @author Marcel Patek
 * @version 1
 * @since June 22 2013
 */

package scan;

import java.io.File;

public class FileExists {

    public static boolean Confirm(String directory, String file, String file_ext) {

        String dire = directory.replace("/", "\\");
        //System.out.println("dire is: "+ dire);

        File dir = new File(dire);
        String f1 = dir + "\\" + file + "." + file_ext;
        //System.out.println("f1 is: "+f1);

        File f = new File(f1);
        boolean fexists = f.exists();

        // System.out.println(fexists);
        return fexists;

    }
}
