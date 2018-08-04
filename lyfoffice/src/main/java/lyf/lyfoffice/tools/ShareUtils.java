package lyf.lyfoffice.tools;

import android.content.Context;
import android.content.Intent;

/**
 * 分享
 */
public class ShareUtils {

    public static void share(Context context) {
        share(context, "LYF分享");
    }

    public static void share(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享"));
    }
}
