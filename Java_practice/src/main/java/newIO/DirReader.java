package newIO;
import java.io.*;
import java.util.ArrayList;

public class DirReader {
    public static ArrayList<File> DirectoryReader(File file){
        ArrayList<File> temp = new ArrayList();
        File[] dirList = file.listFiles();
        for(File files:dirList) {
            if (files.isDirectory())
                temp.addAll(DirectoryReader(files));
            else
                temp.add(files);
        }
        return temp;
    };
}
