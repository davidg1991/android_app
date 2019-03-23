package com.example.app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminDataDisplay extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    protected ImageView closeReconnect;
    protected Dialog reconnect;
    protected Button reconnect_button;

    private TextView sub_topic;
    String topicStr="DataDisplay";
    MqttAndroidClient client;
    private ProgressBar connection_progressBar;
    String MQTTHOST="tcp://broker.hivemq.com:1883";

    protected ListView admin_data_display_list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_display);

        reconnect=new Dialog(this);

        sub_topic=findViewById(R.id.sub_topic);
        admin_data_display_list=findViewById(R.id.admin_data_display_list);
        connection_progressBar=findViewById(R.id.connection_progressBar);

        //The following lines are for the navigation menu
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        m_connect();
        loadListView();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if(id==R.id.db)
        {

        startActivity(new Intent(this, AdminAccess.class));
        }
        else if (id==R.id.Data)
        {

        startActivity(new Intent(this, DataDisplaySettings.class));
        }
        else if (id==R.id.Contact_Driver)
        {

        startActivity(new Intent(this, AdminAccess.class));
        }
         else if (id==R.id.Manage_Users)
        {

        startActivity(new Intent(this, AdminUsersDisplay.class));
        }
        else if (id==R.id.Logout) {

        startActivity(new Intent(this, MainActivity.class));
        }

        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

  //Connect to the server
    private void m_connect()
    {
        connection_progressBar.setVisibility(View.VISIBLE);

        String clientId = MqttClient.generateClientId();
         client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST,
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener()
            {
                @Override
                public void onSuccess(IMqttToken asyncActionToken)
                {
                    // We are connected
                    Log.d("In MQTT_Connection", "onSuccess");
                    connection_progressBar.setVisibility(View.GONE);
                    Toast.makeText(AdminDataDisplay.this,"Connected",Toast.LENGTH_SHORT).show();
                    m_subscribe();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d("In MQTT_Connection", "onFailure");
                    connection_progressBar.setVisibility(View.GONE);
                    Show_reconnect_popup();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
  //opens a popup page where the user can connect to the server again
    private void Show_reconnect_popup()
    {
        reconnect.setContentView(R.layout.popup_failed_connection);
        closeReconnect= reconnect.findViewById(R.id.closeReconnect);
        reconnect_button=reconnect.findViewById(R.id.reconnect_button);
        closeReconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reconnect.dismiss();
            }
        });
        reconnect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_connect();
                reconnect.dismiss();
            }
        });
        reconnect.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        reconnect.show();


    }

    //subscribe to a topic in server
    public void m_subscribe()
    {
        String topic =topicStr;
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener()
            {
                @Override
                public void onSuccess(IMqttToken asyncActionToken)
                {
                    // The message was published
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // The subscription could not be performed, maybe the user was not
                    // authorized to subscribe on the specified topic e.g. using wildcards

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message)
            {
                sub_topic.setText(new String(message.getPayload()));
                //vibrator.vibrate(500);
                //ringtone.play();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
    
     public void m_publish()
    {
        String topic="Data display";
        int qos=2;
        String content="Message from the input file!";
        String clientId= MqttClient.generateClientId();
        String MQTTHOST="tcp://broker.hivemq.com:1883";
        MemoryPersistence persistence=new MemoryPersistence();

        try
        {
            MqttClient client=new MqttClient(MQTTHOST,clientId,persistence);
            MqttConnectOptions connectOptions=new MqttConnectOptions();
            connectOptions.setCleanSession(true);
            System.out.println("Connecting to broker: "+MQTTHOST);
            client.connect(connectOptions);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            client.publish(topic, message);
            System.out.println("Message published");
            client.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        }

        catch (MqttException e)
        {
            System.out.println("reason "+e.getReasonCode());
            System.out.println("msg "+e.getMessage());
            System.out.println("loc "+e.getLocalizedMessage());
            System.out.println("cause "+e.getCause());
            System.out.println("excep "+e);
            e.printStackTrace();

        }
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
        admin_data_display_list.setAdapter(arrayAdapter);


    }
    
    public void addData()
    {

        loadListView();

        if (!inputs.rangeOilTemperature() ||
                !inputs.rangeOilPressure() ||
                !inputs.rangeFuelPressure() ||
                !inputs.rangeFuelTemperature() ||
                !inputs.rangeCoolantTemperature() ||
                !inputs.rangeEngineRPM())
        {
            sendNotification();
        }
    }


    public void sendNotification()
    {

        String contentText = "The following issues of the sensor require attention: ";
        List<String> issues = new ArrayList<>();
        if (!inputs.rangeOilTemperature())
        {
            issues.add("oil temperature");
        }

        if (!inputs.rangeOilPressure())
        {
            issues.add("oil pressure");
        }

        if   (!inputs.rangeFuelPressure())
        {
            issues.add("fuel pressure");
        }

        if (!inputs.rangeFuelTemperature())
        {
            issues.add("Fuel temperature");
        }

        if (!inputs.rangeCoolantTemperature())
        {
            issues.add("Coolant temperature");
        }

        if (!inputs.rangeEngineRPM())
        {
            issues.add("Engine RPM");
        }

        contentText += TextUtils.join(", ", issues);

        //Builds the notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Warning!")
                .setContentText(contentText);

        //Creates and display the notification related to oil temperature
        Intent notificationIntent=new Intent(this,AdministratorDataDisplayActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(contentIntent);


        //Add as notification
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

    }//end of function



}
