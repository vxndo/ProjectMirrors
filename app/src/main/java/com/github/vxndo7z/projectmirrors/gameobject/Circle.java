package com.github.vxndo7z.projectmirrors.gameobject;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.*;
import com.github.vxndo7z.projectmirrors.graphics.*;
import com.github.vxndo7z.projectmirrors.engine.*;

public abstract class Circle
extends MirrorsObject {

	protected double radius;
	protected Paint paint;

	public Circle(int color, double positionX, double positionY, double radius) {
		super(positionX, positionY);
		this.radius = radius;
		paint = new StylePaint(true, true);
		paint.setColor(color);
	}

	public static boolean isColliding(Circle obj1, Circle obj2) {
		double distance = obj1.getDistanceBetweenObjects(obj2);
		double distanceToCollision = obj1.getRadius() + obj2.getRadius();
		if (distance < distanceToCollision)
			return true;
		else return false;
	}

	public void draw(Canvas canvas, MirrorsDisplay gameDisplay) {
		canvas.drawCircle(
			(float) gameDisplay.gameToDisplayCoordinatesX(positionX),
			(float) gameDisplay.gameToDisplayCoordinatesY(positionY),
			(float) radius,
			paint
		);
	}

	public double getRadius() {
		return radius;
	}
}
