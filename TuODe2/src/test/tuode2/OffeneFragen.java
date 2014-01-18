package test.tuode2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class OffeneFragen extends Activity {
	
	private Cursor cursor;
	
//befüllt die Listview (dafür muss die id der listview aber wieder in id="@ListView1" geändert werden) ist wohl nicht geeignet um daten aus einer datenbank einzutragen?! */
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offene_fragen);
		
	//String[] listItems = {"Element 1", "Element 2", "Element 3", "Element 4", "Element 5", "Element 6", "Element 7", "Element 8"};
	
	FragenDatenbank db = new FragenDatenbank(this);
	SQLiteDatabase con = db.getWritableDatabase();
	
	String columns[] = {FragenDatenbank.id,FragenDatenbank.titel};
	cursor = con.query(FragenDatenbank.fragenTabelle, columns, null, null, null, null, null);
	String fragen[];
	fragen = new String[cursor.getCount()];
	int i = 0;
	
	while(cursor.moveToNext()){
		fragen[i] = cursor.getString(cursor.getColumnIndex(FragenDatenbank.titel));
		i++;
	}
	
	ListView lv = (ListView) findViewById(R.id.list);
	lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fragen));
	
	lv.setClickable(true);
	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			cursor.moveToPosition(position);
			int id = cursor.getInt(cursor.getColumnIndex(FragenDatenbank.id));
			frageAnzeigen(id);
			
			
		}

		
	});
	
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
	

	public void frageAnzeigen(int id) {
		Intent i = new Intent(this, FrageAnzeigen.class);
		i.putExtra(FragenDatenbank.id, id);
		startActivityForResult(i,1);
		
	
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.offene_fragen, menu);
		return true;
	}

	
	
}
