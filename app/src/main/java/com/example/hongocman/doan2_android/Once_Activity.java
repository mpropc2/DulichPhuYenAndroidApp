package com.example.hongocman.doan2_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Once_Activity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_once);

        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Once_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);

        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(Once_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
        }
    }
}
