package smq.bw.com.smq0218;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import smq.bw.com.smq0218.adapter.MyAdapter;
import smq.bw.com.smq0218.bean.JsonBean;
import smq.bw.com.smq0218.presenter.ShowPresenter;
import smq.bw.com.smq0218.view.ShowView;

public class MainActivity extends AppCompatActivity implements ShowView {

    private RecyclerView ryl;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ryl = findViewById(R.id.ryl);

        showPresenter = new ShowPresenter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        ryl.setLayoutManager(linearLayoutManager);

        showPresenter.onRelated();


    }

    @Override
    public void getViewData(String json) {
        if (json!=null){
            Gson gson=new Gson();
            JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
            List<JsonBean.DataBeanX.DataBean> data = jsonBean.getData().getData();
            ryl.setAdapter(new MyAdapter(MainActivity.this,data));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.deatchView();
    }
}
