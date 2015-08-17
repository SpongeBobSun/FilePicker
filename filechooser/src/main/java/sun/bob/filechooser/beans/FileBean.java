package sun.bob.filechooser.beans;

import android.graphics.Bitmap;

import java.io.File;

import sun.bob.filechooser.utils.Constants;

/**
 * Created by bob.sun on 15/8/16.
 */
public class FileBean {
    private Bitmap icon;
    private String fileName;
    public FileBean(String fileName, boolean isFolder){
        this.fileName = fileName;
        if (isFolder){
            icon = Constants.folderIcon;
        } else {
            icon = Constants.fileIcon;
        }
    }

    public Bitmap getIcon() {
        return icon;
    }

    public String getFileName() {
        return fileName;
    }
}
