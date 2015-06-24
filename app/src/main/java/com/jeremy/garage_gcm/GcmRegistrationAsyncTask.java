package com.jeremy.garage_gcm;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.jeremy.garage_gcm.backend.registration.Registration;

public class GcmRegistrationAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String SENDER_ID = "487046872110";
    private static Registration mRegistration = null;
    private GoogleCloudMessaging mGoogleCloudMessaging;
    private Context mContext;

    public GcmRegistrationAsyncTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        String regId;

        if (mRegistration == null) {
            Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://garage-gcm-demo.appspot.com/_ah/api/");

            mRegistration = builder.build();
        }

        try {
            if (mGoogleCloudMessaging == null) {
                mGoogleCloudMessaging = GoogleCloudMessaging.getInstance(mContext);
            }

            regId = mGoogleCloudMessaging.register(SENDER_ID);
            mRegistration.register(regId).execute();

        } catch (Exception ex) {
            ex.printStackTrace();

            return ex.getMessage();
        }

        return "Device Registered, Registration ID=" + regId;
    }

    @Override
    protected void onPostExecute(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
}