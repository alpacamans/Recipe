package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Dialog dilaog01;
    Dialog dilaog02;
    boolean IdCheckBool;
    boolean EmailCheckBool;
    boolean UserNameCheckBool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Button sign_btn = findViewById(R.id.sign_btn);
        Button login_btn = findViewById(R.id.login_btn);








        // 레이아웃 버튼과 연결
//
//        toolbar_btn.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        Log.d("DATA_SNS", "asdasd");
//
//                        Intent first_intent = new Intent(MainActivity.this, toolbar.class);
//                        startActivity(first_intent);
//                        // toolbar액티비티로 이동
//                    }
//                }
//        );


        sign_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Log.d("DATA_SNS","signup");
                        showDialogsignup();

                    }
                }
        );
        login_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Log.d("DATA_SNS","login");
                        showDialoglogin();

                    }
                }
        );
    }


    public void showDialogsignup() {

        dilaog01 = new Dialog(MainActivity.this);       // Dialog 초기화
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog01.setContentView(R.layout.activity_signupdialog);             // xml 레이아웃 파일과 연결

        dilaog01.show(); // 다이얼로그 띄우기
        dilaog01.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 투명 배경


        IdCheckhide(true);
        EmailCheckhide(true);
        UserNameCheckhide(true);
        Pw_cCheckhide(true);
        PwCheckhide(true);
        UserinfoCheck(true);
        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */

        // 아니오 버튼
        dilaog01.findViewById(R.id.end_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 원하는 기능 구현
                dilaog01.dismiss(); // 다이얼로그 닫기
            }
        });
        // 네 버튼
        dilaog01.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("에쓰","클릭됨");
                signup sign = new signup();
                // 원하는 기능 구현
                EditText IdEditText = dilaog01.findViewById(R.id.Id);
                EditText EmailEditText = dilaog01.findViewById(R.id.email);
                EditText UserNameEditText = dilaog01.findViewById(R.id.Username);
                EditText pwEditText = dilaog01.findViewById(R.id.pw);
                EditText pw_cEditText = dilaog01.findViewById(R.id.pw_c);
                String Id = "";
                String Email = "";
                String Pw = "";
                String Pw_c = "";
                String UserName = "";
                if (IdEditText != null) {
                    Id = IdEditText.getText().toString();
                }
                if (EmailEditText != null) {
                    Email = EmailEditText.getText().toString();
                }
                if (pwEditText != null) {
                    Pw = pwEditText.getText().toString();
                }
                if (pw_cEditText != null) {
                    Pw_c = pw_cEditText.getText().toString();
                }
                if (UserNameEditText != null) {
                    UserName = UserNameEditText.getText().toString();
                }

                if (sign.password_finish_Check(sign.password_Condition_Check(Pw),sign.password_Same_Check(Pw,Pw_c)) == true && sign.email_Regular_Check(Email)==true&&!(Id.equals(""))&&!(Email.equals(""))&&!(UserName.equals(""))) {
                    sign.UserInformationCheck(MainActivity.this, Id, Email,UserName,Pw);
                } else{
                    UserinfoCheck(false);
                }
            }
        });

    }

    public void showDialoglogin() {

        dilaog02 = new Dialog(MainActivity.this);       // Dialog 초기화
        dilaog02.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog02.setContentView(R.layout.activity_logindialog);             // xml 레이아웃 파일과 연결

        dilaog02.show(); // 다이얼로그 띄우기
        dilaog02.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 투명 배경

        /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */
        loginhide(true);
        // 아니오 버튼
        dilaog02.findViewById(R.id.end_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 원하는 기능 구현
                dilaog02.dismiss(); // 다이얼로그 닫기
            }
        });
        // 네 버튼
        dilaog02.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logindialog Login = new logindialog();
                EditText IdEditText = dilaog02.findViewById(R.id.id);
                EditText pwEditText = dilaog02.findViewById(R.id.pw);

                String Id = "";
                String Pw = "";
                if (IdEditText != null) {
                    Id = IdEditText.getText().toString();
                }
                if(Id.equals("")){
                    loginhide(false);
                }
                if (pwEditText != null) {
                    Pw = pwEditText.getText().toString();
                }
                if(Pw.equals("")){
                    loginhide(false);
                }
                Login.Login(MainActivity.this,Id,Pw);



            }
        });

    }

    public void Regok(){

        Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
        dilaog01.dismiss();
    }
    public void Regno(boolean... params){
        Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
        IdCheckhide(params[0]);
        EmailCheckhide(params[1]);
        UserNameCheckhide(params[2]);
    }


    public void logok(){
        UserInfo UserInfo = new UserInfo();
        Toast.makeText(getApplicationContext(), "로그인 성공 "+UserInfo.getUsername()+"님 환영합니다.", Toast.LENGTH_LONG).show();
        Intent first_intent = new Intent(MainActivity.this, recipe.class);
        startActivity(first_intent);
        finish();
        dilaog02.dismiss();

    }
    public void logno(){
        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
        loginhide(false);
    }


    public void IdCheckhide(boolean b) {

        TextView IdCheck = dilaog01.findViewById(R.id.IdCheck);

        if (b == false) {
            IdCheck.setVisibility(View.VISIBLE);
        } else {
            IdCheck.setVisibility(View.GONE);
        }
    }


    public void UserNameCheckhide(boolean b) {

        TextView UserNameCheck = dilaog01.findViewById(R.id.UsernameCheck);
        if (b == false) {
            UserNameCheck.setVisibility(View.VISIBLE);
        } else {
            UserNameCheck.setVisibility(View.GONE);
        }
    }

    public void UserinfoCheck(boolean b) {

        TextView UserInfoCheck = dilaog01.findViewById(R.id.userinformationText);
        if (b == false) {
            UserInfoCheck.setVisibility(View.VISIBLE);
        } else {
            UserInfoCheck.setVisibility(View.GONE);
        }
    }

    public void EmailCheckhide(boolean b) {

        TextView EmailCheck = dilaog01.findViewById(R.id.emailCheck);
        if (b== false) {
            EmailCheck.setVisibility(View.VISIBLE);
        } else {
            EmailCheck.setVisibility(View.GONE);
        }
    }

    public void PwCheckhide(boolean b) {
        TextView PwCheck = dilaog01.findViewById(R.id.pwCheck);
        if (b==false) {
            PwCheck.setVisibility(View.VISIBLE);
        } else {
            PwCheck.setVisibility(View.GONE);
        }
    }

    public void Pw_cCheckhide(boolean b){
    TextView Pw_cCheck = dilaog01.findViewById(R.id.pw_cCheck);

        if (b==false) {
            Pw_cCheck.setVisibility(View.VISIBLE);
        } else {
            Pw_cCheck.setVisibility(View.GONE);
        }

    }



    public void loginhide(boolean hide){
        TextView login = dilaog02.findViewById(R.id.id_pw_Check);
        Log.d("하이드","로그인");
        if(hide==false){
            login.setVisibility(View.VISIBLE);
        }
        else{
            login.setVisibility(View.GONE);

        }
    }

    public void setIdCheckBool(boolean res){
        IdCheckBool = res;
    }
    public void setEmailCheckBool(boolean res){
        EmailCheckBool = res;
    }
    public void setUserNameCheckBool(boolean res){
        UserNameCheckBool = res;
    }



}
