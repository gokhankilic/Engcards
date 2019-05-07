package tr.com.gokhan.kilic.engcards.shared;

import android.app.Application;
import android.content.Context;

public class EngcardsApp extends Application {
    private static Context context;

    public static Context getAppContext(){
        return EngcardsApp.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        EngcardsApp.context = getApplicationContext();
    }
}
