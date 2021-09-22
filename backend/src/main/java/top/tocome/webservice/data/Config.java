package top.tocome.webservice.data;

import org.yaml.snakeyaml.Yaml;
import top.tocome.io.File;

import java.util.ArrayList;

/**
 * 配置文件
 */
public abstract class Config {
    /**
     * 自动保存的配置文件
     */
    public static final ArrayList<Config> autoSaveConfig = new ArrayList<>();

    /**
     * 数据文件保存位置
     */
    public static String dataPath = "data/";

    /**
     * 配置文件保存路径
     */
    protected String savePath = dataPath + getClass().getSimpleName() + ".yml";

    public Config() {
        load();
        autoSaveConfig.add(this);
    }

    protected Yaml yaml = new Yaml();

    /**
     * 从文件加载数据
     */
    public void load() {
        if (new File(savePath).exists())
            try {
                this.getClass().getField("Instance").set(this, parseConfigObject(getClass()));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
    }

    /**
     * 保存数据到文件
     */
    public void save() {
        File.write(savePath, serializeToString().getBytes());
    }

    protected String serializeToString() {
        return yaml.dumpAsMap(this);
    }

    protected <T extends Config> T parseConfigObject(Class<T> tClass) {
        return yaml.loadAs(File.read(savePath), tClass);
    }
}
