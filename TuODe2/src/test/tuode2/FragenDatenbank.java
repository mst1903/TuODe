package test.tuode2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FragenDatenbank extends SQLiteOpenHelper{
	
	//Tabellennamen und Attribute in Strings speichern, um sie in anderen Klassen einfach aufrufen zu können
	public static final String fragenTabelle = "frage";
	public static final String antwortenTabelle = "antwort";
	public static final String id = "_id";
	public static final String beschreibung = "Beschreibung";
	public static final String titel = "Titel";
	public static final String kategorie = "Kategorie";
	public static final String frageId = "Frage_id";
	public static final String datum = "Datum";
	
	public FragenDatenbank(Context context) {
		super(context, "fragen.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		//DB-Tabellen fuer Frage und Antwort
		db.execSQL("CREATE TABLE frage ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Titel TEXT NOT NULL," 
				+ "Kategorie TEXT NOT NULL," 
				+ "Beschreibung TEXT NOT NULL,"
				+ "Datum Date"
				+ ");");
		db.execSQL("CREATE TABLE antwort ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Frage_id integer NOT NULL," 
				+ "Beschreibung TEXT NOT NULL,"
				+ "Datum Date"
				+ ");");
		
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE Frage;");
		db.execSQL("DROP TABLE Antwort;");
		onCreate(db);
	}
}
