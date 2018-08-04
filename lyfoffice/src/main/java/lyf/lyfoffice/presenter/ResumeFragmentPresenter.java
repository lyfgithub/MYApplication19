package lyf.lyfoffice.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import lyf.lyfoffice.base.Contents;
import lyf.lyfoffice.network.MyOkHttp;

/**
 * Author LYF
 * Created by Administrator on 2016/10/7.
 */
public class ResumeFragmentPresenter {
    Handler mHandler;
    Context mContext;

    public ResumeFragmentPresenter(Context mContext,Handler mHandler){
        this.mContext=mContext;
        this.mHandler=mHandler;
    }

    //加载数据
    public void loadResumeList(){
        MyOkHttp.doGetJsonString(Contents.Url_jianliList1,mHandler,Contents.Code_load);
    }

    public void refreshResumeData(){
        MyOkHttp.doGetJsonString(Contents.Url_jianliList1,mHandler,Contents.Code_Refresh);
    }

    int pageIndex=1;
    public void nextPageDataResume(){
        pageIndex++;
        String url=Contents.Url_jianliList1.replace("/pn1/","/pn"+pageIndex+"/");
        Log.d("ResumeFragment","url="+url);
        MyOkHttp.doGetJsonString(url,mHandler,Contents.Code_nextPage);
    }
}
