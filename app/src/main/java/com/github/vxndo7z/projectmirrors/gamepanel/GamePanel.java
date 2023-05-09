package com.github.vxndo7z.projectmirrors.gamepanel;

import android.graphics.*;
import android.view.*;
import com.github.vxndo7z.projectmirrors.*;

public abstract class GamePanel {

	private GameDisplay display;

	public GamePanel(GameDisplay display) {
		this.display = display;
	}

	public int displayWidth() {
		return display.DISPLAY_RECT.width();
	}

	public int displayHeight() {
		return display.DISPLAY_RECT.height();
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()) {
			case event.ACTION_DOWN:
				onClick();
				return true;
		} return false;
	}

	public abstract void onClick();
	public abstract void draw(Canvas canvas);
}