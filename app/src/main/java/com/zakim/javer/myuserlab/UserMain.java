package com.zakim.javer.myuserlab;
//http://android-delight.blogspot.com/2016/07/how-to-sharing-data-between-apps-using.html
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class UserMain extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    TextView resultView;
    CursorLoader cursorLoader;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        resultView= (TextView) findViewById(R.id.res);
        mListView = findViewById(R.id.listView);

    }

    public void onClickDisplayNames(View view) {

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        cursorLoader= new CursorLoader(this, Uri.parse("content://com.zakim.javer.MyProvider.MC/cte"), null, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
        cursor.moveToFirst();
        final List<Users> userList = new ArrayList<>();
        while (!cursor.isAfterLast()) {

            //adding a new user
            Users users = new Users(cursor.getString(cursor.getColumnIndex("userName")),
                    cursor.getString(cursor.getColumnIndex("passwprd")),
                    cursor.getInt(cursor.getColumnIndex("highScore")),
                    cursor.getInt(cursor.getColumnIndex("id")));

            userList.add(users);
            cursor.moveToNext();
        }
        UsersListAdapter adapter = new UsersListAdapter(this, R.layout.adapter_view_layout_admin, userList);
        mListView.setAdapter(adapter);

        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users user;
                user = userList.get(position);
                Intent intent = new Intent(UserMain.this, UserSelected.class);
                // sends question
                intent.putExtra("userName", user.getuName());
                intent.putExtra("passwprd", user.getPass());
                intent.putExtra("highScore", user.gethScore());
                intent.putExtra("id", user.getId());
                startActivity(intent);
                Toast.makeText(UserMain.this, String.valueOf(user.getId()),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub
    }



    public void click(View view) {
        Intent intent = new Intent(UserMain.this, AddUser.class);
        startActivity(intent);
    }

    public void clickHome(View view) {
        Intent intent = new Intent(UserMain.this, MainActivity.class);
        startActivity(intent);
    }
}
