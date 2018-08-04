package com.myapplication19;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 *
 */
public class MyStartServiceDemo extends Activity {

    private Button btn_start;
    private Button btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.btn_Start);
        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyStartServiceDemo.this,MyStartService.class);
                MyStartServiceDemo.this.startService(intent);
            }
        });


        btn_stop = (Button) findViewById(R.id.btn_Stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyStartServiceDemo.this,MyStartService.class);
                MyStartServiceDemo.this.stopService(intent);
            }
        });

    }


}
