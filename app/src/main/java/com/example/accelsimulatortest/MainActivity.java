package com.example.accelsimulatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    EditText etTxt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTxt1 = (EditText) findViewById(R.id.t1);
        // 获取传感器模拟器的传感器管理服务
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);}


    @Override
    protected void onResume()	{
        super.onResume();
        // 为系统的加速度传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onStop()	{
        // 取消注册
        mSensorManager.unregisterListener(this);
        super.onStop();
    }
    @Override
    protected void onPause(){
        mSensorManager.unregisterListener(this);
        super.onPause();
    }
    // 以下是实现SensorEventListener接口必须实现的方法
    // 当传感器精度改变时回调该方法。
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    // 当传感器的值发生改变时回调该方法
    public void onSensorChanged (SensorEvent event)	{
        float[] values = event.values;
        StringBuilder sb = new StringBuilder();
        sb.append("X方向上的加速度：");
        sb.append(values[0]);
        sb.append("\nY方向上的加速度：");
        sb.append(values[1]);
        sb.append("\nZ方向上的加速度：");
        sb.append(values[2]);
        etTxt1.setText(sb.toString());
    }


}
