package lyf.mymvp.view;

import java.util.HashMap;
import java.util.List;

/**
 * Author LYF
 * Created by Administrator on 2016/10/1.
 */
public interface IMainActivity {
    void setTV(String text);

    void loadDataRecycleView(List<HashMap<String, Object>> list);
}
