package test.tuode2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FragenDatenbank extends SQLiteOpenHelper{
	
	public FragenDatenbank(Context context) {
		super(context, "fragen.db", null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		//DB-Tabellen fuer Frage, Kategorie und Antwort
		db.execSQL("CREATE TABLE frage ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Titel TEXT NOT NULL," 
				+ "Kategorie_id integer NOT NULL," 
				+ "Beschreibung TEXT NOT NULL);");
		db.execSQL("CREATE TABLE kategorie ("
				+ "_id INTEGER PRIMARY KEY,"
				+ "Titel TEXT NOT NULL" 
				+ ");");
		db.execSQL("CREATE TABLE antwort ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Frage_id integer NOT NULL," 
				+ "Beschreibung TEXT NOT NULL"
				+ ");");
		
		//Kategorien
		db.execSQL("INSERT INTO kategorie (_id,Titel) VALUES "
				+ "(1,'keine');");
		db.execSQL("INSERT INTO kategorie (_id,Titel) VALUES "
				+ "(2,'Wirtschaft');");
		db.execSQL("INSERT INTO kategorie (_id,Titel) VALUES "
				+ "(3,'Informatik');");
		db.execSQL("INSERT INTO kategorie (_id,Titel) VALUES "
				+ "(4,'Gesundheit');");
		db.execSQL("INSERT INTO kategorie (_id,Titel) VALUES "
				+ "(5,'Mathematik');");
		db.execSQL("INSERT INTO kategorie (_id,Titel) VALUES "
				+ "(6,'Gestaltung');");
		db.execSQL("INSERT INTO kategorie (_id,Titel) VALUES "
				+ "(7,'Sprachen');");
		
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE Frage;");
		db.execSQL("DROP TABLE Kategorie;");
		db.execSQL("DROP TABLE Antwort;");
		onCreate(db);
	}
}
