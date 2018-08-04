package lyf.lyflove.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import lyf.lyf.tools.CrashCatcher;

/**
 * Author LYF
 * Created by Administrator on 2016/10/3.
 */
public class MyApplication extends Application {
    private static Context mContext;
    private static List<Activity> mActivities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        //异常日志
        CrashCatcherConfig();

        //ImageLoader插件配置
        MyImageLoaderConfiguration();
    }

    public static Context getContext() {
        return mContext;
    }

    public static void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mActivities.remove(activity);
    }

    public static void exitAll() {
        for (Activity activity : mActivities) {
            activity.finish();
        }
        System.exit(0);
    }

    //异常日志
    public void CrashCatcherConfig(){
        CrashCatcher crashCatcher = CrashCatcher.newInstance();
        crashCatcher.setDefaultCrashCatcher();
    }


    //==========================================================
    /** ImageLoader插件配置 */
    public void MyImageLoaderConfiguration(){
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getApplicationContext())
                .diskCacheFileCount(500)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加
                .build(); //开始构建
        //全局初始化此配置
        ImageLoader.getInstance().init(config);
    }
    //==========================================================
}
