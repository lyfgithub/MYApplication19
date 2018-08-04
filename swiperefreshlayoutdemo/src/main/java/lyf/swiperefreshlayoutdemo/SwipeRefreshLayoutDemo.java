package lyf.swiperefreshlayoutdemo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

    }
}
