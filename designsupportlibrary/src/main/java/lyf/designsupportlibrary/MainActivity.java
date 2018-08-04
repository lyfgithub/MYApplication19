package lyf.designsupportlibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private FloatingActionButton floatingActionButton;
    private EditText editText;
    private TextInputLayout textInputLayout;
    private List<TextView> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tabLayout();
        tabLayoutViewPager();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbarShow();
                Toast.makeText(getApplicationContext(),"floatingActionButton", Toast.LENGTH_SHORT).show();
            }
        });

//        textInputLayout = (TextInputLayout) findViewById(R.id.til);
//        textInputLayout.setHint("请输入用户名");
//        editText = (EditText) findViewById(R.id.et_1);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.length()>5){
//                    textInputLayout.setError("长度超出5个了");
//                }else{
//                    textInputLayout.setError(null);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
    }

    public void snackbarShow(){
        final Snackbar snackbar= Snackbar.make(floatingActionButton,"弹出Snackbar",Snackbar.LENGTH_SHORT);
        snackbar.show();
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
    }


    public void tabLayout(){
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_tabs);
//        tabLayout.addTab(tabLayout.newTab().setText("tab1").setIcon(R.mipmap.ic_launcher));
//        tabLayout.addTab(tabLayout.newTab().setText("tab2"),true);
//        tabLayout.addTab(tabLayout.newTab().setText("tab3"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab4"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab5"));
    }

    public void tabLayoutViewPager(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp1);

        List<String> titles=new ArrayList<String>();
        views = new ArrayList<TextView>();
        for (int i=0;i<9;i++){
            tabLayout.addTab(tabLayout.newTab().setText("tab"+i));
            TextView textView = new TextView(this);
            textView.setText("tab"+i);
            views.add(textView);
        }
        MyPagerAdapter adapter=  new MyPagerAdapter(views);
        viewPager.setAdapter(adapter);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }
    class MyPagerAdapter extends PagerAdapter{
        List<TextView> listviews;
        public MyPagerAdapter(List<TextView> views){
            listviews=views;
        }
        @Override
        public int getCount() {
            return listviews.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            container.addView(listviews.get(position));
            return listviews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView(listviews.get(position));
        }
    }



}
