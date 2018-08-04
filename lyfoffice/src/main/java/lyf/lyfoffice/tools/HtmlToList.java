package lyf.lyfoffice.tools;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * (.*?) 表示要提取的数据
 *  \s* 表示若干个空格（可以是0个）
 */
public class HtmlToList {
    static String  tag="HtmlToList";

    static String model1="<dl logr=.*>\\s*[\\S*\\s]*?</dl>";
    public static List<HashMap<String,Object>> HtmlGetList(String htmlStr){
        List<HashMap<String,Object>> listResult=new ArrayList<HashMap<String,Object>>();

        Pattern p = Pattern.compile(model1);
        Matcher m = p.matcher(htmlStr);
        while (m.find()) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String itemStr =m.group();
            Log.v(tag, "itemStr="+itemStr);
            System.out.println(itemStr);

            //------------------------------------------------
            Pattern p1 = Pattern.compile("<a.*class=\"t\".*>(.*?)</a>");
            Matcher m1 = p1.matcher(itemStr);
            while(m1.find()){
                MatchResult mr=m1.toMatchResult();
                Log.v(tag, "groupCount="+mr.groupCount());
                String workName= mr.group(mr.groupCount());     //职位名称
                Log.v(tag, "workName="+workName);
                map.put("workName",workName);
            }
            //----------------------------------------------
            String model1_2="<a.*class=\"fl\".*>(.*?)</a>";
            Pattern p2 = Pattern.compile(model1_2);
            Matcher m2 = p2.matcher(itemStr);
            if(m2.find()){
                MatchResult mr=m2.toMatchResult();
                String gongsiName= mr.group(1);     //公司名称
                map.put("gongsiName",gongsiName);
            }
            //--------------------------------------------
            Matcher m3 = Pattern.compile("<dd class=\"w96\">(.*?)</dd>").matcher(itemStr);
            if(m3.find()){
                MatchResult mr=m3.toMatchResult();
                String workAddress= mr.group(1);     //工作的地址
                map.put("workAddress",workAddress);
            }
            //--------------------------------------------
            Matcher m4 = Pattern.compile("<dd class=\"w68\">\\s*(.*?)\\s*</dd>").matcher(itemStr);
            if(m4.find()){
                MatchResult mr=m4.toMatchResult();
                String publicTime= mr.group(1);   //发布的时间
                map.put("publicTime",publicTime);
            }
            //--------------------------------------------
             Matcher m5 = Pattern.compile("<li><span>招聘人数：</span>(.*?)</li>").matcher(itemStr);
            if(m5.find()){
                MatchResult mr=m5.toMatchResult();
                String personCount= mr.group(1);   //发布的时间
                map.put("personCount",personCount);
            }
            //--------------------------------------------
            Matcher m6 = Pattern.compile("<li.*><span>工作经验：</span>(.*?)</li>").matcher(itemStr);
            if(m6.find()){
                MatchResult mr=m6.toMatchResult();
                String workYear= mr.group(1);   //工作经验
                map.put("workYear",workYear);
            }
            //--------------------------------------------
            listResult.add(map);
        }
        Log.v(tag, "list="+listResult.toString());
        return listResult;
    }


    static String model2="<dl logr=.*>\\s*[\\S*\\s]*?</dl>";
    public static List<HashMap<String,Object>> HtmlGetList58Resume(String htmlStr){
        List<HashMap<String,Object>> listResult=new ArrayList<HashMap<String,Object>>();

        Pattern p = Pattern.compile(model2);
        Matcher m = p.matcher(htmlStr);
        while (m.find()) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String itemStr =m.group();
            Log.v(tag, "itemStr="+itemStr);
            System.out.println(itemStr);

            //----------------------------------------------
            String model1_2="<a.*class=\"fl\".*>(.*?)</a>";
            Pattern p2 = Pattern.compile(model1_2);
            Matcher m2 = p2.matcher(itemStr);
            if(m2.find()){
                MatchResult mr=m2.toMatchResult();
                String ResumeName= mr.group(1);     //简历名称
                map.put("ResumeName",ResumeName);
            }
            //--------------------------------------------
            Matcher m3 = Pattern.compile("<dd class=\"w80\">\\s*(.*?)\\s*</dd>").matcher(itemStr);
            if(m3.find()){
                MatchResult mr=m3.toMatchResult();
                String personMame= mr.group(1);     //姓名
                map.put("personMame",personMame);
            }
            //--------------------------------------------
            Matcher m4 = Pattern.compile("<dd class=\"w50\">(.*?)</dd>").matcher(itemStr);
            if(m4.find()){
                MatchResult mr=m4.toMatchResult();
                String sex= mr.group(1);   //性别
                map.put("sex",sex);
            }
            //--------------------------------------------
            Matcher m5 = Pattern.compile("<dd class=\"w70\">(.*?)</dd>").matcher(itemStr);
            if(m5.find()){
                MatchResult mr=m5.toMatchResult();
                String age= mr.group(1);   //年龄
                map.put("age",age);
            }
            //--------------------------------------------
            Matcher m6 = Pattern.compile("<dd class=\"w80\">(.*?)</dd>").matcher(itemStr);
            if(m6.find()){
                MatchResult mr=m6.toMatchResult();
                String workYear= mr.group(1);   //工作经验
                map.put("workYear",workYear);
            }
            //--------------------------------------------
            Matcher m7 = Pattern.compile("<dd class=\"w100\">(.*?)</dd>").matcher(itemStr);
            if(m7.find()){
                MatchResult mr=m7.toMatchResult();
                String study= mr.group(1);   //学历
                map.put("study",study);
            }
            //--------------------------------------------
            Matcher m8 = Pattern.compile("<dd class=\"w190\".*>(.*?)</dd>").matcher(itemStr);
            if(m8.find()){
                MatchResult mr=m8.toMatchResult();
                String work= mr.group(1);   //目前职位
                map.put("work",work);
            }
            //--------------------------------------------
            Matcher m9 = Pattern.compile("<dd class=\"w90\">(.*?)</dd>").matcher(itemStr);
            if(m9.find()){
                MatchResult mr=m9.toMatchResult();
                String RefreshTime= mr.group(1);   //目前职位
                map.put("RefreshTime",RefreshTime);
            }
            //--------------------------------------------

            listResult.add(map);
        }
        Log.v(tag, "list="+listResult.toString());
        return listResult;
    }


}
