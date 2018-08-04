package lyf.coordinatorlayoutdemo;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CoordinatorLayoutDemo extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinatorlayout_demo);

        setToolbar();

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.ctl_CollapsingToolbarLayout);
        collapsingToolbar.setTitle("Title");
    }

    //设置标题
    public void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.tb_Toolbar);
        toolbar.setTitle("这里是Title");
        toolbar.setSubtitle("这里是子标题");
        //toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);  //代替ActionBar

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  //设置返回上一级按钮可用
    }
}
