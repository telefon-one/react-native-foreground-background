package one.telefon.foregroundbackground;

import android.app.Activity;

import android.view.Window;
import android.view.WindowManager;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.*;
import com.facebook.react.ReactActivity;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;





public class ForegroundBackgroundModule extends ReactContextBaseJavaModule {
    ReactApplicationContext mContext;

    private static String LOG = "telefon.one.foregroundbackground.ForegroundBackgroundModule";

    public ForegroundBackgroundModule(ReactApplicationContext context) {
        super(context);
        mContext=context;
    }
    
    @Override
    public String getName() {
        return "ForegroundBackgroundModule";
    }

    @ReactMethod
    public void toForeground() {
        Log.d(LOG, "toForeground()");

        /*
         * PowerManager.WakeLock wl = mPowerManager.newWakeLock(
         * PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE |
         * PowerManager.FULL_WAKE_LOCK, "incoming_call" ); wl.acquire(10000);
         */

        Boolean mAppHidden = true;
        if (mAppHidden) {
            try {
                String ns = getApplicationContext().getPackageName();
                String cls = ns + ".MainActivity";

                Intent intent = new Intent(getApplicationContext(), Class.forName(cls));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.EXTRA_DOCK_STATE_CAR);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.putExtra("foreground", true);

                startActivity(intent);
            } catch (Exception e) {
                Log.w(LOG_TAG, "Failed to open application on received call", e);
            }
        }

        job(new Runnable() {
            @Override
            public void run() {
                // Brighten screen at least 10 seconds
                PowerManager.WakeLock wl = mPowerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP
                        | PowerManager.ON_AFTER_RELEASE | PowerManager.FULL_WAKE_LOCK, "incoming_call");
                wl.acquire(10000);

            }
        });

    }

    @ReactMethod
    public void toBackground() {
    }
}






