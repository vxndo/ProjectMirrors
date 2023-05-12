package com.github.vxndo7z.projectmirrors.gamepanel;

import android.graphics.*;
import android.view.*;
import com.github.vxndo7z.projectmirrors.engine.MirrorsDisplay;

public abstract class GamePanel {

	private MirrorsDisplay display;

	public GamePanel(MirrorsDisplay display) {
		this.display = display;
	}

	public int displayWidth() {
		return display.DISPLAY_RECT.width();
	}

	public int displayHeight() {
		return display.DISPLAY_RECT.height();
	}

	public abstract void draw(Canvas canvas);
}
