package com.lookout.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lookout.sample.mvp.MvpActivity;
import com.lookout.sample.mvvm.MvvmActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);

        final Button mvpButton = new Button(this);
        mvpButton.setText("MVP");
        mvpButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MvpActivity.class));
            }
        });
        linearLayout.addView(mvpButton);

        final Button mvvmButton = new Button(this);
        mvvmButton.setText("MVVM");
        mvvmButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MvvmActivity.class));
            }
        });
        linearLayout.addView(mvvmButton);

    }
}
