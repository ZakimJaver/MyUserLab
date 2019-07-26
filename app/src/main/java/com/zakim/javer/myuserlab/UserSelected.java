package com.zakim.javer.myuserlab;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UserSelected extends AppCompatActivity {

    TextView mID, mUserName, mPassword, mScore;
    String URL = "content://com.zakim.javer.MyProvider.MC/cte";
    Uri friends = Uri.parse(URL);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selected);

        mID = findViewById(R.id.txtID);
        mUserName = findViewById(R.id.txtName);
        mPassword = findViewById(R.id.txtPass);
        mScore = findViewById(R.id.txtScore);

        Intent intent = getIntent();
        mID.setText(String.valueOf(intent.getIntExtra("id",0)));
        mUserName.setText(intent.getStringExtra("userName"));
        mPassword.setText(intent.getStringExtra("passwprd"));
        mScore.setText(String.valueOf(intent.getIntExtra("highScore",0)));
    }

    public void onClickDelete(View view) {
        try{
            getContentResolver().delete(friends, "id = ?", new String[] { mID.getText().toString()});
            Toast.makeText(getBaseContext(),"Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(UserSelected.this, UserMain.class);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getBaseContext(),"Could not delete", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickUpdate(View view) {
        ContentValues cv = new ContentValues();
        cv.put("userName", mUserName.getText().toString());
        cv.put("passwprd", mPassword.getText().toString());
        cv.put("id", Integer.parseInt(mID.getText().toString()));
        cv.put("highScore", mScore.getText().toString());
        try{
            getContentResolver().update(friends, cv,"id = ?", new String[] { mID.getText().toString()});
            Toast.makeText(getBaseContext(),"Updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(UserSelected.this, UserMain.class);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getBaseContext(),"Could not Update", Toast.LENGTH_LONG).show();
        }
    }
}
