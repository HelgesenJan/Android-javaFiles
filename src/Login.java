import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private Button register;
    private int counter = 5;
    private Database myDB;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(R.string.login);

        myDB = new Database(this);
        session = new Session(this);
        name = (EditText)findViewById(R.id.loginName);
        password = (EditText)findViewById(R.id.loginPassword);
        info = (TextView) findViewById(R.id.loginCounter);
        login = (Button)findViewById(R.id.loginButton);
        register = (Button)findViewById(R.id.registerUser);

        if(session.loggedin()){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
        final String attemptsLeft = getString(R.string.attempsleft);

        info.setText(attemptsLeft + String.valueOf(counter));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegisterUser.class);
                startActivity(intent);
            }
        });
    }

    private void login(){
        final String attemptsLeft = getString(R.string.attempsleft);
        String checkName = name.getText().toString();
        String checkPassword = password.getText().toString();
        if (myDB.getUserData(checkName, checkPassword)) {
            session.setNavn(checkName);
            session.setLoggedin(true);
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(this, R.string.loginFail, Toast.LENGTH_SHORT).show();
            counter--;

            info.setText(attemptsLeft + String.valueOf(counter));

            if (counter == 0) {
                login.setEnabled(false);

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        info.setText(getString(R.string.toomany));
                        try {
                            Thread.sleep(2 *
                                    60 *
                                    1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Login.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                final String attemptsLeft = getString(R.string.attempsleft);
                                login.setEnabled(true);
                                counter = 5;
                                info.setText(attemptsLeft + String.valueOf(counter));

                            }
                        });
                    }
                }).start();
            }
        }
    }

}
