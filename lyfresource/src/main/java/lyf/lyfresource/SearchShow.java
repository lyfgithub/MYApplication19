package lyf.lyfresource;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lyf.network.MyHttpClient;

/**
 * Author LYF
 * Created by Administrator on 2016/10/24.
 */

public class SearchShow extends Activity {
    String tag="MainActivity";
    String searchParam="";
    private ListView lv;
    MyBaseAdapter myBaseAdapter;
    List<Map<String, Object>> listMap2;

    Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            String html=msg.obj.toString();
            //tv_msg.setText(html);
            listMap2=getListMapDate(html);
            if(listMap2==null || listMap2.size()<=0){

            }else{
                myBaseAdapter=new MyBaseAdapter(listMap2);
                lv.setAdapter(myBaseAdapter);
            }
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //预先请求数据
        mPreInit();
        //找控件
        mfindViewById();
        //准备数据
        mInitData();
        //设置数据适配器
        msetAdapter();
        //设置各种监听、页面变动监听
        mListener();
    }

    private void mPreInit() {
        Intent intent= this.getIntent();
        searchParam=intent.getStringExtra("param");
    }

    private void mfindViewById() {
        lv = (ListView) findViewById(R.id.lv);
    }

    private void mInitData() {
        getHtml2(searchParam);
    }

    private void msetAdapter() {

    }

    private void mListener() {

    }
    public void getHtml2(final String searchParam){
        new Thread(){
            public void run() {
                //String url="http://www.wangpansou.cn/s.php?wp=0&ty=gn&op=gn&q=Android&q=Android";
                String url="http://www.wangpansou.cn/s.php?q="+searchParam+"&wp=0&start=20"+"&lyf="+((int)(Math.random()*1000));
                Log.v(tag, "url="+url);
                try {
                    String html2= MyHttpClient.doGet(url);
                    Message msg=new Message();
                    msg.obj=html2;
                    msg.what=1;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }


    /**
     * 联网获得数据
     * @return 数据
     */
    private List<Map<String, Object>> getListMapDate(String inHtml) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        String csdnString = inHtml;
        Log.v(tag, "inHtml="+inHtml);
        //<li><a title="(.*?)" href="(.*?)" target="_blank" onclick="LogClickCountthis,363;">\1</a></li>
        //title="(.*?)" href="(.*?)".*?,363\)
        //Pattern p = Pattern.compile("title=\"(.*?)\" href=\"(.*?)\".*?363");

        Pattern p = Pattern.compile("<a class=\"cse-search-result_content_item_top_a\" href=\"(.*?)\" rel=\"noreferrer\".*?>([.\n\r]+)(.*?)</a>");
        Matcher m = p.matcher(csdnString);
        while (m.find()) {
            MatchResult mr=m.toMatchResult();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("url", mr.group(1));
            map.put("title", mr.group(3));
            result.add(map);

            Log.v(tag, "groupCount="+mr.groupCount());
            Log.v(tag, "mr.group(1)=url="+mr.group(1));
            Log.v(tag, "mr.group(2)=url="+mr.group(2));
            Log.v(tag, "mr.group(3)=url="+mr.group(3));
        }
        Log.v(tag, "list="+result.toString());
        return result;
    }


    public class MyBaseAdapter extends BaseAdapter {
        List<Map<String, Object>> listMap=null;
        public MyBaseAdapter(List<Map<String, Object>> listmap){
            if(listmap!=null){
                listMap=listmap;
            }
        }

        @Override
        public int getCount() {
            return listMap.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh=null;
            if(convertView==null){
                vh=new ViewHolder();
                convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.lv_item, null);
                vh.vh_ll_item= (LinearLayout) convertView.findViewById(R.id.ll_item);
                vh.vh_item_tv_title=(TextView) convertView.findViewById(R.id.item_tv_title);
                convertView.setTag(vh);
            }else{
                vh=(ViewHolder) convertView.getTag();
            }
            vh.vh_ll_item.setOnLongClickListener(new MyOnLongClickListener(listMap.get(position).get("url").toString()));
            String title=listMap.get(position).get("title").toString().replace("\n", "").replace("\t", "").replace(" ", "");
            vh.vh_item_tv_title.setText(Html.fromHtml(title));
            return convertView;
        }

        class ViewHolder{
            LinearLayout vh_ll_item;
            TextView vh_item_tv_title;
        }
    }

    public class MyOnLongClickListener implements View.OnLongClickListener{
        String url=null;
        public MyOnLongClickListener(String param){
            url=param;
        }

        @Override
        public boolean onLongClick(View v) {
            try {
                //解码
                url=java.net.URLDecoder.decode(url, "UTF-8");
                //去掉不要的部分
                url=url.replace("http://redirect.wangpansou.cn/redirect.php?url=", "");
                Toast.makeText(SearchShow.this, url, 1).show();
                Log.v(tag, "url2="+url);
                //打开浏览器
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                SearchShow.this.startActivity(intent);

            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }

    }




}
