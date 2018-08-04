package lyf.swiperefreshlayout;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import lyf.lyfdemos2.R;

public class SwipeRefreshLayoutDemo extends AppCompatActivity {

    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout_demo);

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_SwipeRefreshLayout);

        //2.设置刷新动画的触发回调
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mySwipeRefreshLayout.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        mySwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mySwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // 通过 setEnabled(false) 禁用下拉刷新
        //mySwipeRefreshLayout.setEnabled(false);

        // 设定下拉圆圈的背景
        mySwipeRefreshLayout.setProgressBackgroundColor(android.R.color.holo_red_light);

        /* 设置手势下拉刷新的监听 */
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mySwipeRefreshLayout.setRefreshing(true);  //显示刷新动画
                        // 刷新动画开始后回调到此方法
                        Toast.makeText(SwipeRefreshLayoutDemo.this,"刷新",Toast.LENGTH_SHORT).show();
                        Snackbar.make(mySwipeRefreshLayout,"刷新中...",Snackbar.LENGTH_SHORT).show();
                        mySwipeRefreshLayout.setRefreshing(false); //关闭刷新动画
                    }
                }
        );

//
////        swipeRefreshLayout.setColorSchemeResources(
////                R.color.swipe_color_1,
////                R.color.swipe_color_2,
////                R.color.swipe_color_3,
////                R.color.swipe_color_4
////
////        );
//        //swipeRefreshLayout.setColorSchemeColors(0xff0000,0x00ff00,0x0000ff,0xff0000);
//
//        //swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
//        //swipeRefreshLayout.setProgressBackgroundColor(R.color.swipe_background_color);
//        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(0xff0000);
//        swipeRefreshLayout.setPadding(20, 20, 20, 20);
//        swipeRefreshLayout.setProgressViewOffset(true, 100, 200);
//        swipeRefreshLayout.setDistanceToTriggerSync(50);
//        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(SwipeRefreshLayoutDemo.this,"刷新",Toast.LENGTH_SHORT).show();
//                swipeRefreshLayout.setRefreshing(false);
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        data.clear();
////                        for(int i=0;i<20;i++){
////                            data.add("SwipeRefreshLayout下拉刷新"+i);
////                        }
////                        try {
////                            Thread.sleep(5000);
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
////                        mHandler.sendEmptyMessage(1);
////                    }
////                }).start();
//            }
//        });
//
//        //handler
////        Handler mHandler = new Handler(){
////            @Override
////            public void handleMessage(Message msg) {
////                super.handleMessage(msg);
////                switch (msg.what) {
////                    case 1:
////
////                        swipeRefreshLayout.setRefreshing(false);
////                        adapter.notifyDataSetChanged();
////                        //swipeRefreshLayout.setEnabled(false);
////                        break;
////                    default:
////                        break;
////                }
////            }
////        };
    }
}
