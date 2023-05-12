package com.github.vxndo7z.projectmirrors.gamepanel;

import android.graphics.*;
import android.util.*;
import com.github.vxndo7z.projectmirrors.engine.*;
import com.github.vxndo7z.projectmirrors.graphics.*;

public class GameOver
extends GamePanel {

	private int x;
	private int y;
	private String text;
	private StylePaint paint;

	public GameOver(MirrorsDisplay display) {
		super(display);
		text = "Game Over";
		x = (displayWidth()/2)-150;
		y = (displayHeight()/4);
		paint = new StylePaint(true, true);
		int color = 0xffFC5245;
		paint.setColor(color);
		float textSize = 150;
		paint.setTextSize(textSize);
	}

	public void draw(Canvas canvas) {
		canvas.drawColor(0xff212121);
		canvas.drawText(text, x, y, paint);
	}
}
