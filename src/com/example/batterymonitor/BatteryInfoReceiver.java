package com.example.batterymonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class BatteryInfoReceiver extends BroadcastReceiver {

	private static final String NOW = "NOW";
	private static final String PREV = "PREV";

	@Override
	public void onReceive(Context context, Intent intent) {

		int level = intent.getIntExtra("level", 0);
		Log.i("BatteryInfoReceiver", "Battery level " + level);

		SharedPreferences sharedPref = context.getSharedPreferences(
				context.getString(R.string.battery_monitor_pref),
				Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = sharedPref.edit();

		int prevLevel = sharedPref.getInt(NOW, 0);

		editor.putInt(NOW, level);
		editor.putInt(PREV, prevLevel);

		editor.commit();
	}

}
