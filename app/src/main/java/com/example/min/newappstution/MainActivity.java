package com.example.min.newappstution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.min.newappstution.utile.IntentUtile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.go_T1)
    TextView go_T1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.go_T1})
    public void viewOnClick(View view){
        switch (view.getId()){
            case R.id.go_T1:
                IntentUtile.goAC(MainActivity.this,TextAC1.class);
                break;
        }
    }
}
