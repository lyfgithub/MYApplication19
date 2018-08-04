package lyf.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lyf.lyfdemos2.R;

/**
 * 复用视图
 */
public class RecyclerViewDemo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> listStr;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.rv_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter =new MyAdapter() );
        recyclerView.addItemDecoration(new DividerItemDecoration(RecyclerViewDemo.this,DividerItemDecoration.VERTICAL_LIST));
    }

    protected void initData(){
        listStr = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            listStr.add(""+(char)i);
        }
    }

    //数据适配
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater MyInflater= LayoutInflater.from(RecyclerViewDemo.this);
            //View item= MyInflater.inflate(R.layout.recycleview_item,null);  //第二个参数为null
            View item= MyInflater.inflate(R.layout.recycleview_item,parent,false);
            MyViewHolder holder = new MyViewHolder(item);

            return holder;
        }

        //指定的位置绑定的指定的数据
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText(listStr.get(position));
        }

        //子视图的个数
        @Override
        public int getItemCount() {
            return listStr.size();
        }

        //先写此类
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.textView= (TextView) itemView.findViewById(R.id.tv_item);
            }
        }
    }

}
