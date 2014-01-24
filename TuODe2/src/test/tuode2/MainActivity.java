package test.tuode2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button fragestellen = (Button)findViewById(R.id.stellen);
        fragestellen.setOnClickListener(new OnClickListener (){
        	
        	public void onClick (View v){
        		aufruf_stellen();
        	}
    	});
        
        Button offeneFragen = (Button)findViewById(R.id.offen);
        offeneFragen.setOnClickListener(new OnClickListener (){
        	
        	public void onClick (View v){
        		aufruf_offen();
        	}
        });
        
        Button suchen = (Button)findViewById(R.id.suchen);
        suchen.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//to do
			}
        	
        });
        
    }    
   

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void aufruf_stellen() {
		Intent i = new Intent(this, Fragestellen.class);
		startActivity(i);
	}
	
	public void aufruf_offen() {
		Intent i = new Intent(this, OffeneFragen.class);
		startActivity(i);
	}
}
