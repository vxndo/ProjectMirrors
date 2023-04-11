package atomicspider.vxndo7z.projectmirrors.game;

import android.graphics.*;

public class Player {

	private static final double SPEED_PIXELS_PER_SECOND = 400;
	private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / MainThread.MAX_UPS;
	private double positionX;
	private double positionY;
	private double radius;
	private Paint paint;
	private double velocityX;
	private double velocityY;

	public Player(double positionX, double positionY, double radius) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.radius = radius;
		paint = new Paint();
		paint.setColor(0xffff0000);
		paint.setAntiAlias(true);
	}

	public void setPosition(double positionX, double positionY) {
		this.positionY = positionX;
		this.positionX = positionY;
	}

	public void draw(Canvas canvas) {
		canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
	}

	public void update(Joystick joystick) {
		velocityX = joystick.getActuatorX() * MAX_SPEED;
		velocityY = joystick.getActuatorY() * MAX_SPEED;
		positionX += velocityX;
		positionY += velocityY;
	}
}
