package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    static boolean[] Check= new boolean[5];
    static String[] ErrCheck = new String[5];




    public static void UserInformationCheck(final Context mContext, final String... params){
        final CallAPI postReqId = new CallAPI(new CallAPI.AsyncResponse() { // 콜백 함수 생성
            @Override
            public void processFinish(String res) { // CALLAPI 안의 processFinish함수
                try {

                    JSONObject JsonObject = new JSONObject(res);
                    String st = JsonObject.getString("result");

                    Log.d("Id", ""+st);
                    if(st.equals("1")){
                        Check[0] = true;
                        ((MainActivity) mContext).setIdCheckBool(true);
                    } else{
                        Check[0] = false;
                        ErrCheck[0] = "IdCheck";
                        ((MainActivity) mContext).setIdCheckBool(false);
                    }

                } catch (Exception e) {
                    Log.d("Id_api","Check 오류남");
                }
            }});
        final CallAPI postReqEmail = new CallAPI(new CallAPI.AsyncResponse() { // 콜백 함수 생성
            @Override
            public void processFinish(String res) { // CALLAPI 안의 processFinish함수
                try {

                    JSONObject JsonObject = new JSONObject(res);
                    String st = JsonObject.getString("result");
                    Log.d("Email", ""+st);
                    if(st.equals("1")){
                        Check[1] = true;
                        ((MainActivity) mContext).setEmailCheckBool(true);
                    } else{
                        Check[1] = false;
                        ErrCheck[1] = "EmailCheck";

                        ((MainActivity) mContext).setEmailCheckBool(false);
                    }



                } catch (Exception e) {
                    Log.d("Email_api","Check 오류남");
                }
            }});
        final CallAPI postReqUserName = new CallAPI(new CallAPI.AsyncResponse() { // 콜백 함수 생성
            @Override
            public void processFinish(String res) { // CALLAPI 안의 processFinish함수
                try {

                    JSONObject JsonObject = new JSONObject(res);
                    String st = JsonObject.getString("result");

                    Log.d("Username", ""+st);
                    if(st.equals("1")){
                        Check[2] = true;
                        ((MainActivity) mContext).setUserNameCheckBool(true);
                    } else{
                        Check[2] = false;
                        ((MainActivity) mContext).setUserNameCheckBool(false);
                        ErrCheck[0] = "UserNameCheck";

                    }
                    if(Check[0]==true&&Check[1]==true&&Check[2]==true){
                        ((MainActivity) mContext).Regok();
                        Register(params[0],params[1],params[2],params[3]);
                    }else{
                        ((MainActivity) mContext).Regno(Check[0],Check[1],Check[2]);
                    }

                } catch (Exception e) {
                    Log.d("Username_api","Check 오류남");
                }
            }});

        String[] APIPHP = new String[] {"IdCheck.php","EmailCheck.php","UsernameCheck.php"};
        if(params[0].length()<=15) {
            postReqId.execute("http://52.231.199.17:88/is/" + APIPHP[0], "id=" + params[0]);

        }else{
            postReqId.execute("http://52.231.199.17:88/is/" + APIPHP[0], "id=");

        }
        if(params[1].length()<=30) {
            postReqEmail.execute("http://52.231.199.17:88/is/" + APIPHP[1], "email=" + params[1]);

        }else{
            postReqEmail.execute("http://52.231.199.17:88/is/" + APIPHP[1], "email=");
        }
        if (params[2].length()<=8) {
            postReqUserName.execute("http://52.231.199.17:88/is/" + APIPHP[2], "username=" + params[2]);
        }else{
            postReqUserName.execute("http://52.231.199.17:88/is/" + APIPHP[2], "username=");
        }


    }
    public static void Register(String... params) {
        final CallAPI postReq = new CallAPI(new CallAPI.AsyncResponse() { // 콜백 함수 생성
            @Override
            public void processFinish(String res) { // CALLAPI 안의 processFinish함수
                try {

                    JSONObject JsonObject = new JSONObject(res);
                    String st = JsonObject.getString("result");

                    Log.d("Id", "" + st);


                } catch (Exception e) {
                    Log.d("Register", "Check 오류남");
                }
            }
        });
        Log.d("Reg","id="+params[0]+"&email="+params[1]+"&nickname="+params[2]+"&pw="+params[3]);
        postReq.execute("http://52.231.199.17:88/is/Register.php","id="+params[0]+"&email="+params[1]+"&nickname="+params[2]+"&pw="+params[3]);

    }


    public boolean password_Condition_Check(String password) {
        String pattern1 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,15}$";
        Matcher match = Pattern.compile(pattern1).matcher(password);
        Log.d("패스워드", "" + password + "재확인");
        if (match.find() == true) {
            Log.d("pass", "확인됨");
            Log.d("pass", "" + password.toString());
            return true;
        } else {
            return false;
        }
    }
    public boolean password_Same_Check(String password,String password_con){
        if(password.equals(password_con)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean password_finish_Check(boolean Con,boolean Same){
        if(Con==true&&Same==true){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean email_Regular_Check(String Email){
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(Email);

        if(m.matches()) {
            return true;
        } else {
            return false;
        }

    }
}
