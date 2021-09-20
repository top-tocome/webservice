package top.tocome.webservice.data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 要传输的文件属性
 */
public class FileAttribute {
    /**
     * {@link #time}的格式化方式
     */
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

    /**
     * 该文件是否为目录
     */
    public boolean isDirectory;
    /**
     * 文件名称
     */
    public String name;
    /**
     * 文件路径
     */
    public String path;
    /**
     * 文件大小
     */
    public long size;
    /**
     * 文件上次修改时间
     */
    public String time;

    /**
     * 获取一个目录下的所有文件的属性
     *
     * @param path 文件夹路径
     */
    public static ArrayList<FileAttribute> getAll(String path) {
        ArrayList<FileAttribute> fileAttributes = new ArrayList<>();
        File[] files = new File(path).listFiles();
        for (File f : files) {
            fileAttributes.add(new FileAttribute(f));
        }
        return fileAttributes;
    }
}
