package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class signupdialog extends AppCompatActivity {
    public void signupmessage(boolean... params){
        setContentView(R.layout.activity_signupdialog);

        TextView IdCheck = (TextView)findViewById(R.id.IdCheck);
        TextView emailCheck = (TextView)findViewById(R.id.emailCheck);
        TextView pwCheck = (TextView)findViewById(R.id.pwCheck);
        TextView pw_cCheck = (TextView)findViewById(R.id.pw_cCheck);

        //될때 false를 보내고 안될때 true를 보냄
        if(params[0]==true){
            IdCheck.setVisibility(View.VISIBLE);
        } else{
            IdCheck.setVisibility(View.GONE);
        }
        if(params[1]==true){
            emailCheck.setVisibility(View.VISIBLE);
        } else{
            emailCheck.setVisibility(View.GONE);
        }
        if(params[2]==true){
            pwCheck.setVisibility(View.VISIBLE);
        } else{
            pwCheck.setVisibility(View.GONE);
        }
        if(params[3]==true){
            pw_cCheck.setVisibility(View.VISIBLE);
        } else{
            pw_cCheck.setVisibility(View.GONE);
        }


    }
}
