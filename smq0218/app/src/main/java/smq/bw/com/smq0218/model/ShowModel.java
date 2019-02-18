package smq.bw.com.smq0218.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import smq.bw.com.smq0218.utils.OkHttpUtils;

/**
 * Time:2019/2/18
 * <p>
 * Author:Lenovo
 * <p>
 * Description:
 */
public class ShowModel {
    private String url="http://365jia.cn/news/api3/365jia/news/headline?page=1";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json= (String) msg.obj;
                    Log.i("xxx",json);
                    onShowListener.onResult(json);
                    break;
            }
        }
    };

    public void getHttpData() {
        OkHttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);
            }
        });
    }

    public interface onShowListener{
        void onResult(String json);
    }
    public onShowListener onShowListener;

    public void setOnShowListener(ShowModel.onShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }
}
