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
		String averageFPS = Integer.toString((int)gameLoop.getAverageFPS());
		String averageUPS = Integer.toString((int)gameLoop.getAverageUPS());
		canvas.drawText("UPS: " + averageUPS, 20, 60, paint);
		canvas.drawText("FPS: " + averageFPS, 20, 100, paint);
	}
	
	public void drawText(String text, Canvas canvas, int y) {
		canvas.drawText(text, 20, y, paint);
	}
}
