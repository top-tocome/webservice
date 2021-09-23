package top.tocome.webservice.data;

import com.alibaba.fastjson.JSON;
import top.tocome.io.File;

/**
 * 配置文件
 */
public abstract class Config {

    /**
     * 数据文件保存位置
     */
    public static String dataPath = "data/";

    /**
     * 配置文件保存路径
     */
    protected String savePath = dataPath + getClass().getSimpleName() + ".json";

    public Config() {
    }

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
        return JSON.toJSONString(this);
    }

    protected <T extends Config> T parseConfigObject(Class<T> tClass) {
        return JSON.parseObject(File.read(savePath), tClass);
    }
}
