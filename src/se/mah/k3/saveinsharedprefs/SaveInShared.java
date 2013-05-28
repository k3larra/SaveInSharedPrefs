package se.mah.k3.saveinsharedprefs;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SaveInShared extends Activity {

	private ArrayList<Person> highScore;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_in_shared);
		getSharedPreferences();
		for (Person p : highScore) {
			Log.i("k3larra","Name: "+ p.getFirstName() + " points " + p.getPoints());
		}
	}

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_in_shared, menu);
		return true;
	}
	
	public void clickOn(View v){
				EditText pointsEdit = (EditText) findViewById(R.id.editPoints);
				EditText nameEdit = (EditText) findViewById(R.id.editName);
				EditText savePosEdit = (EditText) findViewById(R.id.saveInPos);
				String pointsAsString = pointsEdit.getText().toString();
				String savePosAsString = savePosEdit.getText().toString();
				String name = nameEdit.getText().toString();
				int points=0;
				int pos = 0;
				try {
					points = Integer.parseInt(pointsAsString);
					pos = Integer.parseInt(savePosAsString);
					Log.i("k3larra","Score:"+ points);
				} catch (Exception  e) {
					Log.i("k3larra",e.getMessage());
				}
				Person p = new Person(name,points);
				highScore.add(pos,p); //Lägg till på plats 2
	}

	@Override
	protected void onDestroy() {
		saveInPrefs();
		super.onDestroy();
	}
	
	

	public void quitClick(View v){
		finish();
	}
	
	private void getSharedPreferences() {
		SharedPreferences settings = getSharedPreferences("spelare", MODE_PRIVATE);
		highScore = new  ArrayList<Person>();
		for(int i = 0; i<10;i++){
			String name = settings.getString("spelare"+i,"NoName");
			int score = settings.getInt("spelareScore"+i,0);
			highScore.add(new Person(name,score));
		}
//		for (Person p : highScore) {
//			Log.i("k3larra"," Got Name: "+ p.getFirstName() + " points " + p.getPoints());
//		}
	}
	
	private void saveInPrefs() {
		// TODO Auto-generated method stub
		SharedPreferences settings = getSharedPreferences("spelare", MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		for(int i = 0; i<10;i++){
			Person p = highScore.get(i);
			editor.putString("spelare"+i,p.getFirstName());
			editor.putInt("spelareScore"+i, p.getPoints());
		}
		editor.commit();
//		for (Person p : highScore) {
//			Log.i("k3larra","Saved Name: "+ p.getFirstName() + " points " + p.getPoints());
//		}
		
	}
}
