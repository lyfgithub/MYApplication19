package com.myapplication19;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * <pre>
 start启动方式的服务
 特点：启动后就与启动者没有联系了
 调用方式：activity.this.startService(New Intent(this,MyService.class));
 生命周期：
 activity.this.startService(New Intent(this,MyService.class));
 ------------------------------------------------------------
 onCreate()[只创建一次] >>
 onStart()[过时方法,参与重复调用]  >>
 onStartCommand()[新方法,参与重复调用] >>
 ------------------------------------------------------------
 activity.this.stopService(New Intent(this,MyService.class));
 ------------------------------------------------------------
 onDestory()[销毁服务]
 ------------------------------------------------------------
 </pre>
 */
public class MyStartService extends Service {
    String Tag="MyStartService";
    public MyStartService() {
        Log.v(Tag,"MyStartService()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(Tag,"onCreate()");
    }

    //start方式不调用onBind()
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(Tag,"onBind()");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(Tag,"onStartCommand()");
       // return super.onStartCommand(intent, flags, startId);
        return START_STICKY;  //如果被杀死就重新启动
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(Tag,"onDestroy()");
    }

}
