package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserLogin extends AppCompatActivity {

    public Button user_login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }
    public void goUserDisplay(View view)
    {
        user_login_button=findViewById(R.id.user_login_button);
        Intent intent=new Intent(this,UserDataDisplay.class);
        startActivity(intent);
    }
}
