// This java code draws and handles the menu of the game. =======================================================

package com.cavemanmancave.caveman;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity implements AnimationListener {
	// Creating button for menu of the game ------------------------------------------------------------------------
	private Button GameButton;//Creates a new GameButton
	private Button OptionsButton;//Creates a new OptionsButton
	private Button HighscoresButton;//Creates a new HighscoresButton
	private Button HelpButton;//Creates a new HelpButton
	private Button CreditsButton;//Creates a new CreditsButton

	// Creating Media Player to play any sound or music -------------------------------------------------------------
	private MediaPlayer mp;//Creates a new MediaPlayer to play any kind of sound
	private void clickSound() {// this function Plays a sound when a button is clicked.
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
		mp.start();
	}

	// Boolean Array of size 5 is created to check if button is selected or not
	private final boolean[] selected = new boolean[5];

	// CreateMenuButtons() function, creates an short animation when a particular menu button is selected ------------------
	private void createMenuButtons() {
		final Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.clicked);

		// for GameButton ----------------------
		GameButton = (Button) findViewById(R.id.Game);
		GameButton.startAnimation(AnimationUtils.loadAnimation(com.cavemanmancave.caveman.MainActivity.this, R.anim.slide_down));
		GameButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				clickSound();
				selected[0] = true;
				GameButton.startAnimation(animation1);
				animation1.setAnimationListener(com.cavemanmancave.caveman.MainActivity.this);
			}
		});

		// for OptionsButton ----------------------
		OptionsButton = (Button) findViewById(R.id.Options);
		OptionsButton.startAnimation(AnimationUtils.loadAnimation(
				com.cavemanmancave.caveman.MainActivity.this, R.anim.slide_down));
		OptionsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				clickSound();
				selected[1] = true;
				OptionsButton.startAnimation(animation1);
				animation1.setAnimationListener(com.cavemanmancave.caveman.MainActivity.this);
			}
		});

		// for HighscoresButton ----------------------
		HighscoresButton = (Button) findViewById(R.id.Highscores);
		HighscoresButton.startAnimation(AnimationUtils.loadAnimation(
				com.cavemanmancave.caveman.MainActivity.this, R.anim.slide_down));
		HighscoresButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				clickSound();
				selected[2] = true;
				HighscoresButton.startAnimation(animation1);
				animation1.setAnimationListener(com.cavemanmancave.caveman.MainActivity.this);
			}
		});

		// for HelpButton ----------------------
		HelpButton = (Button) findViewById(R.id.Help);
		HelpButton.startAnimation(AnimationUtils.loadAnimation(
				com.cavemanmancave.caveman.MainActivity.this, R.anim.slide_down));
		HelpButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				clickSound();
				selected[3] = true;
				HelpButton.startAnimation(animation1);
				animation1.setAnimationListener(com.cavemanmancave.caveman.MainActivity.this);
			}
		});

		// for CreditsButton ----------------------
		CreditsButton = (Button) findViewById(R.id.Credits);
		CreditsButton.startAnimation(AnimationUtils.loadAnimation(
				com.cavemanmancave.caveman.MainActivity.this, R.anim.slide_down));
		CreditsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				clickSound();
				selected[4] = true;
				CreditsButton.startAnimation(animation1);
				animation1.setAnimationListener(com.cavemanmancave.caveman.MainActivity.this);
			}
		});
	}

	//this functions are used for animation part only ---------------------------------------------------------
	public void onAnimationEnd(Animation animation) {
		switchView();
		finish();
	}
	public void onAnimationRepeat(Animation animation) {

	}
	public void onAnimationStart(Animation animation) {

	}

	// This function is called when the activity is first created. ----------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		for (int i = 0; i < 5; i++)
			selected[i] = false;// we here initialize all the boolean array element as false, indicating initially not selected
		createMenuButtons();// then createMenuButtons() function is called
	}

	// This function handles the case when the user hits the back button, the application ends. ---------------------
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// This function detects the button that was clicked and redirects the user to the corresponding Activity.
	private void switchView() {
		for (int i = 0; i < 5; i++) {
			if (selected[i] == true) {// we change the selected[i] from false to true
				switch (i) {
				case 0:// case 0 - for GameButton
					Intent Game = new Intent(com.cavemanmancave.caveman.MainActivity.this,
							com.cavemanmancave.caveman.LevelSelect.class);
					startActivity(Game);
					break;
				case 1:// Case 1 - for OptionsButton
					Intent Options = new Intent(com.cavemanmancave.caveman.MainActivity.this,
							com.cavemanmancave.caveman.OptionsActivity.class);
					startActivity(Options);
					break;
				case 2:// Case 2 - for HighscoresButton
					Intent Highscores = new Intent(com.cavemanmancave.caveman.MainActivity.this,
							com.cavemanmancave.caveman.HighscoresActivity.class);
					startActivity(Highscores);
					break;
				case 3:// Case 3 - for HelpsButton
					Intent Help = new Intent(com.cavemanmancave.caveman.MainActivity.this,
							com.cavemanmancave.caveman.HelpActivity.class);
					startActivity(Help);
					break;
				case 4:// case 4 - for Creditsbutton
					Intent Credits = new Intent(com.cavemanmancave.caveman.MainActivity.this,
							com.cavemanmancave.caveman.CreditsActivity.class);
					startActivity(Credits);
					break;
				}
			}
			selected[i] = false;
		}
	}
}