package cn.com.hiserivice.hiservice;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import cn.com.hiserivice.hiservice.adapter.BrannerAdapter;
import cn.com.hiserivice.hiservice.vo.HomeData;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import request.IRequest;
import request.ReqModel;

public class MainActivity extends AppCompatActivity {


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        request();
        postRequest();
        upLoad();
        newRequest();

    }


    /**
     * okhttp get request
     */
    private void request() {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个Request对象
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        //new call
        Call call = okHttpClient.newCall(request);

        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //mTextView.setText(res);
                    }

                });

            }
        });
    }


    /**
     * okhttp post request
     */
    private void postRequest() {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("vic", "gjc")
                .build();

        Request request = new Request.Builder()
                .url("http://my.wonderfull.url/to/post")
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("gjc", "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("gjc", "onResponse:" + response.body().string());
            }
        });
    }


    /**
     * 基于http的文件上传
     */
    private void upLoad() {
        OkHttpClient okHttpClient = new OkHttpClient();

        File file = new File(Environment.getExternalStorageDirectory(), "balabala.mp4");

        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        RequestBody requestBody1 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"username\""),
                        RequestBody.create(null, "张鸿洋"))/*
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"mFile\";filename=\"wjd.mp4\""), fileBody)*/
                .build();

        Request request = new Request.Builder()
                .url("http://my.wonderfull.url/to/post")
                .post(requestBody1)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("gjc", "upLoad onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("gjc", "upLoad onResponse" + response.body().string());
            }
        });
    }


    /**
     * 封装okhttp后的请求
     */
    private void newRequest() {

        ReqModel.newRequest()
                .url("http://api.hiservice.com.cn/api/v3.3.0")
                .args("imei", "")
                .args("action", "appIndex")
                .args("uid", "")
                .type(HomeData.class)
                .iListener(new IRequest.IListener<HomeData>() {
                    @Override
                    public void onReceiver(HomeData obj) {

                        BrannerAdapter brannerAdapter = new BrannerAdapter(MainActivity.this, obj.getBannerList());
                        mViewPager.setAdapter(brannerAdapter);
                    }

                    @Override
                    public void onFailed() {

                    }
                })
                .send();
    }

}
