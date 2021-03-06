package top.tocome.webservice.data;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回给前端数据结构
 * <p>
 * 固定参数：<br>
 * code: 0为请求成功，其他为请求失败<br>
 * message: 错误消息
 */
public class ResponseData {
    /**
     * json对象
     */
    protected JSONObject object = new JSONObject();

    /**
     * 默认为失败状态
     */
    public ResponseData() {
        this(Error.Failed);
    }

    public ResponseData(int code, String message) {
        object.put("code", code);
        object.put("message", message);
    }

    public ResponseData(Error error) {
        this(error.getCode(), error.getMessage());
    }

    /**
     * 新增要传输的数据
     *
     * @param key   关键词
     * @param value 可序列化对象
     * @return 链式调用
     */
    public ResponseData put(String key, Object value) {
        object.put(key, value);
        return this;
    }

    /**
     * 更改错误消息
     */
    public void setError(Error error) {
        object.put("code", error.getCode());
        object.put("message", error.getMessage());
    }

    /**
     * 序列化为json字符串
     */
    public String toJSONString() {
        return object.toJSONString();
    }
}
