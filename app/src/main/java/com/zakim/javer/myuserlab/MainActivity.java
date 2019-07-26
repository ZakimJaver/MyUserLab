package com.zakim.javer.myuserlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickUsers(View view) {
        Intent intent = new Intent(MainActivity.this, UserMain.class);
        startActivity(intent);
    }

    public void clickQuestions(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionMain.class);
        startActivity(intent);
    }
}
