package com.example.batterymonitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

		Map<String, Integer> levels = (Map<String, Integer>) sharedPref
				.getAll();

		SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");

		SortedSet<String> sortedLevelsKeys = null;

		if (levels != null && !levels.isEmpty()) {

			ArrayList<String> displayLevels = new ArrayList<String>();

			sortedLevelsKeys = new TreeSet<String>(levels.keySet());

			for (String time : sortedLevelsKeys) {
				try {
					Date d = new Date(Long.parseLong(time));
					displayLevels.add(dateFormat.format(d) + " - "
							+ levels.get(time) + "%");
				} catch (Exception e) {

				}
			}

			ListView list = (ListView) findViewById(R.id.list);
			list.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, displayLevels
							.toArray(new String[displayLevels.size()])));

			v.setText("Current level : " + levels.get(sortedLevelsKeys.last())
					+ " %");

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
