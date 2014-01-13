package test.tuode2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class OffeneFragen extends Activity {
	
/*befüllt die Listview (dafür muss die id der listview aber wieder in id="@ListView1" geändert werden) ist wohl nicht geeignet um daten aus einer datenbank einzutragen?! */
/*@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offene_fragen);
		
	String[] listItems = {"Element 1", "Element 2", "Element 3", "Element 4", "Element 5", "Element 6", "Element 7", "Element 8"};
	
	ListView lv = (ListView) findViewById(R.id.listView1);
	
	lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems));
	
}*/
	
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
