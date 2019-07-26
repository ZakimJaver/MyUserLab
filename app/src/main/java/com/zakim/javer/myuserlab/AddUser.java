package com.zakim.javer.myuserlab;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    public void onClickAddName(View view) {
        String URL = "content://com.zakim.javer.MyProvider.MC/cte";
        Uri friends = Uri.parse(URL);
        ContentValues values = new ContentValues();
        values.put("userName", ((EditText) findViewById(R.id.txtName))
                .getText().toString());
        values.put("passwprd", ((EditText) findViewById(R.id.txtPass))
                .getText().toString());
        values.put("highScore", ((EditText) findViewById(R.id.txtScore))
                .getText().toString());
        getContentResolver().insert(friends, values);
        Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(AddUser.this, UserMain.class);
        startActivity(intent);
    }
}
