package com.example.batterymonitor;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class BatteryInfoReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		int level = intent.getIntExtra("level", 0);
		Log.i("BatteryInfoReceiver", "Battery level " + level);

		SharedPreferences sharedPref = context.getSharedPreferences(
				context.getString(R.string.battery_monitor_pref),
				Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = sharedPref.edit();

		Map<String, Integer> levels = (Map<String, Integer>) sharedPref
				.getAll();

		// Remove the last key as we insert a new one
		SortedSet<String> sortedKeys = new TreeSet<String>(levels.keySet());
		if (sortedKeys.size() >= 10) {
			editor.remove(sortedKeys.first());
		}

		editor.putInt(String.valueOf(System.currentTimeMillis()), level);

		editor.commit();
	}
}
