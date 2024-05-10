package com.example.donworry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class Sign_Up extends AppCompatActivity {

    private EditText InputName, InputID, InputPassword, InputRePassword, InputPhone;
    private Button signUPBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 시작시 처음으로 실행되는 생명주기
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

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success) { // 회원 등록에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Sign_Up.this, Log_In.class);
                                startActivity(intent);
                            } else { // 회원 등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };


            }
        });

    }
}