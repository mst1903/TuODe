package test.tuode2;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


/*sollte die Listview mit der besonderen ID="@android:id/list" befüllten (tut aber nicht!)*/
public class ListViewOffeneFragenActivity extends ListActivity {
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] listItems = {"Element 1", "Element 2", "Element 3", "Element 4", "Element 5", "Element 6", "Element 7", "Element 8"};
		
		setContentView(R.layout.activity_offene_fragen);
		
		this.setListAdapter (new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, listItems));
	}

}
