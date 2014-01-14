package test.tuode2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String TAG = "Activity";
	
	
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button fragestellen = (Button)findViewById(R.id.stellen);
        fragestellen.setOnClickListener(new OnClickListener (){
        	
        	public void onClick (View v){
        		Log.v(TAG, "TEST LOG");
        		aufruf_stellen();
        	}
        	 }
        	 );
        
        Button offeneFragen = (Button)findViewById(R.id.offen);
        offeneFragen.setOnClickListener(new OnClickListener (){
        	
        	public void onClick (View v){
        		Log.v(TAG, "TEST LOG");
        		aufruf_offen();
        	}
        	 }
        	 );
        /*
        //mit Suchen Button die Datenbank neu erstellen
        final FragenDatenbank db = new FragenDatenbank(this);
        final SQLiteDatabase con = db.getWritableDatabase();
        Button suchen = (Button)findViewById(R.id.suchen);
        suchen.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				db.onUpgrade(con, 1, 2);
				
				
			}
        	
        });
        */
    }    
   

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void aufruf_stellen() {
		Intent i = new Intent(this, Fragestellen.class);
		startActivityForResult(i,1);
	}
	
	public void aufruf_offen() {
		Intent i = new Intent(this, OffeneFragen.class);
		startActivityForResult(i,1);
	}

/*	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK) {
			Bundle korb = data.getExtras();
			String s = korb.getString("wert1");
			//String s = data.getDataString();
			((TextView)findViewById(R.id.ausgabe)).setText(s);
		}
	}  */
	
	/*  TEST1
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String result;
		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         result=data.getStringExtra("result");          
		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		  }
		}//onActivityResult	
	
}  */
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String s;
		  if (requestCode == 1) {

		     if(resultCode == RESULT_OK){      
		         Bundle korb = data.getExtras();
		         String Title = korb.getString("title");
		         String Beschreibung = korb.getString("beschreibung");
		         s=Title;
		         s=Beschreibung;

		     }
		     if (resultCode == RESULT_CANCELED) {    
		         //Write your code if there's no result
		     }
		  }
		}//onActivityResult	
	
}
