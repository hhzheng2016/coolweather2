package com.weather.app.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.weather.app.util.HttpCallbackListener;
import com.weather.app.util.HttpUtil;
import com.weather.app.util.Utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by hasee on 2016/11/10.
 */

public class AutoUpdateService extends Service {
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather();
            }
        }).start();

        AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour=8*60*60*1000;//每8小时自动更新天气
        long triggerTime= SystemClock.elapsedRealtime()+anHour;
        Intent intent1=new Intent(this,AutoUpdateReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,intent1,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime,pi);
        return super.onStartCommand(intent,flags,startId);
    }


    /**
     * 更新天气信息
     */
    private void updateWeather() {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String cityName=prefs.getString("city_name","");
        String weatherCode=null;

        //修改后的代码 在saveWeatherInfo()方法中，没有存储weatherCode信息
        //只能通过获取城市名字，转码  再进行查询
        //http://wthrcdn.etouch.cn/weather_mini?city=%E9%95%BF%E5%AE%89
        try{
            weatherCode= URLEncoder.encode(cityName,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        String address="http://wthrcdn.etouch.cn/weather_mini?city="+weatherCode;
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Utility.handleWeatherResponse(AutoUpdateService.this,response);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
