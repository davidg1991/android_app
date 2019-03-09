package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminLogin extends AppCompatActivity {

    public Button admin_login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }
    public void goAdminSettings(View view)
    {
        admin_login_button=findViewById(R.id.admin_login_button);
        Intent intent=new Intent(this,AdminAccess.class);
        startActivity(intent);
    }
}
