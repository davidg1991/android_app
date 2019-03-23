package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminAccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_access);
    }

    public void goStartPage(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void goSavedSession(View view)
    {
        Intent intent=new Intent(this,SavedSession.class);
        startActivity(intent);
    }
    public void goNewSession(View view)
    {
        Intent intent=new Intent(this,AdminDataDisplay.class);
        startActivity(intent);
    }
}
