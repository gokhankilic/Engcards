package tr.com.gokhan.kilic.engcards.activities.shared;

import android.content.Context;
import android.content.SharedPreferences;
import tr.com.gokhan.kilic.engcards.activities.shared.EngcardsApp;
import com.google.gson.Gson;

import tr.com.gokhan.kilic.engcards.models.UserModel;


public class UserDefaults {

    public static String ENGCARDS_PREF = "engcards";
    public static String USER_PREF = "userpref";

    public static SharedPreferences getSharedPreferences() {

        Context c = EngcardsApp.getAppContext();
        SharedPreferences prefs = c.getSharedPreferences(ENGCARDS_PREF,Context.MODE_PRIVATE);
        return prefs;
    }

    public static void cleanSharedPreferences() {
        getEditor().clear().commit();

    }

    public static SharedPreferences.Editor getEditor() {
        SharedPreferences prefs = getSharedPreferences();
        return prefs.edit();
    }

    public static void setUserAccount(UserModel userModel) {
        SharedPreferences.Editor editor = getEditor();
        if (userModel == null) {
            editor.putString(USER_PREF, "");
        } else {
            editor.putString(USER_PREF, new Gson().toJson(userModel));
        }
        editor.commit();
    }


    public static UserModel getUserAccount() {
        SharedPreferences prefs = getSharedPreferences();
        String userAccountStr = prefs.getString(USER_PREF, "");
        if (userAccountStr.equals("") || userAccountStr == null) {
            return null;
        }
        return new Gson().fromJson(userAccountStr, UserModel.class);
    }

}
