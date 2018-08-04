package lyf.designsupportlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {
    String Tag="MyFragment";
    String title;
    private TextView textView;

    public MyFragment(){}

    public MyFragment(String title) {
        this.title=title;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(Tag,"================onActivityCreated()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(Tag,"================onActivityCreated()");
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.myfragment,null);
        textView = (TextView) view.findViewById(R.id.tv_fragmentContent);
        textView.setText(title);
        return view;
    }
}
