package com.descarteaqui.descarteaqui.controllers;

/**
 * Created by kelvin on 07/09/16.
 */
import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.facebook.Profile;


public class App extends Application{

    private static App appSingleton;
    private GoogleSignInAccount info;
    private Profile facebookProfile;

    public static App getInstance(){
        return appSingleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appSingleton = this;
    }

    public Profile getFacebookProfile() {return facebookProfile; }

    public void setFacebookProfile(Profile profile){this.facebookProfile = profile;}

    public GoogleSignInAccount getUserGoogleInfo() {
        return info;
    }

    public void setUserGoogleInfo(GoogleSignInAccount info) {
        this.info = info;
    }
}