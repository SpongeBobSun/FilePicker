package sun.bob.filechooser.beans;

import android.graphics.Bitmap;

/**
 * Created by bob.sun on 15/8/16.
 */
public class FileBean {
    private Bitmap icon;
    private String fileName;
    public FileBean(String fileName){
        this.fileName = fileName;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public String getFileName() {
        return fileName;
    }
}
