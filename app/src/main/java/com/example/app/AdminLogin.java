package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {


    protected EditText adminName;
    protected EditText adminPassword;
    SharedPreferencesHelper sharedPreferencesHelper;
    Administrator admin=new Administrator("Admin","admin");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminName=findViewById(R.id.adminName);
        adminPassword=findViewById(R.id.adminPassword);

        sharedPreferencesHelper=new SharedPreferencesHelper(this);
    }
    public void goAdminSettings(View view)
    {
        String nameInput = adminName.getText().toString();
        String passwordInput = adminPassword.getText().toString();
        String adminName=admin.getAdministratorName();
        String adminPassword=admin.getPassword();
        Log.d("Test","The Name is "+ nameInput);
        Log.d("test", "The Password is " + passwordInput);

        if(adminName.equals(nameInput))
        {
            System.out.println("Admin name = " + admin.getAdministratorName());
            if (adminPassword.equals(passwordInput))
            {
                Log.d("Result", "Reached here");
                System.out.println("Admin Password = " + admin.getPassword());
                Intent intent=new Intent(this,AdminAccess.class);
                startActivity(intent);
            }
            else
            {
                Log.d("In Class", "PW: "+ admin.getPassword());
                Toast.makeText(AdminLogin.this, "Wrong password!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Log.d("In CLass", "Name : " + admin.getAdministratorName());
            Toast.makeText(AdminLogin.this, "Invalid user!", Toast.LENGTH_SHORT).show();
        }

    }

}
