package test.uts.hotel.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import test.uts.hotel.Model.User;
//import android.preference.PreferenceManager;

public class SharedPreference {

    private static SharedPreference   sharedPreference;
    public static final String PREFS_NAME = "HOTEL_PREF";
    public static final String PREFS_KEY = "AOP_PREFS_String";



    public static SharedPreference getInstance()
    {
        if (sharedPreference == null)
        {
            sharedPreference = new SharedPreference();
        }
        return sharedPreference;
    }

    public SharedPreference() {
        super();
    }

    public void saveUser(Context context , User user) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString("email", user.getEmail());

        editor.commit(); //4
    }

    public void saveLogin(Context context,boolean status){
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putBoolean("login", status);

        editor.commit(); //4
    }

    public Boolean getLogin(Context context) {
        SharedPreferences settings;
        String text = "";
        //  settings = PreferenceManager.getDefaultSharedPreferences(context);

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Boolean login  = (settings.getBoolean("login",false));
        return login ;
    }

    public User getUser(Context context) {
        User user = new User();
        SharedPreferences settings;
        String text = "";
        //  settings = PreferenceManager.getDefaultSharedPreferences(context);

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        user.setPhoneNumber(settings.getString("phoneNumber"," "));
        user.setEmail(settings.getString("email"," "));
        user.setUserName(settings.getString("username"," "));
        return user ;
    }

    public String getValue(Context context , String Key) {
        SharedPreferences settings;
        String text = "";
        //  settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(Key, "");
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context , String value) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(value);
        editor.commit();
    }
}
