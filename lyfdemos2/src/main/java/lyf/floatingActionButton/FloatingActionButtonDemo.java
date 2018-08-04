package lyf.floatingActionButton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import lyf.lyfdemos2.R;

/**
 * Author LYF
 * Created by Administrator on 2016/9/24.
 */
public class FloatingActionButtonDemo extends AppCompatActivity{

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floatingactionbutton_demo);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbarShow();
                Toast.makeText(getApplicationContext(),"floatingActionButton", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void snackbarShow(){
        final Snackbar snackbar= Snackbar.make(floatingActionButton,"弹出Snackbar",Snackbar.LENGTH_SHORT);
        snackbar.show();
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
    }
}
