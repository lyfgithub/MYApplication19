package lyf.designsupportlibrary;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutDemo extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager vp_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayoutdemo);
        
        tabLayout = (TabLayout) findViewById(R.id.tl_tabs);
        vp_viewpager = (ViewPager) findViewById(R.id.vp_viewpager);

        List<String> titles=new ArrayList<String>();
        List<Fragment> fragments=new ArrayList<Fragment>();
        MyFragment myFragment;
        for (int i = 0; i <5 ; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("tab"+(i+1)));
            myFragment=new MyFragment("tab"+(i+1));
            fragments.add(myFragment);
            titles.add("tab"+(i+1));
        }
        MyFragmentStatePagerAdapter adapter=new MyFragmentStatePagerAdapter(this.getSupportFragmentManager(),fragments,titles);
        vp_viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp_viewpager);
        tabLayout.setTabsFromPagerAdapter(adapter);

//        MyPagerAdapter adapter2=new MyPagerAdapter(fragments);
//        vp_viewpager.setAdapter(adapter2);
//        tabLayout.setupWithViewPager(vp_viewpager);
    }

    class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter{
        List<Fragment> mFragments;
        List<String> mTitles;
        public MyFragmentStatePagerAdapter(FragmentManager fm,List<Fragment> fragments, List<String> titles) {
            super(fm);
            this.mFragments=fragments;
            this.mTitles=titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }


//    class MyPagerAdapter extends PagerAdapter{
//        List<Fragment> mFragments;
//        public MyPagerAdapter(List<Fragment> fragments) {
//            super();
//            this.mFragments=fragments;
//        }
//
//        @Override
//        public int getCount() {
//            return mFragments.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view==object;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//           // super.destroyItem(container, position, object);
//            container.removeView(mFragments.get(position).getView());
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            //return super.instantiateItem(container, position);
//            Fragment fragment= mFragments.get(position);
//            //TODO:fragment.getView()==null???
//            container.addView(fragment.getView());
//            return position;
//        }
//    }

//    class MyBaseAdapter extends BaseAdapter{
//        @Override
//        public int getCount() {
//            return 0;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            return null;
//        }
//    }
}
