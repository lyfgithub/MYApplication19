package lyf.lyf.tools;

import android.content.Context;
import android.content.SharedPreferences;

import lyf.lyflove.base.MyApplication;

/**
 * 用来快速获取相关的设置
 * Created by Troy on 2015/9/20.
 */
public class PrefUtils {

    private static final String PRE_NAME = "preferences_log";
    public static final String PRE_CRASH_ISLASTTIMECRASHED = "isLastTimeCrashed";
    public static final String PRE_CRASH_URI = "crashUri";

    private static SharedPreferences getSharedPreferences() {
        return MyApplication.getContext().getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
    }

    public static void setCrash(boolean isLastTimeCrashed, String crashUri) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PRE_CRASH_ISLASTTIMECRASHED, isLastTimeCrashed);
        editor.putString(PRE_CRASH_URI, crashUri);
        editor.commit();
    }
}
