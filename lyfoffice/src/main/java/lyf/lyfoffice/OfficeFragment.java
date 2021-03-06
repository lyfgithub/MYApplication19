package lyf.lyfoffice;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import lyf.lyfoffice.adapater.MyRecyclerViewAdapter;
import lyf.lyfoffice.base.Contents;
import lyf.lyfoffice.presenter.OfficeFragmentPresenter;
import lyf.lyfoffice.tools.HtmlToList;
import lyf.lyfoffice.tools.NetworkUtils;


public class OfficeFragment extends Fragment {
    String TAG="OfficeFragment";
    private OfficeFragmentPresenter officeFragmentPresenter;
    private View view;
    List<HashMap<String,Object>> DataList;
    MyRecyclerViewAdapter MyRecyclerViewAdapterOffice;
    private SwipeRefreshLayout srl_swipeRefreshLayout1;
    private RecyclerView rv_recyclerView1;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Contents.Code_load:
                case Contents.Code_Refresh:
                    if(DataList==null){ //加载
                        srl_swipeRefreshLayout1.setRefreshing(false);
                        String jsonStr=msg.obj.toString();
                        DataList= HtmlToList.HtmlGetList(jsonStr);
                        MyRecyclerViewAdapterOffice = new MyRecyclerViewAdapter(getContext(),DataList);
                        rv_recyclerView1.setAdapter(MyRecyclerViewAdapterOffice);
                        rv_recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
                    }else { //刷新
                        srl_swipeRefreshLayout1.setRefreshing(false);
                        String jsonStr=msg.obj.toString();
                        DataList.clear();  //清空旧数据
                        DataList= HtmlToList.HtmlGetList(jsonStr);
                        MyRecyclerViewAdapterOffice = new MyRecyclerViewAdapter(getContext(),DataList);
                        rv_recyclerView1.setAdapter(MyRecyclerViewAdapterOffice);
                        rv_recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                    break;
                case Contents.Code_nextPage:
                    DataList.remove(DataList.size()-1); //移除标志数据(最后一项)
                    String jsonStr=msg.obj.toString();
                    List<HashMap<String,Object>> listTemp= HtmlToList.HtmlGetList(jsonStr);
                    if(listTemp!=null){
                        DataList.addAll(listTemp);
                        MyRecyclerViewAdapterOffice.notifyDataSetChanged();
                    }else {
                        Toast.makeText(getContext(),"没有数据了",Toast.LENGTH_SHORT).show();
                        Log.v(TAG,"总数据项DataList="+DataList.size());
                    }
                    loading=false;  //最后修改为加载完成
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate()");
        officeFragmentPresenter = new OfficeFragmentPresenter(this.getContext(),handler);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.officefragment,container,false);
        srl_swipeRefreshLayout1 = (SwipeRefreshLayout) view.findViewById(R.id.srl_SwipeRefreshLayout1);
        rv_recyclerView1 = (RecyclerView) view.findViewById(R.id.rv_RecyclerView1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated()");
        srl_swipeRefreshLayout1.post(new Runnable() {
            @Override
            public void run() {
                if(!NetworkUtils.isNetworkConnected(getContext())) {
                    Toast.makeText(getContext(),"网络开小差！",Toast.LENGTH_SHORT).show();
                    return;
                }
                srl_swipeRefreshLayout1.setRefreshing(true);
                officeFragmentPresenter.loadOfficeList();
            }
        });
        srl_swipeRefreshLayout1.setOnRefreshListener(new MySwipeRefreshLayoutOnRefreshListener());
        rv_recyclerView1.addOnScrollListener(new MyRecyclerViewOnScrollListener());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    //刷新监听
    class MySwipeRefreshLayoutOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if(!NetworkUtils.isNetworkConnected(getContext())) {
                Toast.makeText(getContext(),"网络开小差！",Toast.LENGTH_SHORT).show();
                return;
            }

            srl_swipeRefreshLayout1.setRefreshing(true);
            officeFragmentPresenter.refreshOfficeData();
        }
    }

    //上拉加载===============
    int totalItemCount=0;
    int lastVisibleItem=0;
    boolean bottom=false;
    boolean loading=false;
    //上拉滚动监听
    class MyRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            totalItemCount = layoutManager.getItemCount();
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();

            if (lastVisibleItem != totalItemCount - 1) {
                bottom = false;
            }
            //如果是在底部，也没有进行加载，当可见项剩余3项时，此时就可以加载下一页了
            //bottom 控制是否在底部
            //loading 防止短时间内 多次上拉加载
            //totalItemCount < (lastVisibleItem+3) 理解如果数据项总数为5，那么可见项lastVisibleItem为2时等式就可以成立了
            if (!bottom && !loading && totalItemCount < (lastVisibleItem + 3)) {
                loading = true;
                //=================================================
                if (DataList != null && DataList.size() > 0) {
                    DataList.add(null); //添加标志数据
                    // notifyItemInserted(int position)，这个方法是在第position位置
                    // 被插入了一条数据的时候可以使用这个方法刷新，
                    // 注意这个方法调用后会有插入的动画，这个动画可以使用默认的，也可以自己定义。
                    Log.v("LYF", "增加底部footer 圆形ProgressBar");

                    //mMyRecyclerViewAdapter.notifyDataSetChanged();
                    //通知数据集在最后一个位置插入的一个null数据
                    MyRecyclerViewAdapterOffice.notifyItemInserted(DataList.size() - 1);
                }
                if(!NetworkUtils.isNetworkConnected(getContext())) {
                    Toast.makeText(getContext(),"网络开小差！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //加载下一页
                //Toast.makeText(getContext(), "加载下一页", Toast.LENGTH_SHORT).show();
                officeFragmentPresenter.nextPageData();

                //=================================================
            }

        }
    }
}
