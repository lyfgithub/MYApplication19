package lyf.mymvp.model;

import java.util.HashMap;
import java.util.List;

/**
 * Author LYF
 * Created by Administrator on 2016/10/2.
 */
public interface IMainActivityCallback {
    //
    void onFinish(String result);

    void onFinishRecycleViewData(List<HashMap<String, Object>> list);
}
