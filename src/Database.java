import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dogcoach.db";

    private static final String ILLNESS_TABLE = "illness_table";
    private static final String COL_1I = "ID";
    private static final String COL_2I = "Illness";
    private static final String COL_3I = "Description";
    private static final String COL_4I = "Symptomone";
    private static final String COL_5I = "Symptomtwo";

    private static final String USER_TABLE = "user_table";
    private static final String COL_1U = "ID";
    private static final String COL_2U = "Name";
    private static final String COL_3U = "Password";

    private static final String PLAN_TABLE = "plan_table";
    private static final String COL_1P = "ID";
    private static final String COL_2P = "Date";
    private static final String COL_3P = "Description";
    private static final String COL_4P = "user_fk";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("Database operations", "Database created...");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ILLNESS_TABLE + "  (ID INTEGER PRIMARY KEY AUTOINCREMENT, ILLNESS TEXT, DESCRIPTION TEXT, SYMPTOMONE, SYMPTOMTWO)");
        db.execSQL("CREATE TABLE " + USER_TABLE + "  (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE " + PLAN_TABLE + "  (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,DESCRIPTION TEXT, USER_FK INTEGER REFERENCES USER_TABLE(ID))");

        Log.d("Database operations", "Tables created...");


        // INSERTS USER TABLE
        db.execSQL("INSERT INTO " + USER_TABLE + " (" + COL_2U + ',' + COL_3U + ") Values ('Test', 'Pass')");

        // INSERTS ILLNESS TABLE
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Food allergy', 'A reaction to harmless substances in the food', 'Itching', 'Infection')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Flea allergy', 'Occurs in pets who are allergic or hypersensitive to the saliva fleas emit when they bite', 'Itching', 'Infection')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Epilepsy', 'A serious state that can cause cramps', 'Restlessness', 'Blank eyes')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Tapeworms', 'Intestinal parasitic worms with flat heads, common in dogs', 'Restlessness', 'Depression')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Cancer', 'A disease that comes from uncontrolled splicing of cells in the body', 'Increased hunger', 'Fat lumps')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Rabies', 'A virus that attacks the nervous system', 'Hypersensitive', 'Fear of water')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Acne', 'A benign disorder, hair follicles become irritated', 'Blackheads', 'Red bumps')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Dermatitis', 'Allergic reaction to something', 'Red skin', 'Red bumps')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Ibuprofen Toxicity', 'An anti-inflammatory medication has been consumed', 'Bloody feces', 'Nausea')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Actinomycosis', 'An infectious disease caused by gram positive, branching, pleomorphic', 'Skin swellings', 'Inflammation')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Infected teeth', 'Infected teeth can lead to abscesses, act quick', 'Facial swellings', 'Inflammation')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Spider Venom Toxicosis', 'The venom is a potent neurotoxin, opening channels at the presynaptic nerve terminal', 'Paralysis', 'Muscle tremors')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Capillaria plica', 'A worm that infects the urinary bladder and sometimes other parts of the urinary tract', 'Bloody urine', 'Painful urination')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Hypercapnia', 'An increase in the partial pressure of carbon dioxide in the arterial blood', 'Abnormal breathing', 'Weakness')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Nose dyspnea', 'Nose disease', 'Abnormal breathing', 'Infection')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Lungs dyspnea', 'Lung disease', 'Blank eyes', 'Infection')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Parvovirus', 'A spreading of feces', 'Tired', 'Weakness')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Chylothorax', 'Accumulation of lymphatic fluid in the chest cavity where the heart and lungs reside', 'Heart murmur', 'Coughing')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Fungal infections', 'Yeast and other fungi can be picked up in dirt or through the air.', 'Tired', 'Coughing')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Hepatotoxins', 'Hepatotoxins are toxic substance that can damage the liver', 'Coma', 'Hemorrhages')");
        db.execSQL("INSERT INTO " + ILLNESS_TABLE + " (" + COL_2I + ',' + COL_3I + ',' + COL_4I + ',' + COL_5I + ") Values ('Coran Snake Venom', 'The venom is a potent neurotoxic, can cause respiratory collapse', 'Paralysis', 'Shock')");

        // INSERTS PLAN TABLE
        db.execSQL("INSERT INTO " + PLAN_TABLE + " (" + COL_2P + ',' + COL_3P + ',' + COL_4P + ") Values ('22/02/1995', 'Veterinærbesøk', 1)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ILLNESS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PLAN_TABLE);
        onCreate(db);
    }

    public void addInformation(SQLiteDatabase db, String username, String password){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Users.UserEntry.USERNAME, username);
        contentValues.put(Users.UserEntry.PASSWORD, password);
        db.insert(USER_TABLE, null, contentValues);
        Log.d("Database operations", "User created");
    }

    public boolean addNoteData(String date, String description, String username){

        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("Note date", date);
        Log.e("Note description", description);
        Log.e("Note username", username);

        if(username != null){
            db.execSQL("INSERT INTO " + PLAN_TABLE + " (" + COL_2P + ',' + COL_3P + ',' + COL_4P + ") Values ('"+date+"','"+description+"',(SELECT ID FROM USER_TABLE WHERE NAME='"+username+"'))");
            return true;
        }else{
            return false;
        }

    }


    public Cursor getIllnessData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM  " + ILLNESS_TABLE, null);
        return result;
    }

    public Cursor getUserPlan(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT P.*, U.NAME FROM PLAN_TABLE AS P, USER_TABLE AS U WHERE P.USER_FK = U.ID ORDER BY P.DATE", null);
        return result;
    }


    public boolean getUserData(String name, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM  " + USER_TABLE + " WHERE " + COL_2U + " = " + "'"+name+"'" + " and " + COL_3U + " = " + "'"+password+"'";

        Cursor result = db.rawQuery(selectQuery, null);

        result.moveToNext();
        if(result.getCount() > 0){
            return true;
        }

        result.close();
        db.close();
        return false;
    }
}
