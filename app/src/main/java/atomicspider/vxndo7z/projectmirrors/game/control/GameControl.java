package atomicspider.vxndo7z.projectmirrors.game.control;

import android.graphics.*;

public abstract class GameControl {

	protected boolean isPressed;
	protected double actuatorX;
	protected double actuatorY;

	public abstract boolean isPressed(double touchPositionX, double touchPositionY);

	public void setIsPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public boolean getIsPressed() {
		return isPressed;
	}

	public abstract void setActuator(double touchPositionX, double touchPositionY);

	public double getActuatorX() {
		return actuatorX;
	}

	public double getActuatorY() {
		return actuatorY;
	}

	public void resetActuator() {
		actuatorX = 0;
		actuatorY = 0;
	}

	public abstract void draw(Canvas canvas);

	public abstract void update();
}
