package test.tuode2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FrageAnzeigen extends Activity {
	
	private int id;
	private String titel;
	private String beschreibung;
	private String kategorie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frage_anzeigen);
		id = getIntent().getExtras().getInt(FragenDatenbank.id);
		
		
		//Frage aus DB abfragen
		FragenDatenbank db = new FragenDatenbank(this);
		SQLiteDatabase con = db.getWritableDatabase();
		String columns[] = {FragenDatenbank.id,FragenDatenbank.titel,FragenDatenbank.beschreibung,FragenDatenbank.kategorie};
		Cursor cursor = con.query(FragenDatenbank.fragenTabelle, columns, FragenDatenbank.id.concat("="+String.valueOf(id)), null, null, null, null);
		cursor.moveToNext();
		titel = cursor.getString(cursor.getColumnIndex(FragenDatenbank.titel));
		beschreibung = cursor.getString(cursor.getColumnIndex(FragenDatenbank.beschreibung));
		kategorie = cursor.getString(cursor.getColumnIndex(FragenDatenbank.kategorie));
		
		this.setTitle("Kategorie: " + kategorie);	
		((TextView)findViewById(R.id.fragentitel)).setText(titel);
		((TextView)findViewById(R.id.beschreibung)).setText(beschreibung);
		
		//Antworten abfragen
		String columnsAntworten[] = {FragenDatenbank.id,FragenDatenbank.frageId,FragenDatenbank.beschreibung};
		cursor = con.query(FragenDatenbank.antwortenTabelle, columnsAntworten, FragenDatenbank.frageId.concat("="+String.valueOf(id)), null, null, null, null);
		if(cursor.getCount()>0){
			String antworten[];
			antworten = new String[cursor.getCount()];
			int i = 0;
			
			while(cursor.moveToNext()){
				antworten[i] = cursor.getString(cursor.getColumnIndex(FragenDatenbank.beschreibung));
				i++;
			}
			
			ListView lv = (ListView) findViewById(R.id.antworten);
			lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, antworten));
			
			lv.setClickable(false);
		}
		con.close();
		db.close();
		
		Button btAntworten = (Button)findViewById(R.id.bt_antworten);
		btAntworten.setOnClickListener(new OnClickListener(){

			
			public void onClick(View v) {
				EditText antwortText = (EditText)findViewById(R.id.antworttext);
				String antwort = antwortText.getText().toString();
				
				//Antwort in DB speichern
				if(antwort.equals("")){
					Toast.makeText(getApplicationContext(),"Bitte geben Sie eine Antwort ein.", Toast.LENGTH_SHORT).show();
				}
				else{
					antwortSpeichern(antwort);
					//Frage erneut aufrufen
					anzeigeAktualisieren();
				}
				
				
				
			}
			
		});
	}

	public void antwortSpeichern(String antwort) {
		FragenDatenbank db = new FragenDatenbank(this);
		SQLiteDatabase con = db.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(FragenDatenbank.frageId, id);
		cv.put(FragenDatenbank.beschreibung, antwort);
		con.insert(FragenDatenbank.antwortenTabelle, null, cv);
		con.close();
		db.close();
		
	}

	public void anzeigeAktualisieren() {
		Intent i = new Intent(this, FrageAnzeigen.class);
		i.putExtra(FragenDatenbank.id, id);
		startActivity(i);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.frage_anzeigen, menu);
		return true;
	}
	
	public void onBackPressed(){
		startActivity(new Intent(this, OffeneFragen.class));
		finish();
	}

}
