package sun.bob.filechooser.utils;

import java.util.ArrayList;

/**
 * Created by bob.sun on 15/8/17.
 */
public class Results {
    private static Results staticInstance;
    private ArrayList <String> files;
    private Results(){
        files = new ArrayList<>();
    }
    
    public static Results getInstance() {
        if (staticInstance == null)
            staticInstance = new Results();
        return staticInstance;
    }

    public Results putFile(String filePath){
        files.add(filePath);
        return this;
    }

    public ArrayList<String> getResults() {
        return files;
    }

    public Results removeFile(String filePath){
        files.remove(filePath);
        return this;
    }

    public boolean contains(String filePath){
        return files.contains(filePath);
    }
}
