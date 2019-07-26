package com.zakim.javer.myuserlab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class QuestionListAdapter extends ArrayAdapter<Question> {

    private Context mContext;
    private int mResource;


    public QuestionListAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the Questions
        int id = getItem(position).getId();
        String question = getItem(position).getQuestion();
        String o1 = getItem(position).getChoice(0);
        String o2 = getItem(position).getChoice(1);
        String o3 = getItem(position).getChoice(2);
        String o4 = getItem(position).getChoice(3);
        String ans = getItem(position).getAnswer();

        //Create the person object with the information


            LayoutInflater inflater = LayoutInflater.from(mContext);//setting the inflater
            convertView = inflater.inflate(mResource, parent, false);

            TextView tQuestion = (TextView) convertView.findViewById(R.id.txtQuestion);
            TextView tO1 = (TextView) convertView.findViewById(R.id.txtOp1);
            TextView tO2 = (TextView) convertView.findViewById(R.id.txtOp2);
            TextView tO3 = (TextView) convertView.findViewById(R.id.txtOp3);
            TextView tO4 = (TextView) convertView.findViewById(R.id.txtOp4);
            TextView tAns = (TextView) convertView.findViewById(R.id.txtAns);
            TextView tID = (TextView) convertView.findViewById(R.id.txtID);

            tQuestion.setText(question);
            tO1.setText(o1);
            tO2.setText(o2);
            tO3.setText(o3);
            tO4.setText(o4);
            tAns.setText(ans);
            tID.setText(String.valueOf(id));

        return convertView;
    }

    public String getID(){
        return "gg";
    }
}
