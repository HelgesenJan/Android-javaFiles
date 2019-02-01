import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Planner extends AppCompatActivity {
    private Database myDB;
    private Session session;
    private ListView myList;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    TextView date;
    private Button addNote;
    private EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planner);

        getSupportActionBar().setTitle(R.string.createnotes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        myDB = new Database(this);
        session = new Session(this);
        myList = findViewById(R.id.myList);
        listItem = new ArrayList<>();
        date = findViewById(R.id.date);
        addNote = findViewById(R.id.addNote);
        desc = findViewById(R.id.desc);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Planner.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String stringDate = dayOfMonth + "/" + month + "/" + year;
                date.setText(stringDate);
            }
        };

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newDate = date.getText().toString();
                String newDesc = desc.getText().toString();
                String username = session.getUsername();

                if((newDate.length() != 0) &&(newDesc.length() != 0) && (username.length() != 0)){
                    Boolean aBoolean = myDB.addNoteData(newDate, newDesc, username);
                    if(aBoolean){
                        emptyUserPlan();
                        getUserPlan();
                        date.setText("");
                        desc.setText("");
                    }else{
                        Toast.makeText(Planner.this, "ID not found, please relog", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Planner.this, R.string.cantbempty, Toast.LENGTH_SHORT).show();
                }
            }
        });


        if(!session.loggedin()){
            logout();
        }



        getUserPlan();

    }


    private void getUserPlan() {
        Cursor cursor = myDB.getUserPlan();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                if(session.getUsername().equals(cursor.getString(4))){
                    listItem.add(cursor.getString(1) + ": " + cursor.getString(2));
                }
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            myList.setAdapter(adapter);
        }
    }

    private void emptyUserPlan(){
        listItem.clear();
        myList.setAdapter(null);

    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(Planner.this, Login.class));
    }
}
