package top.tocome.webservice.filemanager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileAttribute {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss yyyy-MM-dd");

    public FileAttribute(String path) {
        this(new File(path));
    }

    public FileAttribute(File file) {
        isDirectory = file.isDirectory();
        name = file.getName();
        path = file.getPath();
        size = file.length();
        time = dateFormat.format(new Date(file.lastModified()));
    }

    public boolean isDirectory;
    public String name;
    public String path;
    public long size;
    public String time;

    public static ArrayList<FileAttribute> getAll(String path) {
        ArrayList<FileAttribute> fileAttributes = new ArrayList<>();
        File[] files = new File(path).listFiles();
        for (File f : files) {
            fileAttributes.add(new FileAttribute(f));
        }
        return fileAttributes;
    }
}
