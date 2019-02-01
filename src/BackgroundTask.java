import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

public class BackgroundTask extends AsyncTask<String, Void, String> {

    Context context;

    BackgroundTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        Database db = new Database(context);
        if(method.equals("add_info")){
            String username = params[1];
            String password = params[2];
            SQLiteDatabase dbsql = db.getWritableDatabase();
            db.addInformation(dbsql, username, password);
            return context.getResources().getString(R.string.createdMessage);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
