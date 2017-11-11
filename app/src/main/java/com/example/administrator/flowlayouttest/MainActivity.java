package com.example.administrator.flowlayouttest;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.lidroid.xutils.ViewUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.lidroid.xutils.view.annotation.ViewInject;

import static android.R.id.edit;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.found_activity_fyt)
    private FlowLayout found_activity_fyt;

    @ViewInject(R.id.found_edt)
    private EditText found_edt;

    @ViewInject(R.id.search)
    private ImageView search;

    @ViewInject(R.id.found_history_ryt)
    private LinearLayout found_history_ryt;

    @ViewInject(R.id.lv)
    private ListView lv;

    @ViewInject(R.id.found_back)
    private ImageView found_back;
    private InputMethodManager inputMethodManager;
    private String[] strs = {"美的", "格力", "飞利浦", "方太", "西门子", "A.O.史密斯", "爱马仕", "奔腾", "TCL", "小天鹅", "三洋"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        found_edt.addTextChangedListener(new TextWatcher() {//给搜索框添加监听
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {//判断搜索框输入
                if (editable.length() == 0) {
                    found_history_ryt.setVisibility(View.VISIBLE);//搜索记录View可见
                    lv.setVisibility(View.GONE);//搜索内容View不可见
                } else if (editable.length() >= 1) {
                    found_history_ryt.setVisibility(View.GONE);
                   lv.setVisibility(View.VISIBLE);
                }

            }
        });

        for (int i = 0; i < strs.length; i++) {//加载搜索记录
            final TextView text = new TextView(MainActivity.this);
            text.setText(strs[i]);//添加内容
            text.setTextSize(12);
            text.setTextColor(Color.rgb(102, 102, 102));
            text.setBackgroundResource(R.drawable.light_button_back);
            text.setPadding(15, 10, 15, 10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//设置宽高,第一个参数是宽,第二个参数是高
            //设置边距
            params.topMargin = 5;
            params.bottomMargin = 5;
            params.leftMargin = 8;
            params.rightMargin = 8;
            text.setLayoutParams(params);
            found_activity_fyt.addView(text);//将内容添加到布局中
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//添加点击事件
                    found_edt.setText(text.getText().toString());
                    Toast.makeText(MainActivity.this, text.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        found_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                inputMethodManager.hideSoftInputFromWindow(found_edt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                lv.setAdapter(new FoundListAdapter(MainActivity.this));//绑定适配器
            }
        });
    }
}
