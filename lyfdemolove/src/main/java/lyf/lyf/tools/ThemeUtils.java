package lyf.lyf.tools;

import android.app.Activity;

import lyf.lyflove.R;

/**
 * Created by tangqi on 10/5/15.
 */
public class ThemeUtils {

    public static boolean isLight = true;

    /** 修改主题 白天和夜间模式切换 */
    public static void changeTheme(Activity activity) {
        if (!isLight) {
            activity.setTheme(R.style.Base_Theme_AppTheme_Dark);
        }
    }
}
