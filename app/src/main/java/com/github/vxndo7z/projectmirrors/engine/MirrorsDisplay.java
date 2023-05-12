package com.github.vxndo7z.projectmirrors.engine;

import android.graphics.*;
import android.util.*;
import com.github.vxndo7z.projectmirrors.gameobject.*;

public class MirrorsDisplay {

	public final Rect DISPLAY_RECT;
	private final int widthPixels;
	private final int heightPixels;
	private final MirrorsObject centerObject;
	private final double displayCenterX;
	private final double displayCenterY;
	private double gameToDisplayCoordinatesOffsetX;
	private double gameToDisplayCoordinatesOffsetY;
	private double gameCenterX;
	private double gameCenterY;

	public MirrorsDisplay(DisplayMetrics metrics, MirrorsObject centerObject) {
		this.widthPixels = metrics.widthPixels;
		this.heightPixels = metrics.heightPixels;
		DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);
		this.centerObject = centerObject;
		displayCenterX = widthPixels / 2.0;
		displayCenterY = heightPixels / 2.0;
		update();
	}

	public void update() {
		gameCenterX = centerObject.getPositionX();
		gameCenterY = centerObject.getPositionY();
		gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
		gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
	}

	public double gameToDisplayCoordinatesX(double x) {
		return x + gameToDisplayCoordinatesOffsetX;
	}

	public double gameToDisplayCoordinatesY(double y) {
		return y + gameToDisplayCoordinatesOffsetY;
	}

	public Rect getGameRect() {
		return new Rect(
			(int) (gameCenterX - widthPixels / 2),
			(int) (gameCenterY - heightPixels / 2),
			(int) (gameCenterX + widthPixels / 2),
			(int) (gameCenterY + heightPixels / 2)
		);
	}
}
