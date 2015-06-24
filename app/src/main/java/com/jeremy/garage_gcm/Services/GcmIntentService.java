package com.jeremy.garage_gcm.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.jeremy.garage_gcm.Receivers.GcmBroadcastReceiver;

/**
 * Created by Jeremy on 6/23/2015.
 */
public class GcmIntentService extends IntentService {

    private static final int NOTIFICATION_ID = 0;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        GoogleCloudMessaging googleCloudMessaging = GoogleCloudMessaging.getInstance(this);
        String messageType = googleCloudMessaging.getMessageType(intent);


        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}