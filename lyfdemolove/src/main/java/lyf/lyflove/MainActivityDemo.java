package lyf.lyflove;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import lyf.lyf.tools.NetworkUtils;
import lyf.lyflove.adapter.MyRecyclerViewAdapter;
import lyf.lyflove.base.Contents;
import lyf.lyflove.base.MyBaseActivty;
import lyf.lyflove.presenter.MainActivityDemoPresenter;

public class MainActivityDemo extends MyBaseActivty {
    private MainActivityDemoPresenter mMainActivityDemoPresenter;
    private SwipeRefreshLayout srl_swipeRefreshLayout;
    //recyclerView============
    private RecyclerView rv_recyclerView;
    MyRecyclerViewAdapter mMyRecyclerViewAdapter;
    List<HashMap<String,Object>> ListMap;
    //下拉加载===============
    int totalItemCount=0;
    int lastVisibleItem=0;
    private boolean bottom = false;
    private boolean loading = false;
    //=================
    private MySwipeRefreshLayoutOnRefreshListener mySwipeRefreshLayoutOnRefreshListener;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Contents.Code_load: //加载
                    srl_swipeRefreshLayout.setRefreshing(false);
                    ListMap= (List<HashMap<String, Object>>) msg.obj;
                    rv_recyclerView.setAdapter(mMyRecyclerViewAdapter=new MyRecyclerViewAdapter(MainActivityDemo.this,ListMap));
                    rv_recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityDemo.this));
                    break;
                case Contents.Code_Refresh: //刷新
                    srl_swipeRefreshLayout.setRefreshing(false);
                    if (ListMap != null) ListMap.clear();
                    if (ListMap != null) ListMap.addAll((List<HashMap<String, Object>>) msg.obj);
                    mMyRecyclerViewAdapter.notifyDataSetChanged();
                    break;
                case Contents.Code_nextPage:  //下一页
                    ListMap.remove(ListMap.size() - 1); //移除标志数据(最后一项)
                    ListMap.addAll((List<HashMap<String, Object>>) msg.obj);
                    mMyRecyclerViewAdapter.notifyDataSetChanged();
                    loading = false;
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainActivityDemoPresenter = new MainActivityDemoPresenter(this,handler);  //控制人

        mfindViewById();
        mListener();
    }

    @Override
    protected void mfindViewById() {
        srl_swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_SwipeRefreshLayout);
        rv_recyclerView = (RecyclerView) findViewById(R.id.rv_RecyclerView);
        //加载动画
        srl_swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(!NetworkUtils.isNetworkConnected(MainActivityDemo.this)) {
                    Toast.makeText(MainActivityDemo.this,"网络开小差！",Toast.LENGTH_SHORT).show();
                    return;
                }
                srl_swipeRefreshLayout.setRefreshing(true);
                mMainActivityDemoPresenter.LoadData();  //加载数据
            }
        });
    }

    @Override
    protected void mListener() {
        Log.d("LYF","=======mListener()");
        //添加下拉刷新
        srl_swipeRefreshLayout.setOnRefreshListener(mySwipeRefreshLayoutOnRefreshListener =new MySwipeRefreshLayoutOnRefreshListener());
        //添加上拉滑动监听
        rv_recyclerView.addOnScrollListener(new MyRecyclerViewOnScrollListener());
    }


    //下拉刷新
    class MySwipeRefreshLayoutOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {
            if(!NetworkUtils.isNetworkConnected(MainActivityDemo.this)) {
                Toast.makeText(MainActivityDemo.this,"网络开小差！",Toast.LENGTH_SHORT).show();
                return;
            }
            srl_swipeRefreshLayout.setRefreshing(true);
            mMainActivityDemoPresenter.refreshData();  //加载数据
        }
    }

    //上拉加载
    class MyRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            totalItemCount = layoutManager.getItemCount();
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();

            if (lastVisibleItem != totalItemCount - 1) {
                bottom = false;
            }
            //3表示 控制还剩3项新闻的时候加载更多
            //
            if (!bottom && !loading && totalItemCount < (lastVisibleItem + 3)) {
                loading = true;

                //增加底部的一个null数据，表示ProgressBar
                if (ListMap != null && ListMap.size() > 0) {
                    ListMap.add(null); //添加标志数据
                    // notifyItemInserted(int position)，这个方法是在第position位置
                    // 被插入了一条数据的时候可以使用这个方法刷新，
                    // 注意这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义。
                    Log.v("LYF","增加底部footer 圆形ProgressBar");

                    //mMyRecyclerViewAdapter.notifyDataSetChanged();
                    mMyRecyclerViewAdapter.notifyItemInserted(ListMap.size() - 1);
                }


                if(!NetworkUtils.isNetworkConnected(MainActivityDemo.this)) {
                    Toast.makeText(MainActivityDemo.this,"网络开小差！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //加载下一页
                Toast.makeText(MainActivityDemo.this,"加载下一页",Toast.LENGTH_SHORT).show();
                mMainActivityDemoPresenter.nextPageData();
            }
        }
    }



}
