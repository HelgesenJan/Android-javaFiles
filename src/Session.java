import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;


    public Session(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("dogcoach", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode", logggedin);
        editor.commit();
    }
    public void setNavn(String name){
        editor.putString("name", name);
        editor.commit();
    }

    public void setAttempts(){
        editor.putInt("attempts", 5);
        editor.commit();
    }

    public void setTime(){
        editor.putLong("time", System.currentTimeMillis());
    }

    public boolean loggedin(){
        return sharedPreferences.getBoolean("loggedInmode", false);
    }

    public boolean buttonDisabled(){
        return sharedPreferences.getBoolean("buttonDisabled", false);
    }

    public String getUsername() {
        String username = sharedPreferences.getString("name","default");
        return username;
    }

    public int getAttempts(){
        int time = sharedPreferences.getInt("attempts", 5);
        return time;
    }

    public String getTimes(){
        String time = sharedPreferences.getString("time", "");
        return time;
    }


}

