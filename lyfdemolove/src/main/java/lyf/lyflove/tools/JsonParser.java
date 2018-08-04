package lyf.lyflove.tools;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author LYF
 * Created by Administrator on 2016/10/2.
 */
public class JsonParser {
    public static List<HashMap<String,Object>> getList(String jsonStr){
        Log.v("LYF","jsonStr="+jsonStr);
        try {
            JSONObject object = new JSONObject(jsonStr);
            JSONArray stories= object.getJSONArray("stories");
            List<HashMap<String,Object>> list= new ArrayList<HashMap<String,Object>>();

            for (int i = 0; i < stories.length(); i++) {
                JSONObject item= (JSONObject) stories.get(i);
                HashMap<String,Object> itemMap=new HashMap<String,Object>();
                itemMap.put("title",item.get("title")) ;

                JSONArray images= item.getJSONArray("images");
                itemMap.put("img",images.get(0).toString()) ;

                list.add(itemMap);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("LYF","json解析异常");
        }
        return null;
    }
}
