package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class logindialog extends AppCompatActivity {

    public static void Login(final Context mContext, final String... params) {
        final CallAPI postLogin = new CallAPI(new CallAPI.AsyncResponse() { // 콜백 함수 생성
            @Override
            public void processFinish(String res) { // CALLAPI 안의 processFinish함수
                try {

                    JSONObject JsonObject = new JSONObject(res);
                    Log.d("json","됨");

                    JSONArray JsonArray = new JSONArray(JsonObject.getString("data"));
                    Log.d("json two","" + JsonArray);

                    JSONObject JsonreObject = new JSONObject(JsonArray.getString(0));
                    Log.d("json three",""+JsonreObject);
                    String re = JsonObject.getString("result");
                    int  index = Integer.parseInt(JsonreObject.getString("idx"));
                    String email = JsonreObject.getString("email");
                    String username = JsonreObject.getString("username");

                    Log.d("res",""+re);
                    Log.d("index", "" + index);
                    Log.d("username", "" + username);
                    Log.d("email", "" + email);
                    UserInfo UserInfo = new UserInfo();
                    if (re.equals("1")) {
                        Log.d("로그인", "로그인성공");
                        UserInfo.setIdx(index);
                        UserInfo.setEmail(email);
                        UserInfo.setUsername(username);
                        ((MainActivity) mContext).logok();
                    } else {
                        Log.d("로그인", "로그인실패");
                        ((MainActivity) mContext).logno();
                    }

                } catch (Exception e) {
                    Log.d("login_api", "Check 오류남");
                }
            }
        });
        postLogin.execute("http://52.231.199.17:88/is/Login.php", "id=" + params[0] + "&pw=" + params[1]);

    }
}