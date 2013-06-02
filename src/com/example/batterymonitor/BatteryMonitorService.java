package com.example.batterymonitor;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class BatteryMonitorService extends Service {
	
	AlarmMonitor m = new AlarmMonitor();
	BatteryInfoReceiver batteryInfoReceiver = new BatteryInfoReceiver();

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("BatteryMonitorService", "Service Started");
        m.SetAlarm(getApplicationContext());
        registerReceiver(batteryInfoReceiver, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("BatteryMonitorService", "Service destroyed");
		unregisterReceiver(batteryInfoReceiver);
	}
}
