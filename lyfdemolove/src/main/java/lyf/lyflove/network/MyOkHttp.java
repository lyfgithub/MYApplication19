package lyf.lyflove.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lyf.lyflove.tools.JsonParser;

/**
 * Author LYF
 * Created by Administrator on 2016/10/3.
 */
public class MyOkHttp {
    static String TAG="MyOkHttp";


    /**
     * 同步执行的Get
     * @param url
     * @param handler
     * @param handlerCode
     */
    public static void doGet(final String url, final Handler handler, final int handlerCode) {
        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    OkHttpClient client = new OkHttpClient();
                    client.setConnectTimeout(5, TimeUnit.SECONDS); //设置超时
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response= client.newCall(request).execute();  //同步执行
                    String jsonStr=response.body().string().toString();

                    List<HashMap<String,Object>> list= JsonParser.getList(jsonStr);

                    Message message=Message.obtain();
                    message.what=handlerCode;
                    message.obj=list;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.v(TAG,"异常：doGet()");
                }
            }
        }.start();

    }
}
