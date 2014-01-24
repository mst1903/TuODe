package test.tuode2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
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
	private List<String> antworten = new ArrayList<String>();
	private ListView lv;
	private ArrayAdapter<String> ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frage_anzeigen);
		// ID der anzuzeigenden Frage aus dem Intent abfragen
		id = getIntent().getExtras().getInt(FragenDatenbank.id);

		// Frage aus DB abfragen
		FragenDatenbank db = new FragenDatenbank(this);
		SQLiteDatabase con = db.getWritableDatabase();
		// Spalten die abgefragt werden
		String columns[] = { FragenDatenbank.id, FragenDatenbank.titel,
				FragenDatenbank.beschreibung, FragenDatenbank.kategorie };
		// query mit where-klausel id = id aus dem intent
		Cursor cursor = con.query(FragenDatenbank.fragenTabelle, columns,
				FragenDatenbank.id.concat("=" + String.valueOf(id)), null,
				null, null, null);
		cursor.moveToNext();
		// Frage in Strings speichern
		titel = cursor.getString(cursor.getColumnIndex(FragenDatenbank.titel));
		beschreibung = cursor.getString(cursor.getColumnIndex(FragenDatenbank.beschreibung));
		kategorie = cursor.getString(cursor.getColumnIndex(FragenDatenbank.kategorie));

		// Titel der Activity, Fragentitel und Beschreibung setzen
		this.setTitle("Kategorie: " + kategorie);
		((TextView) findViewById(R.id.fragentitel)).setText(titel);
		((TextView) findViewById(R.id.beschreibung)).setText(beschreibung);

		// Antworten abfragen
		String columnsAntworten[] = { FragenDatenbank.id,
				FragenDatenbank.frageId, FragenDatenbank.beschreibung };
		// Nur Antworten, bei denen frageid = id der anzuzeigenden Frage
		cursor = con.query(FragenDatenbank.antwortenTabelle, columnsAntworten,
				FragenDatenbank.frageId.concat("=" + String.valueOf(id)), null,
				null, null, null);
		// wenn vorhanden, Antworten in die Arraylist antworten schreiben
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				antworten.add(cursor.getString(cursor.getColumnIndex(FragenDatenbank.beschreibung)));
			}
		}

		// listview über Adapter mit Arraylist verbinden
		lv = (ListView) findViewById(R.id.antworten);
		ad = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, antworten);
		lv.setAdapter(ad);
		lv.setClickable(false);

		con.close();
		db.close();

		Button btAntworten = (Button) findViewById(R.id.bt_antworten);
		btAntworten.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Antworttext in String schreiben
				EditText antwortText = (EditText) findViewById(R.id.antworttext);
				String antwort = antwortText.getText().toString();
				// Tastatur ausblenden
				hideSoftKeyBoard();

				// Toast ausgeben, falls keine Antwort eingegeben wurde
				if (antwort.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Bitte geben Sie eine Antwort ein.",
							Toast.LENGTH_SHORT).show();
				} else {
					// Antwort in DB speichern
					antwortSpeichern(antwort);
					Toast.makeText(getApplicationContext(),
							"Ihre Antwort wurde gespeichert.",
							Toast.LENGTH_SHORT).show();
					// Textfeld leeren
					antwortText.setText("");
					// Antwort in Antwortenliste eintragen
					antworten.add(antwort);
					// ListView aktualisieren
					ad.notifyDataSetChanged();
					// zur letzten Antwort scrollen
					lv.requestFocus();
					lv.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
					lv.setSelection(lv.getCount() - 1);
				}

			}

		});
	}

	public void antwortSpeichern(String antwort) {
		FragenDatenbank db = new FragenDatenbank(this);
		SQLiteDatabase con = db.getWritableDatabase();
		ContentValues cv = new ContentValues();
		// id der Frage und und text der antwort in ConentValues Objekt packen
		cv.put(FragenDatenbank.frageId, id);
		cv.put(FragenDatenbank.beschreibung, antwort);
		// Antwort in Antworten-Tabelle einfügen
		con.insert(FragenDatenbank.antwortenTabelle, null, cv);
		con.close();
		db.close();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.frage_anzeigen, menu);
		return true;
	}

	private void hideSoftKeyBoard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		if (imm.isAcceptingText())// verify if the soft keyboard is open
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	}
}
