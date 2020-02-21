package peru.volcanes.volcanesper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexionsql extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.

   // private static final String SQL_CREATE_ENTRIES =
           // "CREATE TABLE notificaciones (id INTEGER, texto  TEXT)";



   // public static final int DATABASE_VERSION = 1;
   // public static final String DATABASE_NAME = "FeedReader.db";

    public Conexionsql(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_NOTIFICACIONES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS notificaciones");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
