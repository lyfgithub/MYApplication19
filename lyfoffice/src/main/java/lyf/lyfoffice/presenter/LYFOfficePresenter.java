package lyf.lyfoffice.presenter;

import android.content.Context;
import android.os.Handler;

/**
 * Author LYF
 * Created by Administrator on 2016/10/5.
 */
public class LYFOfficePresenter {
    Context mContext;
    Handler mHandler;
    public LYFOfficePresenter(Context mContext, Handler mHandler){
        this.mContext=mContext;
        this.mHandler=mHandler;
    }

//    public void getDataList(){
//        //MyOkHttp.doGetJsonString(Contents.Url_HaopinList1,mHandler,123);
//        MyOkHttp.doGetJsonString(Contents.Url_jianliList1,mHandler,1234);
//    }


}
