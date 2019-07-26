package com.zakim.javer.myuserlab;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionSelected extends AppCompatActivity {

    TextView mQuestion, mAns, mOp1, mOp2, mOp3, mOp4, mID;
    String URL = "content://com.zakim.javer.MyProviderQuestion.MC/cte";
    Uri friends = Uri.parse(URL);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_selected);

        mQuestion = findViewById(R.id.txtQuestion);
        mAns = findViewById(R.id.txtAns);
        mID = findViewById(R.id.txtID);
        mOp1 = findViewById(R.id.txtOp1);
        mOp2 = findViewById(R.id.txtOp2);
        mOp3 = findViewById(R.id.txtOp3);
        mOp4 = findViewById(R.id.txtOp4);

        Intent intent = getIntent();
        //get the question to edit
        mQuestion.setText(intent.getStringExtra("question"));
        mAns.setText(intent.getStringExtra("ans"));
        mID.setText(String.valueOf(intent.getIntExtra("id",0)));
        mOp1.setText(intent.getStringExtra("c1"));
        mOp2.setText(intent.getStringExtra("c2"));
        mOp3.setText(intent.getStringExtra("c3"));
        mOp4.setText(intent.getStringExtra("c4"));
        //

        //mQuestion.setText(question.getQuestion().toString());

    }

    public void onClickUpdate(View view) {
        ContentValues cv = new ContentValues();
        cv.put("question", mQuestion.getText().toString());
        cv.put("answer", mAns.getText().toString());
        cv.put("id", Integer.parseInt(mID.getText().toString()));
        cv.put("choice1", mOp1.getText().toString());
        cv.put("choice2", mOp2.getText().toString());
        cv.put("choice3", mOp3.getText().toString());
        cv.put("choice4", mOp4.getText().toString());
        try{
            int count = getContentResolver().update(friends, cv,"id = ?", new String[] { mID.getText().toString()});
            Toast.makeText(getBaseContext(),"Updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(QuestionSelected.this, QuestionMain.class);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getBaseContext(),"Could not Update", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickDelete(View view) {

        try{
            getContentResolver().delete(friends, "id = ?", new String[] { mID.getText().toString()});
            Toast.makeText(getBaseContext(),"Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(QuestionSelected.this, QuestionMain.class);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getBaseContext(),"Could not delete", Toast.LENGTH_LONG).show();
        }


    }
}
