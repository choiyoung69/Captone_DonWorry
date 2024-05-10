package com.example.donworry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sign_Up extends AppCompatActivity {

    private EditText InputName, InputID, InputPassword, InputRePassword, InputPhone;
    private Button signUPBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // 아이디 값 찾아주기
        InputName = findViewById(R.id.InputName);
        InputID = findViewById(R.id.InputID);
        InputPassword = findViewById(R.id.InputPassword);
        InputRePassword = findViewById(R.id.InputRePassword);
        InputPhone = findViewById(R.id.InputPhone);

        // 회원가입 버튼 클릭 시 수행
        signUPBtn = findViewById(R.id.signUPBtn);
        signUPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editText에 현재 입력되어 있는 값을 가져온다.
                String userName = InputName.getText().toString();
                String userID = InputID.getText().toString();
                String userPassword = InputPassword.getText().toString();
                String userRePassword = InputRePassword.getText().toString();
                String userPhone = InputPhone.getText().toString();


                // jason object 필요
            }
        });

    }
}