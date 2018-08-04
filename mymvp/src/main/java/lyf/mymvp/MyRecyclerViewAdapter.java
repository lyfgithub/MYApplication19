package lyf.mymvp;

/**
 * Author LYF
 * Created by Administrator on 2016/10/2.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.HashMap;
import java.util.List;

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
            View itemView= LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
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
            ((MainActivity) mContext).ImageLoaderShow(myViewHolder.getVh_iv_img(), list.get(position).get("img").toString());
            myViewHolder.getVh_tv_title().setText(list.get(position).get("title").toString());

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
        Log.v("LYF","getItemViewType(int position)="+position);
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
        private ImageView vh_iv_img;
        private TextView vh_tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            setVh_iv_img((ImageView) itemView.findViewById(R.id.item_iv_img));
            setVh_tv_title((TextView) itemView.findViewById(R.id.item_tv_title));
        }
        public void setVh_tv_title(TextView vh_tv_title) {
            this.vh_tv_title = vh_tv_title;
        }
        public void setVh_iv_img(ImageView vh_iv_img) {
            this.vh_iv_img = vh_iv_img;
        }

        public ImageView getVh_iv_img() {
            return vh_iv_img;
        }

        public TextView getVh_tv_title() {
            return vh_tv_title;
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