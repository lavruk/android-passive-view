package com.lookout.sample.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.lookout.sample.R;

public class MvpActivity extends AppCompatActivity implements Screen {

    private Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mPresenter = new Presenter(this);
        mPresenter.onCreate();

        findViewById(R.id.button).setOnClickListener(v -> mPresenter.onButtonClicked());

        ((EditText) findViewById(R.id.editText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onTextChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void setTextForButton(String text) {
        ((TextView) findViewById(R.id.textView)).setText(text);
    }

    @Override
    public void setChangedText(String text) {
        ((TextView) findViewById(R.id.textView2)).setText(text);
    }
}
