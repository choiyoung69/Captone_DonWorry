package com.example.donworry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class Log_In extends AppCompatActivity {

    private EditText loginID, loginPassword;
    private TextView signUp, reIdPassword;
    private Button  loginButton;
    private ImageView KakaoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginID = findViewById(R.id.loginID);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUp);
        reIdPassword = findViewById(R.id.reIdPassword);
        KakaoBtn = findViewById(R.id.KakaoBtn);

        //회원가입 버튼을 클릭 시 수행
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_In.this, Sign_Up.class);
                startActivity(intent);
            }
        });

        // 로그인 버튼 클릭 시 수행
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //editText에 현재 입력되어 있는 값을 가져온다.
//                String userID = InputID.getText().toString();
//                String userPassword = InputPassword.getText().toString();
                Response.Listener<String> resposeListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String loginID = jsonObject.getString("loginID");
                            String loginPassword = jsonObject.getString("loginPassword");

                            boolean success = jsonObject.getBoolean("success");
                            if(success) { // 로그인에 성공한 경우
                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Log_In.this, MainActivity.class);
                                intent.putExtra("loginID", loginID);
                                intent.putExtra("loginPassword", loginPassword);
                                startActivity(intent);
                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

            }
        });

        // 카카오 버튼 클릭 시 수행
        KakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}