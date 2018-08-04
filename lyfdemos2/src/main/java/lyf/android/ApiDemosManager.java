package lyf.android;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入口
 * @author LYF
 * 文档 http://hunter2014.iteye.com/blog/778160
 */
public class ApiDemosManager extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String path = intent.getStringExtra("com.example.android.apis.Path");

        if (path == null) {  //如果不存在path
            path = "";
        }

        //设置listView的数据
        setListAdapter(new SimpleAdapter(this,
                getData(path),
                android.R.layout.simple_list_item_1,
                new String[] { "title" },
                new int[] { android.R.id.text1 }));
        //这个方法的作用是用来过滤选项的.例如在软键盘上打出一个a,则会过滤掉除了a开头的所有选项.
        getListView().setTextFilterEnabled(true);
    }

    //根据path得到list数据
    protected List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        //创建一个intent意图（动作为main,类别为例子）
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager(); //得到包管理器
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);  //使用指定的Intent查询
        //如果结果为空
        if (null == list)
            return myData;

        /** 定义一个前缀数组 */
        String[] prefixPath;
        String prefixWithSlash = prefix;

        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }

        int len = list.size();

        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm); //得到label标签的值
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : info.activityInfo.name;   //如果标签有值取标签的值，没有则取name    App/Activity/Wallpaper

            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];  //App

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        //对集合排序
        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    /**静态　定义 显示名称  集合  　*/
    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName); //可以跳转到与当前的activity是不同applicaiton的activity或者service.
        return result;
    }
    /** 跳转本Activity */
    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, ApiDemosManager.class);
        result.putExtra("com.example.android.apis.Path", path);
        return result;
    }

    /**
     * 将指定的name和intent封装成hashmap,并添加到指定的list
     * @param data 指定的数据容器集合
     * @param name 名字
     * @param intent 相关意图
     */
    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }




}