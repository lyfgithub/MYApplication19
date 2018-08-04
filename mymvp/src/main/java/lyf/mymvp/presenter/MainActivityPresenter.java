package lyf.mymvp.presenter;

import java.util.HashMap;
import java.util.List;

import lyf.mymvp.model.IMainActivityCallback;
import lyf.mymvp.model.IMainActivityModel;
import lyf.mymvp.model.MainActivityModel;
import lyf.mymvp.view.IMainActivity;

/**
 * Author LYF
 * Created by Administrator on 2016/10/1.
 */
public class MainActivityPresenter {
    private IMainActivity mIMainActivity;
    private IMainActivityModel mIMainActivityMode;

    public MainActivityPresenter(IMainActivity view){
        this.mIMainActivity=view;
        mIMainActivityMode=new MainActivityModel(new My());
    }

    //
    public void saveData(){
        mIMainActivityMode.getTV();
    }

    //在界面上显示数据
    public void loadData(String tvStr){
        mIMainActivity.setTV(tvStr);
    }

    public void getRecycleViewData(){
        mIMainActivityMode.getRecycleViewData();
    }

    class My implements IMainActivityCallback{
        @Override
        public void onFinish(String result) {
            loadData(result);
        }

        @Override
        public void onFinishRecycleViewData(List<HashMap<String, Object>> list) {
            mIMainActivity.loadDataRecycleView(list);
        }


    }

}
