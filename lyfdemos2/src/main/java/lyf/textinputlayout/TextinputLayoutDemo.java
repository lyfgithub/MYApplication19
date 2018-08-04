package lyf.textinputlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import lyf.lyfdemos2.R;

/**
 * Author LYF
 * Created by Administrator on 2016/9/24.
 */
public class TextinputLayoutDemo extends AppCompatActivity {

    private TextInputLayout textInputLayout;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textinputlayoutdemo);

        textInputLayout = (TextInputLayout) findViewById(R.id.til);
        textInputLayout.setHint("请输入用户名");
        editText = (EditText) findViewById(R.id.et_1);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>5){
                    textInputLayout.setError("长度超出5个了");
                }else{
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
