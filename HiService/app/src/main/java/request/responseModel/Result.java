package request.responseModel;

import com.google.gson.JsonElement;

/**
 * Created by gaojicai1 on 2017/1/20.
 */

public class Result<T> {
    public String Code;
    public JsonElement Data;
    public T $_result;
}
