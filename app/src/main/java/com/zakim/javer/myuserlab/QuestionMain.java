package com.zakim.javer.myuserlab;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestionMain extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {


    CursorLoader cursorLoader;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_main);
        mListView = (ListView) findViewById(R.id.listView);
    }

    public void onClickAddName(View view) {
        Intent intent = new Intent(QuestionMain.this, AddQuestion.class);
        startActivity(intent);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        cursorLoader= new CursorLoader(this, Uri.parse("content://com.zakim.javer.MyProviderQuestion.MC/cte"), null, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
            cursor.moveToFirst();
        final List<Question> questionList = new ArrayList<>();
        while (!cursor.isAfterLast()) {

                Question question = new Question(cursor.getString(cursor.getColumnIndex("question")),
                        new String[]{cursor.getString(cursor.getColumnIndex("choice1")), cursor.getString(cursor.getColumnIndex("choice2")),
                        cursor.getString(cursor.getColumnIndex("choice3")), cursor.getString(cursor.getColumnIndex("choice4"))},
                        cursor.getString(cursor.getColumnIndex("answer")), cursor.getInt(cursor.getColumnIndex("id")));
            questionList.add(question);
                cursor.moveToNext();
            }
        QuestionListAdapter adapter = new QuestionListAdapter(this, R.layout.adapter_view_layout_admin_question, questionList);
        mListView.setAdapter(adapter);

        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question question;
                question = questionList.get(position);
                Intent intent = new Intent(QuestionMain.this, QuestionSelected.class);
                // sends question
                intent.putExtra("question", question.getQuestion());
                intent.putExtra("ans", question.getAnswer());
                intent.putExtra("id", question.getId());
                intent.putExtra("c1", question.getChoice(0));
                intent.putExtra("c2", question.getChoice(1));
                intent.putExtra("c3", question.getChoice(2));
                intent.putExtra("c4", question.getChoice(3));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub
    }


    public void onClickDisplay(View view) {
        getLoaderManager().initLoader(1, null, this);

    }

    public void clickHome(View view) {
        Intent intent = new Intent(QuestionMain.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle save) {
        super.onSaveInstanceState(save);


    }
}
