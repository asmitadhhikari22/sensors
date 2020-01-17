package com.asmit.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class proximityActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView tvNear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        setTitle("Proximity Sensor");

        tvNear= findViewById(R.id.tvNear);
        proximity();
    }

    private void proximity() {

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor= sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener proxilistner = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <= 4){
                    tvNear.setText("Object is near");
                }else{
                    tvNear.setText("Objecct is Far");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null) {
            sensorManager.registerListener(proxilistner, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }
}
