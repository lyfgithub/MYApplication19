package lyf.lyflove.presenter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import lyf.lyflove.base.Contents;
import lyf.lyflove.network.MyOkHttp;

/**
 * Author LYF
 * Created by Administrator on 2016/10/3.
 */
public class MainActivityDemoPresenter {
    Context mContext;
    Handler mHandler;
    public MainActivityDemoPresenter(Context mContext, Handler mHandler){
        this.mContext=mContext;
        this.mHandler=mHandler;
    }

    /** 加载数据 */
    public void LoadData(){
        String url= Contents.UrlBase+getCurrentDate();
        MyOkHttp.doGet(url,mHandler,Contents.Code_load);
    }

    /** 刷新数据 */
    public void refreshData(){
        String url= Contents.UrlBase+getCurrentDate();
        MyOkHttp.doGet(url,mHandler,Contents.Code_Refresh);
    }

    /**下一页数据 */
    public void nextPageData(){
        String url= Contents.UrlBase+getDateBefore();
        MyOkHttp.doGet(url,mHandler,Contents.Code_nextPage);
    }

    public String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
//        calendar.getTime();
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
       String date= new SimpleDateFormat("yyyyMMdd", Locale.US).format(calendar.getTime()).toString();
        Log.w("LYF","date="+date);
        return date;
    }

    int index=0;
    public String getDateBefore(){
        Calendar calendar = Calendar.getInstance();
//        calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, --index);
        String date= new SimpleDateFormat("yyyyMMdd", Locale.US).format(calendar.getTime()).toString();
        Log.w("LYF","date="+date);
        return date;
    }


}
