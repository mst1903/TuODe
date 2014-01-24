package test.tuode2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Fragestellen extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragestellen);

		Button absenden = (Button) findViewById(R.id.absenden);
		absenden.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//Titel, Beschreibung und Kategorie in Strings schreiben
				EditText fragentitel = (EditText) findViewById(R.id.editTitel);
				EditText fragenbeschreibung = (EditText) findViewById(R.id.editBeschreibung);
				Spinner spinner = (Spinner) findViewById(R.id.sp_kategorie);
				String titel = fragentitel.getText().toString();
				String beschreibung = fragenbeschreibung.getText().toString();
				String kategorie = String.valueOf(spinner.getSelectedItem());

				//Toast ausgeben, falls keine Beschreibung und/oder Titel eingegeben wurde
				if (titel.equals("") | beschreibung.equals("")) {
					Toast.makeText(
							getApplicationContext(),
							"Bitte geben Sie einen Titel und eine Beschreibung ein.",
							Toast.LENGTH_SHORT).show();
				} else {
					//Toast ausgeben, falls keine Kategorie ausgewählt wurde
					if (kategorie.equals("(Kategorie auswählen)")) {
						Toast.makeText(getApplicationContext(),
								"Bitte wählen Sie eine Kategorie aus.",
								Toast.LENGTH_SHORT).show();
					} else {
						//Frage in DB speichern
						frageSpeichern(kategorie, titel, beschreibung);
						Toast.makeText(getApplicationContext(),
								"Ihre Frage wurde gespeichert.",
								Toast.LENGTH_SHORT).show();
						//Activity beenden, zur MainActivity zurückkehren
						finish();
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragestellen, menu);
		return true;
	}

	public void frageSpeichern(String kategorie, String titel,
			String beschreibung) {

		FragenDatenbank db = new FragenDatenbank(this);
		SQLiteDatabase con = db.getWritableDatabase();
		ContentValues cv = new ContentValues();

		//Titel, Kategorie und Beschreibung in ConentValues Objekt packen
		cv.put(FragenDatenbank.titel, titel);
		cv.put(FragenDatenbank.kategorie, kategorie);
		cv.put(FragenDatenbank.beschreibung, beschreibung);
		//Frage in Fragen-Tabelle einfügen
		con.insert(FragenDatenbank.fragenTabelle, null, cv);
		con.close();
		db.close();
	}
}
