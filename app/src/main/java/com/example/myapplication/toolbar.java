//package com.example.myapplication;
//
//import android.os.Bundle;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.Switch;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class toolbar extends AppCompatActivity {
//
//    Switch darkmodeSwitch;
//    // 온클릭을 온크리에이트 밖에서도 쓰려고 여기다 선언
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_toolbar);
//        // 툴바 레이아웃에 연결
//
//        darkmodeSwitch = (Switch) findViewById(R.id.darkmode_switch);
//        //스위치를 연결
//
//        theme_switch();
//        // SQLite에 저장되있는 값 확인해서 버튼을 활성화하는 메소드
//
//        theme();
//        // 버튼이 활성화 되있으면 다크모드 설정해주는 메소드
//
//
//
//    }
//    public void theme(){
//        //버튼을 확인하여 테마 적용하는 메소드
//
//        darkmodeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //버튼이 활성화 되있는지 확인하는거 isCheckd = 온일때 true, 오프일때 false값이 저장됨
//
//                Setting.setBooleanData(getApplicationContext(),"theme",isChecked);
//                // setOnOffData에 isCheckd값을 보냄
//                ImageView ImageView = ((MainActivity)getApplicationContext()).getImageView();
//                Setting.setTheme(getApplicationContext(),ImageView);
//                // Setting 클래스에 있는 setTheme를 실행시킴
//            }
//        });
//    }
//
//    public void theme_switch(){
//        if(Setting.getBooleanData(getApplicationContext(),"theme") == true){
//            //Setting 클래스에 있는 getOnOffcheck 메소드를 실행시켜 리턴된 값이 true면 실행, 키값 theme를 보냄
//            darkmodeSwitch.setChecked(true);
//            //스위치 값을 true로 바꿈
//        }
//    }
//
//
//}
