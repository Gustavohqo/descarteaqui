package com.descarteaqui.descarteaqui;

/**
 * Created by Mafra on 05/09/16.
 */
import android.app.Application;
import com.facebook.Profile;

public class App extends Application{

    private Profile profile;

    private static App appSingleton;

    public static App getInstance(){
        if (appSingleton == null){
            System.out.println("Entrei");
            appSingleton = new App();
        }
        return appSingleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appSingleton = this;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setCurrentProfile(Profile profile) {
        this.profile = profile;
    }
}
