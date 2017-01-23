package request;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import request.secret.MD5Util;

/**
 * Created by gaojicai1 on 2017/1/17.
 */

public class BaseRequest {

    private static BaseRequest me;
    private static OkHttpClient sOkHttpClient;

    static {
        if (false) {
            sOkHttpClient = new OkHttpClient.Builder().build();
        } else {
            sOkHttpClient = new OkHttpClient();
        }
    }

    private BaseRequest() {
    }

    public static BaseRequest instance() {
        if (null == me) {
            synchronized (BaseRequest.class) {
                if (me == null) {
                    me = new BaseRequest();
                }
            }
        }
        return me;
    }

    public void requestObjectResult(String uri, Map<String, Object> argsMap, Type type, IRequest.IListener iListener) {

        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : argsMap.entrySet()) {
            formBody.add(entry.getKey(), entry.getValue() + "");
        }

        Request request = new Request.Builder()
                .url(uri)
                .post(formBody.build())
                .build();

        CallBackWrapper callBackWrapper = new CallBackWrapper(type, iListener);
        send(request, callBackWrapper);
    }

    private void send(Request request, Callback callback) {
        sOkHttpClient.newCall(request).enqueue(callback);
    }


    /**
     * 请求公共入参
     */
    public static Map<String, Object> getPublicParams() {
        String time = System.currentTimeMillis() + "";
        String name = "cqI4mAv2orQr3fJtTJgmaGbA3ifi8eR0" + time + "cqI4mAv2orQr3fJtTJgmaGbA3ifi8eR0";
        String sign = MD5Util.getMD5String(name);

        Map<String, Object> map = new HashMap<>();
        map.put("appid", "hiandroid");
        map.put("version", "3.3.4");
        map.put("timestamp", time);
        map.put("sign", sign);
        return map;
    }
}
