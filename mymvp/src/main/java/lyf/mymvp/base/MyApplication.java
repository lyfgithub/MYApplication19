package lyf.mymvp.base;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Author LYF
 * Created by Administrator on 2016/10/2.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MyImageLoaderConfiguration();
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
