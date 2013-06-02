package com.example.batterymonitor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class Monitor extends Activity {

	AlarmMonitor m = new AlarmMonitor();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitor);

		// start background service to collection battery data
		startService(new Intent(this, BatteryMonitorService.class));

		SharedPreferences sharedPref = getApplicationContext()
				.getSharedPreferences("battery_monitor_pref_file",
						Context.MODE_PRIVATE);

		TextView v = (TextView) findViewById(R.id.level);

		if (sharedPref.contains("NOW")) {
			Log.i("test", "String found");
			v.setText("Current level : "
					+ String.valueOf(sharedPref.getInt("NOW", 0)) + " %");
		} else {
			v.setText("No Data found !!");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.monitor, menu);
		return true;
	}

}
