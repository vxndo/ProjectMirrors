package com.github.vxndo7z.projectmirrors.gameobject;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.*;

public abstract class GameObject {

	protected double positionX, positionY;
	protected double velocityX, velocityY;
	protected double directionX = 1;
	protected double directionY;

	public GameObject() {}

	public GameObject(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
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

	public abstract void draw(Canvas canvas, GameDisplay gameDisplay);
	public abstract void update();

	public double getDistanceBetweenObjects(GameObject obj2) {
		return Math.sqrt(
			Math.pow(obj2.getPositionX() - getPositionX(), 2) +
			Math.pow(obj2.getPositionY() - getPositionY(), 2)
		);
	}
}