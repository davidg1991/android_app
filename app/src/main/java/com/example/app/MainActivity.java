package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button button_admin;
    public Button button_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goAdminLogin(View view)
    {
        button_admin=findViewById(R.id.button_admin);
        Intent intent=new Intent(this,AdminLogin.class);
        startActivity(intent);
    }
    public void goUserLogin(View view)
    {
        button_user=findViewById(R.id.button_user);
        Intent intent=new Intent(this,UserLogin.class);
        startActivity(intent);
    }
}
