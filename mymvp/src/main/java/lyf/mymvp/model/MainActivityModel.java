package lyf.mymvp.model;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import lyf.mymvp.utils.JsonParser;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author LYF
 * Created by Administrator on 2016/10/1.
 */
public class MainActivityModel implements IMainActivityModel {
    IMainActivityCallback mIMainActivityCallback;

    public MainActivityModel(IMainActivityCallback callback){
        this.mIMainActivityCallback=callback;
    }

    @Override
    public String getTV() {
        //查询数据库或者网络数据,并返回
        String result="Hello LYF";
        mIMainActivityCallback.onFinish(result);
        return result;
    }

    @Override
    public void setTV() {
        //向数据库存储数据
    }

    @Override
    public void getRecycleViewData() {
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://news.at.zhihu.com/api/4/news/before/20161002")
                //.url("http://www.1niukou.com/api/ProductService.asmx/Recommend")
                .build();
        client.newCall(request).enqueue(new Okhttp3Callback());

    }
    //得到异步执行的结果
    class Okhttp3Callback implements okhttp3.Callback{
        @Override
        public void onFailure(Call call, IOException e) {
            Log.v("LYF","失败了onFailure");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String json= response.body().string().toString();  //response.body().string()是得到字符串

            Log.v("LYF","json="+json);
            List<HashMap<String,Object>> list= JsonParser.getList(json);

            mIMainActivityCallback.onFinishRecycleViewData(list);
        }
    }
}

//class JsonParser{
//    public static List<HashMap<String,Object>> getList(String jsonStr){
//        Log.v("LYF","jsonStr="+jsonStr);
//        try {
//            JSONObject object = new JSONObject(jsonStr);
//            JSONArray stories= object.getJSONArray("stories");
//            List<HashMap<String,Object>> list= new ArrayList<HashMap<String,Object>>();
//
//            for (int i = 0; i < stories.length(); i++) {
//                JSONObject item= (JSONObject) stories.get(i);
//                HashMap<String,Object> itemMap=new HashMap<String,Object>();
//                itemMap.put("title",item.get("title")) ;
//
//                JSONArray images= item.getJSONArray("images");
//                itemMap.put("img",images.get(0).toString()) ;
//
//                list.add(itemMap);
//            }
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.v("LYF","json解析异常");
//        }
//        return null;
//    }
//}
