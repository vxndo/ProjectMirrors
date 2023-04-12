package atomicspider.vxndo7z.projectmirrors.game.control;

import android.graphics.*;

public class Joystick
extends GameControl {

	private int outerCircleCenterPositionX;
	private int outerCircleCenterPositionY;
	private int innerCircleCenterPositionX;
	private int innerCircleCenterPositionY;
	private int outerCircleRadius;
	private int innerCircleRadius;
	private Paint innerCirclePaint;
	private Paint outerCirclePaint;
	private double joystickCenterToTouchDistance;

	public Joystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius) {
		outerCircleCenterPositionX = centerPositionX;
		outerCircleCenterPositionY = centerPositionY;
		innerCircleCenterPositionX = centerPositionX;
		innerCircleCenterPositionY = centerPositionY;
		this.outerCircleRadius = outerCircleRadius;
		this.innerCircleRadius = innerCircleRadius;
		outerCirclePaint = new Paint();
		outerCirclePaint.setColor(Color.GRAY);
		outerCirclePaint.setAntiAlias(true);
		outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		innerCirclePaint = new Paint(outerCirclePaint);
		innerCirclePaint.setColor(Color.BLUE);
	}

	@Override
	public boolean isPressed(double touchPositionX, double touchPositionY) {
		joystickCenterToTouchDistance = Math.sqrt(
			Math.pow(outerCircleCenterPositionX - touchPositionX, 2) +
			Math.pow(outerCircleCenterPositionY - touchPositionY, 2)
		);
		return joystickCenterToTouchDistance < outerCircleRadius;
	}

	@Override
	public void setActuator(double touchPositionX, double touchPositionY) {
		double deltaX = touchPositionX - outerCircleCenterPositionX;
		double deltaY = touchPositionY - outerCircleCenterPositionY;
		double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		if (deltaDistance < outerCircleRadius) {
			actuatorX = deltaX / outerCircleRadius;
			actuatorY = deltaY / outerCircleRadius;
		} else {
			actuatorX = deltaX / deltaDistance;
			actuatorY = deltaY / deltaDistance;
		}
	}

	@Override
	public void update() {
		innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX * outerCircleRadius);
		innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY * outerCircleRadius);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(outerCircleCenterPositionX, outerCircleCenterPositionY, outerCircleRadius, outerCirclePaint);
		canvas.drawCircle(innerCircleCenterPositionX, innerCircleCenterPositionY, innerCircleRadius, innerCirclePaint);
	}
}
