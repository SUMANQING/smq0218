package smq.bw.com.smq0218.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import smq.bw.com.smq0218.model.ShowModel;
import smq.bw.com.smq0218.view.ShowView;

/**
 * Time:2019/2/18
 * <p>
 * Author:Lenovo
 * <p>
 * Description:
 */
public class ShowPresenter<T> {

    private final ShowModel showModel;
    private final ShowView showView;
    private Reference<T> reference;

    public ShowPresenter(ShowView view) {
        showModel = new ShowModel();
        showView = view;
    }

    public void attachView(T t){
        reference=new WeakReference<T>(t);
    }

    public void onRelated() {
        showModel.getHttpData();
        showModel.setOnShowListener(new ShowModel.onShowListener() {
            @Override
            public void onResult(String json) {
                showView.getViewData(json);
            }
        });
    }

    public void  deatchView(){
        if (reference!=null){
            reference.clear();
            reference=null;
        }
    }
}
