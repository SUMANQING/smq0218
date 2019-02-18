package smq.bw.com.smq0218.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import smq.bw.com.smq0218.R;
import smq.bw.com.smq0218.bean.JsonBean;

/**
 * Time:2019/2/18
 * <p>
 * Author:Lenovo
 * <p>
 * Description:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<JsonBean.DataBeanX.DataBean> data;
    private int TYPE_one=0;
    private int TYPE_two=1;

    DisplayImageOptions options=new DisplayImageOptions.Builder()
            .showImageOnFail(R.drawable.ic_launcher_background)
            .cacheOnDisk(true)
            .cacheInMemory(true)
            .displayer(new RoundedBitmapDisplayer(20))
            .build();
    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    public MyAdapter(Context context, List<JsonBean.DataBeanX.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       if (TYPE_one==i){
           View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,null,false);
           ViewHolder viewHolder=new ViewHolder(view);
           return  viewHolder;
       }else{
            View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list2,null,false);
            ViewHolder viewHolder=new ViewHolder(view);
            return  viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int itemViewType=getItemViewType(i);
        if (TYPE_one==itemViewType){
            viewHolder.textView.setText(data.get(i).getTitle());
            Glide.with(context).load(data.get(i).getPics()).into(viewHolder.imageView);
        }else {
            viewHolder.textView1.setText(data.get(i).getTitle());
          //  Glide.with(context).load(data.get(i).getLink()).into(viewHolder.imageView1);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       // private final ImageView imageView1;
        private final ImageView imageView;
        private final TextView textView1;
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            textView1 = itemView.findViewById(R.id.name1);
            imageView = itemView.findViewById(R.id.img);
           // imageView1 = imageView.findViewById(R.id.img1);
        }
    }
}
