package top.tocome.webservice.data;

import com.alibaba.fastjson.JSONObject;

public class ResponseData {

    protected JSONObject object = new JSONObject();

    public ResponseData() {
        object.put("code", 0);
        object.put("message", "ok");
    }

    public ResponseData(int code, String message) {
        object.put("code", code);
        object.put("message", message);
    }

    public ResponseData put(String key, Object value) {
        object.put(key, value);
        return this;
    }

    public String toJSONString() {
        return object.toJSONString();
    }
}
