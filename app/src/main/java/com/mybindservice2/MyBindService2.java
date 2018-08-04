package com.mybindservice2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/** bind方式：
 * <pre>
 bind方式：
 特点：与activity绑定后，可以随时与Activity通信,[一损俱损，一荣俱荣]
 调用：
 Intent serviceintent=new Intent(this,MyBindService3.class);
 this.bindService(serviceintent, conn, Context.BIND_AUTO_CREATE);
 生命周期：
 调用端：activity.this.bindService(...) >>
 --------------------------------------
 *服务端：onCreate() >>
 *服务端：onBind() >>
 --------------------------------------
 调用端：onServiceConnected()
 --------------------------------------
 调用端：activity.this.unbindService(conn) >>
 --------------------------------------
 *服务端：onDestory()
 --------------------------------------

 总结：onCreate() >> onBind() >> onDestory()
 </pre>
 */
public class MyBindService2 extends Service {
    String Tag="MyBindService3";
    public MyBindService2() {
        Log.v(Tag,"MyBindService3()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(Tag,"onCreate()");
    }

    //bind方式不调用onStart()和onStartCommand()
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(Tag,"onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.v(Tag,"onBind()");
        return new MyMiddlePerson();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(Tag,"onDestroy()");
    }

    //中间人 //暴露方法给外面
    private class MyMiddlePerson extends Binder implements IMiddlePerson{
        /** 暴露的方法 */
        public void funInService2(int money){
            funInService(money);  //调用服务里面的自定义私有方法
        }

        /** 中间人的私有方法 */
        void MyMiddlePersonFun(){
               Toast.makeText(MyBindService2.this,"中间人私有方法！",Toast.LENGTH_SHORT).show();
        }
    }

    /** 服务中的方法  */
    private void funInService(int money){
        if(money>50) Toast.makeText(this, "证明办好了", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this,"多准备点钱！",Toast.LENGTH_SHORT).show();
    }









}
