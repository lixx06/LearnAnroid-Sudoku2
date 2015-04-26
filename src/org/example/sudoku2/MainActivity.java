package org.example.sudoku2;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // Set up click listeners for all the buttons
        View aboutButton = this.findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        
        View continueButton = this.findViewById(R.id.continue_button);
        continueButton.setOnClickListener( this);
        View newButton = this.findViewById(R.id.new_game_button);
        newButton.setOnClickListener( this);
        View exitButton = this.findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.settings:
    		startActivity(new Intent(this, Settings.class));
    		return true;
    		// More items go here (if any) ...
    	}
    	return false;
    }

	public void onClick(View v) {		
		switch (v.getId()) {
		case R.id.about_button:
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
		case R.id.new_game_button:
			openNewGameDialog();
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}
	
	private static final String TAG = "Sudoku" ;
	
	/** Ask the user what difficulty level they want */
	private void openNewGameDialog() {
		new AlertDialog.Builder(this)
		.setTitle(R.string.new_game_title)
		.setItems(R.array.difficulty,
		new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialoginterface, int i) {
				startGame(i);
			}
		})
		.show();
	}

	/** Start a new game with the given difficulty level */
	private void startGame(int i) {
		Log.d(TAG, "clicked on " + i);
		Intent intent = new Intent(MainActivity.this, Game.class);
		intent.putExtra(Game.KEY_DIFFICULTY, i);
		startActivity(intent);
		// Start game here...
	}	
	
}




