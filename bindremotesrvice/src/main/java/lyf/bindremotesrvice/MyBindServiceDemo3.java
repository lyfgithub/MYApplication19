package lyf.bindremotesrvice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.mybindservice3.IMiddlePerson;


public class MyBindServiceDemo3 extends Activity {
    String Tag="MyBindServiceDemo3";
    MyConn conn=new MyConn();
    IMiddlePerson myMiddlePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bindsevicedemo);
    }

    //绑定服务
    public void myBindService(View v){
        Intent intent=new Intent();
        intent.setAction("com.mybindservice3.MyBindService3");
        MyBindServiceDemo3.this.bindService(intent,conn,BIND_AUTO_CREATE);
    }

    //解除绑定服务
    public void myUnBindService(View v){
        if(conn!=null) MyBindServiceDemo3.this.unbindService(conn);
    }

    //调用服务中方法
    public void myCallServiceFun(View v){
        Log.v(Tag,"myCallServiceFun()");
            try {
                myMiddlePerson.funInService2(55);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.v(Tag,"RemoteException:"+e.getMessage());
            }
    }

    //中间人
    class MyConn implements ServiceConnection{
        //服务连接成功时
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v(Tag,"onServiceConnected()连接成功！");
           // myMiddlePerson= (IMiddlePerson) iBinder;  //得到中间人
            myMiddlePerson=IMiddlePerson.Stub.asInterface(iBinder); //得到中间人
        }

        //连接被断开
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v(Tag,"onServiceDisconnected()连接断开！");
        }
    }



}
