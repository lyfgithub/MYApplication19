package lyf.lyfoffice.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import lyf.lyfoffice.R;

/**
 * Author LYF
 * Created by Administrator on 2016/10/3.
 */
public abstract class MyBaseActivty extends AppCompatActivity {
    String TAG="MyBaseActivty";
    private ImageLoader mImageLoader=ImageLoader.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.v(TAG, TAG+"=======onCreate");

        //注册广播
        mRegisterReceiver();
        //预先请求数据
        mPreInit();
        //找控件
        mfindViewById();
        //准备数据
        mInitData();
        //设置数据适配器
        msetAdapter();
        //设置各种监听、页面变动监听
        mListener();
    }

    //============================================
    //以下这些方法在子子类中并不能自动调用，还是需要在子类的OnCreate中手动调用
    //这里的只是模板
    /** 注册广播 */
    protected  void mRegisterReceiver(){}
    /** 预先请求数据 */
    protected  void mPreInit(){}
    /** 找控件 */
    protected  void mfindViewById(){}
    /** 准备数据 */
    protected  void mInitData(){}
    /** 设置数据适配器 */
    protected  void msetAdapter(){}
    /** 设置各种监听、页面变动监听 */
    protected void mListener(){}
    //=============================================


    /**
     * 在指定控件上显示指定的图片
     * @param iv
     * @param imgPath
     */
    public void ImageLoaderShow(ImageView iv, String imgPath){
        //===图片加载==================================================================
        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .showImageForEmptyUri(R.mipmap.ic_launcher)	// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) //设置下载的图片是否缓存在SD卡中
                .build();
        mImageLoader.displayImage(imgPath, iv, option);
        //=====================================================================
    }

}
