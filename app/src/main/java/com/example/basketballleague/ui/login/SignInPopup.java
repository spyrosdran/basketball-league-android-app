package com.example.basketballleague.ui.login;

import android.app.Activity;
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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignInPopup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        View popup = (View) findViewById(R.id.popup_signin);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.BOTTOM;
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(width, (int) (height * 0.8));

        MaterialButton guest = (MaterialButton) findViewById(R.id.btn_cont_as_guest);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInPopup.this, MainActivity.class);
                intent.putExtra("type", "normal");
                intent.putExtra("display", "Guest");
                startActivity(intent);
            }
        });

        MaterialButton signin = (MaterialButton) findViewById(R.id.btn_signin_popup);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInPopup.this, MainActivity.class);

                TextView txt_username = (TextView) findViewById(R.id.txt_username);
                String username = txt_username.getText().toString();

                TextView txt_password = (TextView) findViewById(R.id.txt_password);
                String password = txt_password.getText().toString();

                String type = login(username,password);
                if(type != null){
                    intent.putExtra("type", type);
                    intent.putExtra("display", username);
                    startActivity(intent);
                    finish();
                }
                else{
                    GradientDrawable border = new GradientDrawable();
                    border.setStroke(2, getResources().getColor(R.color.orangeForeground));
                    border.setCornerRadius(15);
                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        txt_username.setBackgroundDrawable(border);
                        txt_password.setBackgroundDrawable(border);
                    } else {
                        txt_username.setBackground(border);
                        txt_password.setBackground(border);
                    }
                }
            }
        });


        TextView signup = (TextView) findViewById(R.id.signup_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInPopup.this, SignUpPopup.class));
                finish();
            }
        });
    }

    public String login(String username, String password){
        String type = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            String url ="http://192.168.1.129/basketleague/login.php";
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("uname","\"" + username + "\"")  //put question mark symbol to specify that it is a string value into th URL
                    .addFormDataPart("pass","\"" + password + "\"")
                    .build();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            String responseStr = response.body().string();
            System.out.println(responseStr);
            if(!(responseStr.equals("Not an existing user") || responseStr.equals("Failed to connect to Database"))){
                type = responseStr;
            }
            else if(responseStr.equals("Not an existing user")){
                Toast toast = Toast.makeText(SignInPopup.this, "Invalid username or password", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
            }
            else if(responseStr.equals("Failed to connect to Database")){
                Toast toast = Toast.makeText(SignInPopup.this, "Database connection error", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return type;
    }

}