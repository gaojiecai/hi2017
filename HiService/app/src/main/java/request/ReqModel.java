package request;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaojicai1 on 2017/1/18.
 */

public class ReqModel {

    String url;
    Map<String, Object> argsMap;
    Type mType;
    IRequest.IListener mIListener;

    public static ReqModel newRequest() {
        return new ReqModel();
    }

    /**
     * 设置请求Url
     */
    public ReqModel url(String url) {
        this.url = url;
        return this;
    }

    /**
     * 设置post请求参数
     */
    public ReqModel args(String key, Object value) {
        if (argsMap == null) {
            argsMap = new HashMap<>();
        }
        argsMap.put(key, value);

        return this;
    }

    /**
     * 设置接口返回对象
     */
    public ReqModel type(Type type) {
        this.mType = type;
        return this;
    }

    /**
     * 设置回调监听
     */
    public ReqModel iListener(IRequest.IListener iListener) {
        this.mIListener = iListener;
        return this;
    }

    /**
     * 发起请求
     */
    public void send() {
        Map<String, Object> map = new HashMap<>();
        map.putAll(BaseRequest.getPublicParams());
        if (argsMap != null) {
            map.putAll(argsMap);
        }

        BaseRequest.instance().requestObjectResult(url, map, mType, mIListener);
    }
}
