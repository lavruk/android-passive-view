package com.lookout.sample.mvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.lookout.sample.R;

import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class MvvmActivity extends AppCompatActivity {

    private ViewModel mViewModel;
    private CompositeSubscription mSubscription = Subscriptions.from();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mViewModel = new ViewModel();
        mSubscription.addAll(
            mViewModel.observeButtonText().subscribe(text -> {
                ((TextView) findViewById(R.id.textView)).setText(text);
            }),
            mViewModel.observeEditedText().subscribe(text -> {
                ((TextView) findViewById(R.id.textView2)).setText(text);
            })
        );

        findViewById(R.id.button).setOnClickListener(v -> mViewModel.onButtonClicked());

        ((EditText) findViewById(R.id.editText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.onTextChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        mSubscription.clear();
        super.onDestroy();
    }
}
