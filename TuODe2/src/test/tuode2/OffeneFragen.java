package test.tuode2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class OffeneFragen extends Activity {
	
//befüllt die Listview (dafür muss die id der listview aber wieder in id="@ListView1" geändert werden) ist wohl nicht geeignet um daten aus einer datenbank einzutragen?! */
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offene_fragen);
		
	//String[] listItems = {"Element 1", "Element 2", "Element 3", "Element 4", "Element 5", "Element 6", "Element 7", "Element 8"};
	
	FragenDatenbank db = new FragenDatenbank(this);
	SQLiteDatabase con = db.getWritableDatabase();
	
	String columns[] = {FragenDatenbank.titel, FragenDatenbank.id};
	Cursor cursor = con.query(FragenDatenbank.fragenTabelle, columns, null, null, null, null, null);
	String fragen[];
	fragen = new String[cursor.getCount()];
	int id[];
	id = new int[cursor.getCount()];
	int i = 0;
	
	while(cursor.moveToNext()){
		fragen[i] = cursor.getString(cursor.getColumnIndex(FragenDatenbank.titel));
		id[i] = cursor.getInt(cursor.getColumnIndex(FragenDatenbank.id));
		i++;
	}
	
	ListView lv = (ListView) findViewById(R.id.list);
	lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fragen));
	
}
	
/*	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offene_fragen);
		
		ArrayList<String> valueList = new ArrayList<String>();
		for (int i = 0; i < 10; i++)
		{
		valueList.add("value"+i);
		}
		ListAdapter adapter = new ArrayAdapter<Frage>(getApplicationContext(), android.R.layout.simple_list_item_1);
		
	    final ListView lv = (ListView)findViewById(R.id.listView1);

	    lv.setAdapter(adapter);
	}*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.offene_fragen, menu);
		return true;
	}

	
	
}
