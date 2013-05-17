package com.example.batterymonitor;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.TextView;

public class Monitor extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        
        TextView textView = (TextView)findViewById(R.id.level);
        textView.setText("Hello Sriram");
        
        registerReceiver(mBatInfoReceiver, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.monitor, menu);
        return true;
    }
    
    
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent i) {
            int level = i.getIntExtra("level", 0);
        
            TextView tv = (TextView) findViewById(R.id.level);
            tv.setText("Battery Level: " + Integer.toString(level) + "%");
        }

    };
    
}
