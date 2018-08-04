package lyf.lyfresource;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_search;
    private Button btn_StartSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

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

    private void mPreInit() {
    }

    private void mfindViewById() {
        et_search = (EditText) findViewById(R.id.et_search);
        btn_StartSearch = (Button) findViewById(R.id.btn_StartSearch);
    }

    private void mInitData() {
        // TODO Auto-generated method stub

    }

    private void msetAdapter() {
        // TODO Auto-generated method stub

    }

    private void mListener() {
        btn_StartSearch.setOnClickListener(new MyOnClickListener());

    }

    class MyOnClickListener implements View.OnClickListener{
        @SuppressLint("ShowToast")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_StartSearch:
                    String param=et_search.getText().toString();
                    if("".equals(param)){
                        Toast.makeText(getApplicationContext(), "请输入搜索关键字！", 0).show();
                    }else{
                        Intent intent=new Intent(MainActivity.this,SearchShow.class);
                        intent.putExtra("param", param);
                        MainActivity.this.startActivity(intent);
                    }
                    break;
                default:
                    break;
            }

        }

    }
}
