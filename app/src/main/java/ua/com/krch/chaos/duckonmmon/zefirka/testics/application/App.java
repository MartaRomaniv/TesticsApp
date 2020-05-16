package ua.com.krch.chaos.duckonmmon.zefirka.testics.application;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import ua.com.krch.chaos.duckonmmon.zefirka.testics.constant.Constants;

public class App extends Application {

    private static App INSTANCE;

    private SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        preferences = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
    }

    public static App getINSTANCE() {
        return INSTANCE;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }
}
