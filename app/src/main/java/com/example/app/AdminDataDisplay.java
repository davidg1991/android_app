package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminDataDisplay extends AppCompatActivity {
    public Button manage_users_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_display);
    }
    public void goAdminUsersDisplay(View view)
    {
        manage_users_button=findViewById(R.id.manage_users_button);
        Intent intent=new Intent(this,AdminUsersDisplay.class);
        startActivity(intent);
    }


}
