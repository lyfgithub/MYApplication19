package lyf.lyfoffice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lyf.lyfoffice.base.MyBaseActivty;
import lyf.lyfoffice.presenter.LYFOfficePresenter;
import lyf.lyfoffice.tools.HtmlToList;

public class LYFOffice extends MyBaseActivty {
    private LYFOfficePresenter lyfOfficePresenter;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 123:
                    String jsonStr=msg.obj.toString();
                    //tv_text.setText(jsonStr);
                     HtmlToList.HtmlGetList(jsonStr);
                case 1234:
                    String jsonStr2=msg.obj.toString();
                    //tv_text.setText(jsonStr2);
                    HtmlToList.HtmlGetList58Resume(jsonStr2);
                    break;
                default:
                    break;
            }

        }
    };
    private TextView tv_text;
    private TabLayout tl_tabLayout;
    private ViewPager vp_viewPager;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyfoffice);
        lyfOfficePresenter = new LYFOfficePresenter(this,handler);

        mPreInit();
        mfindViewById();
        mInitData();
    }

    @Override
    protected void mPreInit() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new OfficeFragment());
        fragmentList.add(new ResumeFragment());
    }

    @Override
    protected void mfindViewById() {
        //tv_text = (TextView) findViewById(R.id.tv_text);
        //Tab
        tl_tabLayout = (TabLayout) findViewById(R.id.tl_TabLayout);
        tl_tabLayout.addTab(tl_tabLayout.newTab().setText("招聘"));
        tl_tabLayout.addTab(tl_tabLayout.newTab().setText("简历"));
        //ViewPager
        vp_viewPager = (ViewPager) findViewById(R.id.vp_ViewPager);
        vp_viewPager.setAdapter(new MyFragmentStatePagerAdapter(this.getSupportFragmentManager(),fragmentList));
        tl_tabLayout.setupWithViewPager(vp_viewPager);
    }

    @Override
    protected void mInitData() {
        //lyfOfficePresenter.getDataList();
    }


    //Tablayout和Viewpager的适配
    class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter{
        List<Fragment> fragmentList = null;
        String[] titles={"58招聘","简历"};

        public MyFragmentStatePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList=fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    public void log(String TAG,String str){
        if (str.length() > 4000) {
            Log.v(TAG, "sb.length = " + str.length());
            int chunkCount = str.length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= str.length()) {
                    Log.v(TAG, "chunk " + i + " of " + chunkCount + ":"+ str.substring(4000 * i));
                } else {
                    Log.v(TAG, "chunk " + i + " of " + chunkCount + ":" + str.substring(4000 * i, max));
                }
            }
        } else {
            Log.v(TAG, str.toString());
        }
    }
}
