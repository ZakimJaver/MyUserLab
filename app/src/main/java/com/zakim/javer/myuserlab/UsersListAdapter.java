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

class UsersListAdapter extends ArrayAdapter<Users> {

    private Context mContext;
    private int mResource;


    public UsersListAdapter(@NonNull Context context, int resource, @NonNull List<Users> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the persons information
        int id = getItem(position).getId();
        String uName = getItem(position).getuName();
        String password = getItem(position).getPass();
        int score = getItem(position).gethScore();

        //Create the person object with the information
        Users users = new Users(uName, password, score, id);


            LayoutInflater inflater = LayoutInflater.from(mContext);//setting the inflater
            convertView = inflater.inflate(mResource, parent, false);

            TextView tUserName = (TextView) convertView.findViewById(R.id.userName);
            TextView tpass = (TextView) convertView.findViewById(R.id.password);
            TextView tScore = (TextView) convertView.findViewById(R.id.score);
            TextView tID = (TextView) convertView.findViewById(R.id.id);
            tUserName.setText(uName);
            tpass.setText(password);
            tScore.setText(String.valueOf(score));
            tID.setText(String.valueOf(id));

        return convertView;
    }
}
