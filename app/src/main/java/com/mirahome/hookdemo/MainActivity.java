package com.mirahome.hookdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private Queue<String> mQueue;

    private static String TAG = "HOOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvImei = findViewById(R.id.tv_content);
        tvImei.setText(getStr());
        Intent intent = new Intent();
        intent.putExtra("FCUK", "origin");
        tvImei.setText("intent: " + intent.getStringExtra("FCUK"));

        loop();
        Log.e(TAG, "FK");

//        mQueue.
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private String getStr() {
        return "normal";
    }

    private void loop() {
        while (true) {
            Log.e(TAG, "FK");
            return;
        }
    }
}
