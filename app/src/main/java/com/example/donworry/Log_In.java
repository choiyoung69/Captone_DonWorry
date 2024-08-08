package com.example.donworry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

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
                // Calendar 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // "calendar"라는 extra 데이터를 넣어 Calendar 화면에서 필요한 경우를 구분할 수 있습니다.
                intent.putExtra("destination", "calendar");
                // Intent를 사용하여 Calendar 화면으로 이동
                startActivity(intent);
            }
            /*

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

            }*/
        });

        // 카카오 버튼 클릭 시 수행
        // 카카오가 설치되어 있는지 확인 하는 메서드 (카카오에서 제공하는 콜백 객체를 이용)
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                // 토큰이 전달이 되면 로그인이 성공한 것이고 토큰이 전달되지 않았다면 로그인 실패
                kakaoLoginProcess();
                return null;
            }
        };
        KakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카카오톡에 핸드폰에 설치 되어 있는지 확인함
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(Log_In.this)) { // 카카오톡이 설치 돼 있으면
                    UserApiClient.getInstance().loginWithKakaoTalk(Log_In.this, callback);
                } else { // 카카오톡이 설치 돼 있지 않으면
                    UserApiClient.getInstance().loginWithKakaoAccount(Log_In.this, callback);
                }
            }
        });

    }
    private void kakaoLoginProcess() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                // 로그인이 되어있으면
                if (user != null) {
                    //유저의 아이디
                    Log.d("TAG","로그인 됨");
                    // Calendar 화면으로 이동하는 Intent 생성
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    // "calendar"라는 extra 데이터를 넣어 Calendar 화면에서 필요한 경우를 구분할 수 있습니다.
                    intent.putExtra("destination", "calendar");
                    // Intent를 사용하여 Calendar 화면으로 이동
                    startActivity(intent);

                } else {
                    // 로그인이 안되어 있을 때
                    Log.d("TAG", "로그인이 안되어 있음");
                }
                return null;
            }
        });
    }
}