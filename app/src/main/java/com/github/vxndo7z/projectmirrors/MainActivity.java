package com.github.vxndo7z.projectmirrors;

import vxndo.app.*;
import android.os.*;

public class MainActivity
extends Activity {

	private Game game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSystemUiFlags(4098);
		game = new Game(this);
		setContentView(game);
	}

	@Override
	protected void onPause() {
		game.pause();
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}