package atomicspider.vxndo7z.projectmirrors.game.control;

import android.graphics.*;

public class GamePad
extends GameControl {

	private int outerPosX;
	private int outerPosY;
	private int innerPosX;
	private int innerPosY;
	private int outerRadius;
	private int innerRadius;
	private Paint innerPaint;
	private Paint outerPaint;
	private double joystickCenterToTouchDistance;

	public GamePad(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius) {
		outerPosX = centerPositionX;
		outerPosY = centerPositionY;
		innerPosX = centerPositionX;
		innerPosY = centerPositionY;
		this.outerRadius = outerCircleRadius;
		this.innerRadius = innerCircleRadius;
		outerPaint = new Paint();
		outerPaint.setColor(Color.GRAY);
		outerPaint.setAntiAlias(true);
		outerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		innerPaint = new Paint(outerPaint);
		innerPaint.setColor(Color.BLUE);
	}

	@Override
	public boolean isPressed(double touchPositionX, double touchPositionY) {
		joystickCenterToTouchDistance = Math.sqrt(
			Math.pow(outerPosX - touchPositionX, 2) +
			Math.pow(outerPosY - touchPositionY, 2)
		);
		return joystickCenterToTouchDistance < outerRadius;
	}

	@Override
	public void setActuator(double touchPositionX, double touchPositionY) {
		double deltaX = touchPositionX - outerPosX;
		double deltaY = touchPositionY - outerPosY;
		double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		if (deltaDistance < outerRadius) {
			actuatorX = deltaX / outerRadius;
			actuatorY = deltaY / outerRadius;
		} else {
			actuatorX = deltaX / deltaDistance;
			actuatorY = deltaY / deltaDistance;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(outerPosX, outerPosY, outerRadius, outerPaint);
		//canvas.drawCircle(innerCircleCenterPositionX, innerCircleCenterPositionY, innerCircleRadius, innerCirclePaint);
		Path p = new Path();
		p.moveTo(outerPosX, outerPosY-80);
		p.lineTo(outerPosX, outerPosY+80);
		p.moveTo(outerPosX-80, outerPosY);
		p.lineTo(outerPosX+80, outerPosY);
		p.close();
		p.setFillType(Path.FillType.EVEN_ODD);
		Paint paint = new Paint(innerPaint);
		paint.setStrokeWidth(40);
		canvas.drawPath(p, paint);
	}

	@Override
	public void update() {
		innerPosX = (int) (outerPosX + actuatorX * outerRadius);
		innerPosY = (int) (outerPosY + actuatorY * outerRadius);
	}
}
