package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserDataDisplay extends AppCompatActivity {
   public Button user_logout_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_display);
    }
    public void goUserStart(View view)
    {
        user_logout_button=findViewById(R.id.user_logout_button);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
