package com.example.eexport.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.eexport.Login;

import java.util.HashMap;

public class Session {

    SharedPreferences sharePr;
    SharedPreferences.Editor shareEdi;
    Context context;
    int Private_mode = 0;
    private static final String PREF_NAME = "AndroidHivePref";

    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";

    public Session(Context _context) {
        this.context = _context;
        sharePr = _context.getSharedPreferences(PREF_NAME, Private_mode);
        shareEdi = sharePr.edit();

    }

    public void createLoginSession(String phone, String password) {
        shareEdi.putBoolean(IS_LOGIN, true);
        shareEdi.putString(KEY_PHONE, phone);
        shareEdi.putString(KEY_PASSWORD, password);
        shareEdi.commit();
//        Log.d("Session","Phone :"+phone);
//        Log.d("Session","Password :"+password);
    }

    public boolean isLoggedIn() {
        return sharePr.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent intent = new Intent(context, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_PHONE, sharePr.getString(KEY_PHONE, null));
        user.put(KEY_PASSWORD, sharePr.getString(KEY_PASSWORD, null));
        return user;
    }

    public void logOutUser() {
        shareEdi.clear();
        shareEdi.commit();

        Intent intent = new Intent(context, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }


}
