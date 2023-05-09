package com.github.vxndo7z.projectmirrors.engine;

import android.graphics.*;

public abstract class Object {

	protected double positionY, positionX;
	protected double velocityY, velocityX;
	protected double directionY, directionX = 1;

	public Object(double positionY, double positionX) {
		this.positionY = positionY;
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public double getDirectionY() {
		return directionY;
	}

	public double getDirectionX() {
		return directionX;
	}

	public double getDistanceFrom(Object obj) {
		return Math.sqrt(
			Math.pow(obj.getPositionX() - getPositionX(), 2) +
			Math.pow(obj.getPositionY() - getPositionY(), 2)
		);
	}

	public abstract void update();
	public abstract void draw(Canvas canvas);
}
