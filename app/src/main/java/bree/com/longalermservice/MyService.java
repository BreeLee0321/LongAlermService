package bree.com.longalermservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

public class MyService extends Service {
    public MyService() {
    }
    private MyBinder binder;
    private String info="init";
    class MyBinder extends Binder {
        public void setInfo(String info){
        }
        public String getInfo(){
            return info;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder=new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run() {
                info="";
//                binder.setInfo(info=new Date().toString());
            }
        }).start();
        AlarmManager manger= (AlarmManager) getSystemService(ALARM_SERVICE);
        int anMin=10*1000;//一分钟
        long triggerAlTime= SystemClock.elapsedRealtime()+anMin;
        Intent i=new Intent(this,AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        manger.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAlTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy");
        startActivity(new Intent(getApplicationContext(),MyService.class));
    }
}
