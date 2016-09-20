package bree.com.longalermservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by bree on 2016/9/20.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context,MyService.class);
        context.startService(i);
    }
}
