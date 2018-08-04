package lyf.mymvp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import lyf.mymvp.base.MyBaseActivity;
import lyf.mymvp.presenter.MainActivityPresenter;
import lyf.mymvp.utils.JsonParser;
import lyf.mymvp.view.IMainActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends MyBaseActivity {
    private MainActivityPresenter mMainActivityPresenter;
    private TextView textView;
    private RecyclerView rv_recyclerView;
    List<HashMap<String, Object>> list;
    MyRecyclerViewAdapter mMyRecyclerViewAdapter;
    private SwipeRefreshLayout srl_swipeRefreshLayout;
    MySwipeRefreshLayoutOnRefreshListener mySwipeRefreshLayoutOnRefreshListener;

    android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 123:  //下一页
                    list.remove(list.size() - 1); //移除标志数据
                    list.addAll((List<HashMap<String, Object>>) msg.obj);
                    mMyRecyclerViewAdapter.notifyDataSetChanged();
                    loading = false;
                    break;
                case 111: //刷新
                    if(list!=null){
                        srl_swipeRefreshLayout.setRefreshing(false);
                        list.clear();
                        list.addAll((List<HashMap<String, Object>>) msg.obj);
                        mMyRecyclerViewAdapter.notifyDataSetChanged();
                    }
                    break;
                default:  //加载
                    srl_swipeRefreshLayout.setRefreshing(false);
                    list= (List<HashMap<String, Object>>) msg.obj;
                    rv_recyclerView.setAdapter(mMyRecyclerViewAdapter=new MyRecyclerViewAdapter(MainActivity.this,list));
                    rv_recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainActivityPresenter = new MainActivityPresenter(new MyIMainActivity());

        textView = (TextView) findViewById(R.id.tv_textview);
        mMainActivityPresenter.saveData();

        srl_swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_SwipeRefreshLayout);
        srl_swipeRefreshLayout.setOnRefreshListener(mySwipeRefreshLayoutOnRefreshListener=new MySwipeRefreshLayoutOnRefreshListener());
        //mySwipeRefreshLayoutOnRefreshListener.onRefresh();
        //加载动画
        srl_swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                srl_swipeRefreshLayout.setRefreshing(true);
            }
        });

        rv_recyclerView = (RecyclerView) findViewById(R.id.rv_RecyclerView);
        mMainActivityPresenter.getRecycleViewData();

        //添加滑动监听
        rv_recyclerView.addOnScrollListener(new MyRecyclerViewOnScrollListener());


    }

    //业务数据
    class MyIMainActivity implements IMainActivity {
        @Override
        public void setTV(String text) {
            textView.setText(text);
        }
        @Override
        public void loadDataRecycleView(List<HashMap<String, Object>> list) {
//            rv_recyclerView.setAdapter(new MyRecyclerViewAdapter(list));
//            rv_recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            Message message= Message.obtain();
            message.obj=list;
            handler.sendMessage(message);
        }
    }

    class MySwipeRefreshLayoutOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {
            srl_swipeRefreshLayout.setRefreshing(true);
            RefreshData();
        }
    }

    public void RefreshData(){
        String url="http://news.at.zhihu.com/api/4/news/before/20161002";
        GetNextData(url,handler,111);  //刷新
    }





//    class MyRecyclerViewAdapter extends RecyclerView.Adapter{
//
//        List<HashMap<String,Object>> list;
//
//        public MyRecyclerViewAdapter(List<HashMap<String,Object>> list){
//            this.list=list;
//        }
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View itemView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item,parent,false);
//                return new MyViewHolder(itemView);
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            MyViewHolder myViewHolder;
//                myViewHolder = ((MyViewHolder) holder);
//                //myViewHolder.getVh_iv_img().setImageResource(R.mipmap.ic_launcher);
//                ImageLoaderShow(myViewHolder.getVh_iv_img(), list.get(position).get("img").toString());
//                myViewHolder.getVh_tv_title().setText(list.get(position).get("title").toString());
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return list.size();
//        }
//
//        //正常的item布局
//        class MyViewHolder extends RecyclerView.ViewHolder {
//            private ImageView vh_iv_img;
//            private TextView vh_tv_title;
//
//            public MyViewHolder(View itemView) {
//                super(itemView);
//                setVh_iv_img((ImageView) itemView.findViewById(R.id.item_iv_img));
//                setVh_tv_title((TextView) itemView.findViewById(R.id.item_tv_title));
//            }
//            public void setVh_tv_title(TextView vh_tv_title) {
//                this.vh_tv_title = vh_tv_title;
//            }
//            public void setVh_iv_img(ImageView vh_iv_img) {
//                this.vh_iv_img = vh_iv_img;
//            }
//
//            public ImageView getVh_iv_img() {
//                return vh_iv_img;
//            }
//
//            public TextView getVh_tv_title() {
//                return vh_tv_title;
//            }
//        }
//    }


    int totalItemCount=0;
    int lastVisibleItem=0;
    private boolean bottom = false;
    private boolean loading = false;
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
                if (list != null && list.size() > 0) {
                    list.add(null); //添加标志数据
                    // notifyItemInserted(int position)，这个方法是在第position位置
                    // 被插入了一条数据的时候可以使用这个方法刷新，
                    // 注意这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义。
                    Log.v("LYF","增加底部footer 圆形ProgressBar");

                    //mMyRecyclerViewAdapter.notifyDataSetChanged();
                    mMyRecyclerViewAdapter.notifyItemInserted(list.size() - 1);
                }

                //加载下一页
                Toast.makeText(MainActivity.this,"加载下一页",Toast.LENGTH_SHORT).show();
                LoadNextPagerData();

            }
        }
    }

    private void LoadNextPagerData(){
        Log.v("LYF","=====加载下一页======");
        //new MyAsyncTaskNextPagerData().execute();
        String pathUrl="http://news.at.zhihu.com/api/4/news/before/20161001";
        GetNextData(pathUrl,handler,123);
    }

//    class MyAsyncTaskNextPagerData extends AsyncTask<String,Nullable,List<HashMap<String,Object>>> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            //增加底部的一个null数据，表示ProgressBar
//            if (list != null && list.size() > 0) {
//                list.add(null);
//                // notifyItemInserted(int position)，这个方法是在第position位置
//                // 被插入了一条数据的时候可以使用这个方法刷新，
//                // 注意这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义。
//                Log.v("LYF","增加底部footer 圆形ProgressBar");
//
//                mMyRecyclerViewAdapter.notifyItemInserted(list.size() - 1);
//            }
//        }
//
//        @Override
//        protected List<HashMap<String, Object>> doInBackground(String... params) {
//            //return null;
//            List<HashMap<String,Object>> listTemp=GetNextData();
//            list.addAll(listTemp);
//
//            return list;
//        }
//
//        @Override
//        protected void onPostExecute(List<HashMap<String, Object>> listTemp) {
//            super.onPostExecute(listTemp);
//            if (list.size() == 0) {
//                list.addAll(listTemp);
//                mMyRecyclerViewAdapter.notifyDataSetChanged();
//            } else {
//                //删除 footer
//                list.remove(list.size() - 1);
//
//                Log.v("LYF", "下拉增加数据 " + listTemp);
//
//                //只有到达最底部才加载
//                //防止上拉到了倒数两三个也加载
//                if (!bottom && lastVisibleItem == totalItemCount - 1 && listTemp.size() == 0) {
////                    Snackbar.with(mActivity.getApplicationContext()) // context
////                            .text(mActivity.getResources().getString(R.string.list_no_data)) // text to display
////                            .duration(Snackbar.SnackbarDuration.LENGTH_SHORT) // make it shorter
////                            .show(mActivity); // activity where it is displayed
//                    bottom = true;
//                }
//
//                list.addAll(listTemp);
//                mMyRecyclerViewAdapter.notifyDataSetChanged();
//
//                loading = false;
//            }
//        }
//    }


    public void GetNextData(final String pathUrl, final Handler handler, final int handlerCode){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            //.url("http://news.at.zhihu.com/api/4/news/before/20161001")
                            .url(pathUrl)
                            .build();

                    Response response= client.newCall(request).execute();
                    String jsonStr=response.body().string().toString();
                    List<HashMap<String,Object>> listTemp= JsonParser.getList(jsonStr);
                    Message message=Message.obtain();
                    message.obj=listTemp;
                    message.what=handlerCode;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }



}
