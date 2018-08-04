package lyf.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author LYF
 * Created by Administrator on 2016/9/18.
 */
public class MyFragment extends Fragment {
    @Override //Fragment创建时
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override //
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.myfragment,null);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
