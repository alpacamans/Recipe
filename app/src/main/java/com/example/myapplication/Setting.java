package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDelegate;

import static android.content.Context.MODE_PRIVATE;

public class Setting {
    //static Context mContext = MainActivity.getmContext();
    // 잘모르겠음

    public static void setBooleanData(Context mContext,String key,boolean on_off){
        //버튼을 온오프하면 SQLite에 그에 따른 값 설정 on = true, off = false
        //키값은 key(String)에 저장, 저장할 값은 on_off(boolean)에 저장
        // 다크모드 뿐만 아니라 자동로그인 같은 On/Off를 저장해야 하는 상황에서 getBoolean와 같이 사용가능

        SharedPreferences pref = mContext.getSharedPreferences(""+key,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        if(on_off == true){
            //받은 값이 true면
            editor.putBoolean(""+key, true);
            //SQLite 키의 값을 true로 바꿈
        }
        else{
            editor.putBoolean(""+key,false);
            //SQLite 키의 값을 false로 바꿈
        }

        editor.commit();
        // 잘모르겠음 저장하는거 같음

//        return;
        //종료
    }

    public static boolean getBooleanData(Context mContext,String key){
        // SQLite 키에 저장되있는 값을 보내주는 메소드
        // 키값은 key(String)에 저장
        // 다크모드 뿐만 아니라 자동로그인 같은 On/Off를 저장해야 하는 상황에서 setBooleanData와 같이 사용가능

        SharedPreferences pref = mContext.getSharedPreferences(""+key, MODE_PRIVATE);
        // sql이랑 연결하는거 같음
        boolean prefData = pref.getBoolean(""+key,false);
        // 키값 받아옴 만약에 키값이 없으면 false값을 넣음
        if(prefData==true){
            //만약 키값이 true라면
            return true;
            //true를 리턴함
        }
        else{
            //false라면
            return false;
            //false를 리턴함
        }
    }

    public static void setSaveData(Context mContext, String key, String Data){
        SharedPreferences pref = mContext.getSharedPreferences(""+key,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(""+key,""+Data);
        return;
    }

    public static String getSaveData(Context mContext, String key){
        SharedPreferences pref = mContext.getSharedPreferences(""+key, MODE_PRIVATE);
        String preData = pref.getString(""+key,"");
        if(preData.matches(".*[a-zA-Z].*")){
            return preData;
        }
        else {
            return "";
        }
    }

    public static void setTheme(Context mContext,ImageView whatfood){
        // 다크테마를 설정하는 메소드
        if(getBooleanData(mContext,"theme")==true){
            // getOnOffcheck에서 받아온값이 true면 다크모드 설정
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            whatfood.setBackgroundColor(Color.parseColor("#FF000000"));

        }
        else{
            // false면 기본화면으로 설정
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            whatfood.setBackgroundColor(Color.parseColor("#00000000"));
        }

    }

    public static boolean check(String one,String two){
        //두개가 동일한지 확인
        if(one.equals(two)){
            return true;
        }
        else{
            return false;
        }
    }
}
