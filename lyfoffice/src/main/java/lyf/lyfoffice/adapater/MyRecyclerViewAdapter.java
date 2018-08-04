package lyf.lyfoffice.adapater;

/**
 * Author LYF
 * Created by Administrator on 2016/10/2.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.HashMap;
import java.util.List;

import lyf.lyfoffice.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter{
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    Context mContext;

    List<HashMap<String,Object>> list;

    public MyRecyclerViewAdapter(Context context, List<HashMap<String,Object>> list){
        this.mContext=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE_CONTENT){
            View itemView= LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item,parent,false);
            return new MyViewHolder(itemView);
        }
        else if(viewType==ITEM_TYPE_BOTTOM){
            View itemView= LayoutInflater.from(mContext).inflate(R.layout.recyclerview_footer,parent,false);
            return new MyFooterViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder;
        MyFooterViewHolder myFooterViewHolder;
        if(holder instanceof  MyViewHolder) {
            myViewHolder = ((MyViewHolder) holder);
            //myViewHolder.getVh_iv_img().setImageResource(R.mipmap.ic_launcher);
            //((MainActivityDemo) mContext).ImageLoaderShow(myViewHolder.getVh_iv_img(), list.get(position).get("img").toString());
            //myViewHolder.getVh_tv_title().setText(list.get(position).get("title").toString());
            myViewHolder.vh_item_tv_officeName.setText(list.get(position).get("workName").toString());
            myViewHolder.item_tv_company.setText(list.get(position).get("gongsiName").toString());
            myViewHolder.item_tv_officeAddress .setText(list.get(position).get("workAddress").toString());
            myViewHolder.item_tv_publicTime .setText(list.get(position).get("publicTime").toString());

            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,""+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(holder instanceof MyFooterViewHolder){
            myFooterViewHolder=((MyFooterViewHolder) holder);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        //Log.v("LYF","getItemViewType(int position)="+position);
        Object flagData= list.get(position);  //得到标志数据
        if(flagData==null){ //如果当前的数据项为空，它就是脚部类型数据
            return ITEM_TYPE_BOTTOM;
        }
        else{
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }


    //正常的item布局
    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView vh_item_tv_officeName;
        public TextView item_tv_company;
        public TextView item_tv_officeAddress;
        public TextView item_tv_publicTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            vh_item_tv_officeName=(TextView) itemView.findViewById(R.id.item_tv_officeName);
            item_tv_company=(TextView) itemView.findViewById(R.id.item_tv_company);
            item_tv_officeAddress=(TextView) itemView.findViewById(R.id.item_tv_officeAddress);
            item_tv_publicTime=(TextView) itemView.findViewById(R.id.item_tv_publicTime);
        }

    }

    //脚布局
    class MyFooterViewHolder extends RecyclerView.ViewHolder{
        public ProgressWheel  progressWheel;
        public MyFooterViewHolder(View itemView) {
            super(itemView);
            progressWheel= (ProgressWheel) itemView.findViewById(R.id.rcv_load_more);
        }
    }
}