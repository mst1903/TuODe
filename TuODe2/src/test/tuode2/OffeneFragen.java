package test.tuode2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class OffeneFragen extends Activity {

	Cursor cursor;
	String kategorie;
	List<String> fragen = new ArrayList<String>();
	List<Integer> fragenId = new ArrayList<Integer>();
	ListView lv;
	ArrayAdapter<String> ad;
	Spinner filter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offene_fragen);

		FragenDatenbank db = new FragenDatenbank(this);
		SQLiteDatabase con = db.getWritableDatabase();
		//ID, Titel und Kategorie der Fragen aus DB abfragen
		String columns[] = { FragenDatenbank.id, FragenDatenbank.titel,
				FragenDatenbank.kategorie };
		cursor = con.query(FragenDatenbank.fragenTabelle, columns, null, null,
				null, null, null);
		
		//Spinner zum Filtern der Fragen nach Kategorien
		filter = (Spinner) findViewById(R.id.sp_filter);
		filter.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				kategorie = String.valueOf(filter.getSelectedItem());
				//Fragen und IDs aus den Listen löschen, da sie anhand der Kategorie im Filter neu befüllt werden
				fragen.clear();
				fragenId.clear();
				//For-Schleife die für jede Frage im Cursor durchlaufen wird
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					//Filter = Alle Kategorien -> jede Frage und id wird in die Liste geschrieben
					if (kategorie.equals("Alle Kategorien")) {
						fragen.add(cursor.getString(cursor
								.getColumnIndex(FragenDatenbank.titel)));
						fragenId.add(cursor.getInt(cursor
								.getColumnIndex(FragenDatenbank.id)));
					} else {
						//Nur Fragen der ausgewählten Kategorie in die Liste schreiben
						if (kategorie.equals(cursor.getString(cursor
								.getColumnIndex(FragenDatenbank.kategorie)))) {
							fragen.add(cursor.getString(cursor
									.getColumnIndex(FragenDatenbank.titel)));
							fragenId.add(cursor.getInt(cursor
									.getColumnIndex(FragenDatenbank.id)));
						}
					}
				}
				//Listview aktualisieren
				ad.notifyDataSetChanged();
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		
		// listview über Adapter mit Arraylist verbinden 
		lv = (ListView) findViewById(R.id.list);
		ad = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fragen);
		lv.setAdapter(ad);
		lv.setClickable(true);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				//ID der angeklickten Frage aus frageid abfragen und übergeben
				frageAnzeigen(fragenId.get(position));
			}
		});
	}

	public void frageAnzeigen(int id) {
		Intent i = new Intent(this, FrageAnzeigen.class);
		//übergebene id in Intent packen
		i.putExtra(FragenDatenbank.id, id);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.offene_fragen, menu);
		return true;
	}

}