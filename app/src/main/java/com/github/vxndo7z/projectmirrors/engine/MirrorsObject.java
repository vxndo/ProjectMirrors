package com.github.vxndo7z.projectmirrors.engine;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.engine.*;

public abstract class MirrorsObject {

	protected double positionX, positionY;
	protected double velocityX, velocityY;
	protected double directionX = 1;
	protected double directionY;

	public MirrorsObject() {}

	public MirrorsObject(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getDirectionX() {
		return directionX;
	}

	public double getDirectionY() {
		return directionY;
	}

	public abstract void draw(Canvas canvas, MirrorsDisplay gameDisplay);
	public abstract void update();

	public double getDistanceBetweenObjects(MirrorsObject obj2) {
		return Math.sqrt(
			Math.pow(obj2.getPositionX() - getPositionX(), 2) +
			Math.pow(obj2.getPositionY() - getPositionY(), 2)
		);
	}
}
