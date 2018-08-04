package lyf.mymvp.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import lyf.mymvp.R;

/**
 * Author LYF
 * Created by Administrator on 2016/10/2.
 */
public abstract class MyBaseActivity extends AppCompatActivity {
    private ImageLoader mImageLoader=ImageLoader.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

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
