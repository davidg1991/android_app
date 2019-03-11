package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminDataDisplay extends AppCompatActivity {
    public Button manage_users_button;
    protected ListView admin_data_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_display);
        admin_data_display=findViewById(R.id.admin_data_display);
        loadListView();
    }
    public void goAdminUsersDisplay(View view)
    {
        manage_users_button=findViewById(R.id.manage_users_button);
        Intent intent=new Intent(this,AdminUsersDisplay.class);
        startActivity(intent);
    }
    public void loadListView()
    {
        List<DataInputs> dataInputsList=new ArrayList<>();
        dataInputsList.add(new DataInputs(8,8,8,8,8,8));//used as a test for the demo. Not final version

        ArrayList<String> dataInputsListText = new ArrayList<>();

        for (int i = 0; i < dataInputsList.size(); i++) {
            String temp = "";
            temp += "Oil Temperature: "+dataInputsList.get(i).getOilTemperature() + "\n";
            temp += "Oil Pressure: "+dataInputsList.get(i).getOilPressure() + "\n";
            temp += "Fuel Pressure: "+dataInputsList.get(i).getFuelPressure() + "\n";
            temp += "Coolant Temperature: "+dataInputsList.get(i).getCoolantTemperature() + "\n";
            temp += "Engine RPM: "+dataInputsList.get(i).getEngineRPM() + "\n";
            temp += "Gear: "+dataInputsList.get(i).getGear();

            dataInputsListText.add(temp);

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataInputsListText);
        admin_data_display.setAdapter(arrayAdapter);



    }


}
