import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IllnessAnalyzer extends AppCompatActivity {

    Database myDB;
    Button viewData;
    TextView textView;
    TextView dogText;
    ImageView dogImage;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> items = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.illness_analyzer);

        getSupportActionBar().setTitle(R.string.Chesym);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Kaller på konstruktøren i Database klassen
        myDB = new Database(this);
        viewData = findViewById(R.id.getList);
        listItems = getResources().getStringArray(R.array.symptoms);
        Arrays.sort(listItems);
        checkedItems = new boolean[listItems.length];
        dogImage = findViewById(R.id.dogimage);
        dogText = findViewById(R.id.dogtext);
        listView = findViewById(R.id.lista);

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < checkedItems.length; i++){
                    checkedItems[i] = false;
                    items.clear();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(IllnessAnalyzer.this);
                builder.setTitle(R.string.symptoms);
                builder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            items.add(position);
                        } else{
                            items.remove((Integer.valueOf(position)));
                        }
                    }
                });
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dogImage.setVisibility(View.INVISIBLE);
                        dogText.setVisibility(View.INVISIBLE);


                        ArrayList<String> list = new ArrayList<>();

                        final Cursor cursor = myDB.getIllnessData();
                        String item = "";
                        for(int i = 0; i < items.size(); i++){
                            item = listItems[items.get(i)];


                            cursor.moveToFirst();
                            do{
                                if ((cursor.getString(3).equals(item)) || (cursor.getString(4).equals(item))) {
                                    if(!list.contains(cursor.getString(1) + ": " + (cursor.getString(2)))){
                                        list.add(cursor.getString(1) + ": " + cursor.getString(2));
                                    }
                                }
                            }while(cursor.moveToNext());
                        }

                        Log.e("item: ", item);
                        Log.e("lista", list.toString());


                        cursor.close();

                        ListAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                        listView.setAdapter(listAdapter);


                    }
                });
                builder.setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton(R.string.clearall, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i < checkedItems.length; i++){
                            checkedItems[i] = false;
                            items.clear();
                            listView.setAdapter(null);
                        }
                        dogImage.setVisibility(View.VISIBLE);
                        dogText.setVisibility(View.VISIBLE);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}