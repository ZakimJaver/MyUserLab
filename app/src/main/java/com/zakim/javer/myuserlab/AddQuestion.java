package com.zakim.javer.myuserlab;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
    }

    public void onClickAddName(View view) {
        addQuestion();
    }

    public void addQuestion(){
        String URL = "content://com.zakim.javer.MyProviderQuestion.MC/cte";
        Uri friends = Uri.parse(URL);
        ContentValues values = new ContentValues();
        values.put("question", ((EditText) findViewById(R.id.txtQuestion))
                .getText().toString());
        values.put("choice1", ((EditText) findViewById(R.id.txtOp1))
                .getText().toString());
        values.put("choice2", ((EditText) findViewById(R.id.txtOp2))
                .getText().toString());
        values.put("choice3", ((EditText) findViewById(R.id.txtOp3))
                .getText().toString());
        values.put("choice4", ((EditText) findViewById(R.id.txtOp4))
                .getText().toString());
        values.put("answer", ((EditText) findViewById(R.id.txtAns))
                .getText().toString());
        getContentResolver().insert(friends, values);

        Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(AddQuestion.this, QuestionMain.class);
        startActivity(intent);
    }
}
