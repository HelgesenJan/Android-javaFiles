import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Session session;
    private Button button;
    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.main);

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }
        user = findViewById(R.id.user);
        user.setText(session.getUsername());

        Button button = findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        ImageView vetImg = findViewById(R.id.vetImg);
        ImageView plannerImg = findViewById(R.id.plannerImg);
        ImageView symptomsImg = findViewById(R.id.symptomsImg);
        ImageView dogcareImg = findViewById(R.id.dogcareImg);

        vetImg.setImageResource(R.drawable.veterinary);
        plannerImg.setImageResource(R.drawable.planner);
        symptomsImg.setImageResource(R.drawable.symptoms);
        dogcareImg.setImageResource(R.drawable.dogcare);

        CardView veterinary = findViewById(R.id.veterinary);
        CardView planner = findViewById(R.id.planner);
        CardView symptoms = findViewById(R.id.symptoms);
        CardView dogcare = findViewById(R.id.dogcare);

        veterinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i){
                            case DialogInterface.BUTTON_POSITIVE:
                                Uri mapUri = Uri.parse("geo: 60.153105, 10.262782?z=17&q=dyreklinikk buskerud");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                                mapIntent.setPackage("com.google.android.apps.maps");
                                startActivity(mapIntent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialogInterface.cancel();
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.google).setPositiveButton(R.string.yes, listener).setNegativeButton(R.string.no, listener).show();

            }
        });
        planner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Planner.class);
                startActivity(intent);
            }
        });
        symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IllnessAnalyzer.class);
                startActivity(intent);
            }
        });
        dogcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dogcare.class);
                startActivity(intent);
            }
        });
    }


    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this, Login.class));
    }
}
