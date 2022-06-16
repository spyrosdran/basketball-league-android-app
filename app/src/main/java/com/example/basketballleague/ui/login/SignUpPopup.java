package com.example.basketballleague.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basketballleague.MainActivity;
import com.example.basketballleague.R;
import com.google.android.material.button.MaterialButton;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        View popup = (View) findViewById(R.id.popup_signup);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.BOTTOM;
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(width, (int) (height * 0.8));

        TextView signin = (TextView) findViewById(R.id.signin_btn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpPopup.this, SignInPopup.class));
                finish();
            }
        });

        MaterialButton signup = (MaterialButton) findViewById(R.id.btn_signup_popup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: write sign up code here
                TextView txt_uname = (TextView) findViewById(R.id.txt_username_signup);
                String username = txt_uname.getText().toString();
                TextView txt_pass = (TextView) findViewById(R.id.txt_password_signup);
                String password = txt_pass.getText().toString();
                String signupResponse = signup(username, password);
                if(signupResponse.equals("User already exists")){
                    GradientDrawable border = new GradientDrawable();
                    border.setStroke(2, getResources().getColor(R.color.orangeForeground));
                    border.setCornerRadius(15);
                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        txt_uname.setBackgroundDrawable(border);
                    } else {
                        txt_uname.setBackground(border);
                    }

                    Toast toast = Toast.makeText(SignUpPopup.this, signupResponse, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();
                }
                else if(signupResponse.equals("Sign up successfully")){
                    Toast toast = Toast.makeText(SignUpPopup.this, signupResponse, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();

                    Intent intent = new Intent(SignUpPopup.this, MainActivity.class);
                    intent.putExtra("type", "normal");
                    intent.putExtra("display", username);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(SignUpPopup.this, signupResponse, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();
                }
            }
        });
    }

    public String signup(String username, String password){
        String signupResponse = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            String url ="http://192.168.1.5/basketleague/signup.php";
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("uname","\"" + username + "\"")  //put question mark symbol to specify that it is a string value into th URL
                    .addFormDataPart("pass","\"" + password + "\"")
                    .build();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            signupResponse = response.body().string();
        } catch(Exception e){
            e.printStackTrace();
        }

        return signupResponse;
    }
}