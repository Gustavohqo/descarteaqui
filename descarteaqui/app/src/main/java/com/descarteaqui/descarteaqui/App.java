package com.descarteaqui.descarteaqui;

/**
 * Created by kelvin on 07/09/16.
 */
import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class App extends Application{

    private static App appSingleton;
    private GoogleSignInAccount info;

    public static App getInstance(){
        return appSingleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appSingleton = this;
    }


    public GoogleSignInAccount getUserGoogleInfo() {
        return info;
    }

    public void setUserGoogleInfo(GoogleSignInAccount info) {
        this.info = info;
    }
}