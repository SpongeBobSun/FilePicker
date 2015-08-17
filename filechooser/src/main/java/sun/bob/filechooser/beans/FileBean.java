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
    private String fullName;
    private boolean hasParent;
    private boolean folder;
    public FileBean(String fileName, boolean isFolder, String fullName){
        this.fileName = fileName;
        this.fullName = fullName;
        this.folder = isFolder;
        if (isFolder){
            icon = Constants.folderIcon;
        } else {
            icon = Constants.fileIcon;
        }
        if (fileName.equalsIgnoreCase("..")){
            icon = Constants.parentIcon;
        }
    }

    public Bitmap getIcon() {
        return icon;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isFolder() {
        return folder;
    }
}
