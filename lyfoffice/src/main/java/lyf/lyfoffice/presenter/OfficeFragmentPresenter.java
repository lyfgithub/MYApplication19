package lyf.lyfoffice.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import lyf.lyfoffice.base.Contents;
import lyf.lyfoffice.network.MyOkHttp;

/**
 * Author LYF
 * Created by Administrator on 2016/10/6.
 */
public class OfficeFragmentPresenter  {
    Handler mHandler;
    Context mContext;

    public OfficeFragmentPresenter(Context mContext,Handler mHandler){
        this.mContext=mContext;
        this.mHandler=mHandler;
    }

    //加载数据
    public void loadOfficeList(){
        MyOkHttp.doGetJsonString(Contents.Url_HaopinList2,mHandler,Contents.Code_load);
    }

    public void refreshOfficeData(){
        MyOkHttp.doGetJsonString(Contents.Url_HaopinList2,mHandler,Contents.Code_Refresh);
    }

    int pageIndex=1;
    public void  nextPageData(){
        pageIndex++;
        String url=Contents.Url_HaopinList1.replace("/pn1/","/pn"+pageIndex+"/");
        Log.d("OfficeFragment","url="+url);
        MyOkHttp.doGetJsonString(url,mHandler,Contents.Code_nextPage);
    }

}
