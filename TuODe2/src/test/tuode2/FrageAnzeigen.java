package test.tuode2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class FrageAnzeigen extends Activity {
	
	private int id;
	private String titel;
	private String beschreibung;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frage_anzeigen);
		id = getIntent().getExtras().getInt(FragenDatenbank.id);
		
		FragenDatenbank db = new FragenDatenbank(this);
		SQLiteDatabase con = db.getWritableDatabase();
		
		String columns[] = {FragenDatenbank.id,FragenDatenbank.titel,FragenDatenbank.beschreibung,FragenDatenbank.kategorie};
		Cursor cursor = con.query(FragenDatenbank.fragenTabelle, columns, FragenDatenbank.id.concat("="+String.valueOf(id)), null, null, null, null);
		cursor.moveToNext();
		titel = cursor.getString(cursor.getColumnIndex(FragenDatenbank.titel));
		beschreibung = cursor.getString(cursor.getColumnIndex(FragenDatenbank.beschreibung));
		
		this.setTitle("Kategorie: " + cursor.getString(cursor.getColumnIndex(FragenDatenbank.kategorie)));	
		((TextView)findViewById(R.id.fragentitel)).setText(titel);
		((TextView)findViewById(R.id.beschreibung)).setText(beschreibung);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.frage_anzeigen, menu);
		return true;
	}

}
