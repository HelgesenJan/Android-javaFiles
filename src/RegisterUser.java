import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {
    EditText e_username;
    EditText e_passOne;
    EditText e_passTwo;
    String username;
    String passOne;
    String passTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(R.string.reguser);

        setContentView(R.layout.activity_register_user);
        e_username = findViewById(R.id.addUser);
        e_passOne = findViewById(R.id.addpwOne);
        e_passTwo = findViewById(R.id.addpwTwo);
    }

    public void registerData(View view){
        username = e_username.getText().toString();
        passOne = e_passOne.getText().toString();
        passTwo = e_passTwo.getText().toString();

        if((passOne.equals(passTwo)) && (!username.isEmpty()) && (!passOne.isEmpty())){
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute("add_info", username, passOne);
            finish();
        }else if(!passOne.equals(passTwo)){
            Log.d("Database operations", "Passwords didn't match");
            Toast.makeText(this, R.string.passnomatch, Toast.LENGTH_SHORT).show();
        }else if(username.isEmpty() || passOne.isEmpty()){
            Log.d("Database operations", "Fields can't be empty");
            Toast.makeText(this, R.string.cantbempty, Toast.LENGTH_SHORT).show();
        }else{
            Log.d("Database operations", "Unable to add user");
            Toast.makeText(this, R.string.unableadduser, Toast.LENGTH_SHORT).show();
        }
    }
}
