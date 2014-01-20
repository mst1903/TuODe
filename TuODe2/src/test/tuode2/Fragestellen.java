package test.tuode2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragestellen);
		
		
		 Button absenden = (Button)findViewById(R.id.absenden);
	        absenden.setOnClickListener(new OnClickListener (){
	        	
	        	@Override
	        	public void onClick (View v){
	        		
	        		EditText nameField = (EditText)findViewById(R.id.editTitel);	        		 
	                String title = nameField.getText().toString();	         
	          /*      if (Title.length() == 0) {
	                    new AlertDialog.Builder(this).setMessage(
	                            R.string.error_name_missing).setNeutralButton(
	                            R.string.error_ok,
	                            null).show();
	                    return;
	                }  */
	                
	        		nameField = (EditText)findViewById(R.id.editBeschreibung);	        		 
	                String beschreibung = nameField.getText().toString();
	                
	                Spinner spinner = (Spinner) findViewById(R.id.sp_kategorie);
	                
	                String kategorie = String.valueOf(spinner.getSelectedItem()); 
	        		
	                if(title.equals("") | beschreibung.equals("")){
	                	Toast.makeText(getApplicationContext(),
	                			"Bitte geben Sie einen Titel und eine Beschreibung ein.",
	                			Toast.LENGTH_SHORT).show();
	                }
	                else{
	                	if(kategorie.equals("(Kategorie auswählen)")){
	                		Toast.makeText(getApplicationContext(),
		                			"Bitte wählen Sie eine Kategorie aus.",
		                			Toast.LENGTH_SHORT).show();
	                	}
	                	else{
	                		frageSpeichern(kategorie,title,beschreibung);
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

	public void auslesen() {
		
		
	/*	Intent i = getIntent();  */
	//	Intent frage =  new Intent();   */
    /*	String eingabe = i.getExtras().getString("eingabeTitel");
    //	((TextView)findViewById(R.id.ausgabeText)).setText(eingabe);
   // 	saveInListe(eingabe);
    
    	//Intent frage =  new Intent();
   /*   Bundle rucksack = new Bundle();
    	rucksack.putString("wert1", eingabe);
    	i.putExtras(rucksack);
    	
    	setResult(RESULT_OK, i);
    	finish(); */
		
		/* TEST 1
		String result="Test";
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra("result",result);
		setResult(RESULT_OK,returnIntent);     
		finish();  */
		String text="Test99";
		
		Intent person = new Intent();
		Bundle rucksack = new Bundle();
		rucksack.putString("wert1", text);
		person.putExtras(rucksack);
		setResult(RESULT_OK, person);
		finish();
		
	}
	
	public void frageSpeichern(String kategorie, String titel, String beschreibung){
		
		FragenDatenbank db = new FragenDatenbank(this);
		SQLiteDatabase con = db.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		
		cv.put(FragenDatenbank.titel, titel);
		cv.put(FragenDatenbank.kategorie, kategorie);
		cv.put(FragenDatenbank.beschreibung, beschreibung);
		con.insert(FragenDatenbank.fragenTabelle, null, cv);
		con.close();
		db.close();
	}
	
	
	
}
