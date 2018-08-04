package lyf.fragmentdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fun2();
    }

//    public void fun1(){
//        FragmentManager FM= this.getSupportFragmentManager();
//        Fragment fragment1= FM.findFragmentById(R.id.f_fragment1);
//        //FM.beginTransaction().add(fragment1,"fragment1").commit();  //错误显示 已经添加
//        //FM.beginTransaction().show(fragment1).commit();
//        //FM.beginTransaction().hide(fragment1).commit();
//    }


    //代码方式将Fragment添加到Activity
    public void fun2(){
        MyFragment2 MyFragment2=new MyFragment2();
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.rl_container,MyFragment2)
                .show(MyFragment2).commit();
    }





}
