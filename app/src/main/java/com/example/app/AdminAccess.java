package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminAccess extends AppCompatActivity {
    public Button admin_logout;
    public Button new_session_button;
    public Button admin_data_display_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_access);
    }

    public void goStartPage(View view)
    {
        admin_logout=findViewById(R.id.admin_logout);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void goSavedSession(View view)
    {
        admin_logout=findViewById(R.id.admin_logout);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void goDisplaySettings(View view)
    {
        admin_data_display_button=findViewById(R.id.admin_data_display_button);
        Intent intent=new Intent(this,DataDisplaySettings.class);
        startActivity(intent);
    }
    public void goNewSession(View view)
    {
        new_session_button=findViewById(R.id.new_session_button);
        Intent intent=new Intent(this,AdminDataDisplay.class);
        startActivity(intent);
    }
}
