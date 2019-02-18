package smq.bw.com.smq0218.utils;

import android.util.Log;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Time:2019/2/18
 * <p>
 * Author:Lenovo
 * <p>
 * Description:
 */
public class OkHttpUtils {
    private OkHttpUtils() {};

    private static OkHttpUtils okHttpUtils=null;

    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            okHttpUtils=new OkHttpUtils();
        }
        return okHttpUtils;
    }

    //设置拦截器
    public static Interceptor MyInterceptor(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);
            }
        });
        return interceptor;
    }

    public static void doGet(String url, Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(MyInterceptor())
                .build();
        Request request=new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void doPost(String url, Map<String,String> params,Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(MyInterceptor())
                .build();
        FormBody.Builder builder=new FormBody.Builder();
        for(String key:params.keySet()){
            builder.add(key,params.get(key));
        }
        Request request=new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
