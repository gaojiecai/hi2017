package request;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import request.responseModel.Result;

/**
 * Created by gaojicai1 on 2017/1/18.
 */

public class CallBackWrapper implements Callback {

    IRequest.IListener mIListener;
    Type mType;
    Handler mHandler = new Handler(Looper.getMainLooper());

    public CallBackWrapper(Type type,IRequest.IListener IListener) {
        mType = type;
        mIListener = IListener;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.v("gjc", "onFailure");
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        String resStr = null;
        Result res = null;
        Gson gson = new Gson();

        Type type = new TypeToken<Result>() {
        }.getType();



        if (resStr==null){
            res = gson.fromJson(response.body().string(),type);
        }
        if (res.Code.equals("999")){
            res.$_result = gson.fromJson(res.Data,mType);
        }
        sendResult(res);

    }

    private void sendResult(Result res) {
        if (mIListener != null) {


            Message message = Message.obtain(mHandler, new MainThreadRunner(res, mIListener));
            message.sendToTarget();
        }
    }

    private static class MainThreadRunner implements Runnable {
        Result res;
        IRequest.IListener mIListener;

        public MainThreadRunner(Result res, IRequest.IListener IListener) {
            this.res = res;
            mIListener = IListener;
        }

        @Override
        public void run() {
            if (res != null) {
                mIListener.onReceiver(res.$_result);
            }
        }
    }

}
