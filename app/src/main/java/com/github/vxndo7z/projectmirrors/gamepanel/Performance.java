package com.github.vxndo7z.projectmirrors.gamepanel;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.*;
import com.github.vxndo7z.projectmirrors.graphics.*;
import com.github.vxndo7z.projectmirrors.engine.*;

public class Performance {

	private Game gameLoop;
	private StylePaint paint;

	public Performance(Game gameLoop) {
		this.gameLoop = gameLoop;
		paint = new StylePaint(true, true);
		paint.setColor(0xffffffff);
		paint.setTypeface(Typeface.MONOSPACE);
		paint.setTextSize(40);
	}

	public void draw(Canvas canvas) {
		drawUPS(canvas);
		drawFPS(canvas);
	}

	public void drawUPS(Canvas canvas) {
		String averageUPS = Integer.toString((int)gameLoop.getAverageUPS());
		canvas.drawText("UPS: " + averageUPS, 20, 60, paint);
	}

	public void drawFPS(Canvas canvas) {
		String averageFPS = Integer.toString((int)gameLoop.getAverageFPS());
		canvas.drawText("FPS: " + averageFPS, 20, 100, paint);
	}
}
