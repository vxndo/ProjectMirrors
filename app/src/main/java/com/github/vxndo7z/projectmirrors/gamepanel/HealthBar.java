package com.github.vxndo7z.projectmirrors.gamepanel;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.*;
import com.github.vxndo7z.projectmirrors.gameobject.*;
import com.github.vxndo7z.projectmirrors.graphics.*;

public class HealthBar {

	private Player player;
	private Paint borderPaint, healthPaint;
	private int width, height, margin; // pixel value

	public HealthBar(Player player) {
		this.player = player;
		this.width = 100;
		this.height = 20;
		this.margin = 2;
		this.borderPaint = new StylePaint(true, true);
		int borderColor = 0xffA0A0A0;
		borderPaint.setColor(borderColor);
		this.healthPaint = new StylePaint(true, true);
		int healthColor = 0xff00FF00;
		healthPaint.setColor(healthColor);
	}

	public void draw(Canvas canvas, GameDisplay gameDisplay) {
		float x = (float) player.getPositionX();
		float y = (float) player.getPositionY();
		float distanceToPlayer = 30;
		float healthPointPercentage = (float) player.getHealthPoint() / player.MAX_HEALTH_POINTS;
		float borderLeft, borderTop, borderRight, borderBottom;
		borderLeft = x - width / 2;
		borderRight = x + width / 2;
		borderBottom = y - distanceToPlayer;
		borderTop = borderBottom - height;
		canvas.drawRect(
			(float) gameDisplay.gameToDisplayCoordinatesX(borderLeft),
			(float) gameDisplay.gameToDisplayCoordinatesY(borderTop),
			(float) gameDisplay.gameToDisplayCoordinatesX(borderRight),
			(float) gameDisplay.gameToDisplayCoordinatesY(borderBottom),
			borderPaint);
		float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;
		healthWidth = width - 2 * margin;
		healthHeight = height - 2 * margin;
		healthLeft = borderLeft + margin;
		healthRight = healthLeft + healthWidth * healthPointPercentage;
		healthBottom = borderBottom - margin;
		healthTop = healthBottom - healthHeight;
		canvas.drawRect(
			(float) gameDisplay.gameToDisplayCoordinatesX(healthLeft),
			(float) gameDisplay.gameToDisplayCoordinatesY(healthTop),
			(float) gameDisplay.gameToDisplayCoordinatesX(healthRight),
			(float) gameDisplay.gameToDisplayCoordinatesY(healthBottom),
			healthPaint);
	}
}