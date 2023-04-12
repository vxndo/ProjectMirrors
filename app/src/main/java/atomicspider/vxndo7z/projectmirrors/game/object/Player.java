package atomicspider.vxndo7z.projectmirrors.game.object;

import android.graphics.*;
import atomicspider.vxndo7z.projectmirrors.game.control.*;
import atomicspider.vxndo7z.projectmirrors.game.engine.*;

public class Player
extends GameObject {

	private static final double MAX_SPEED = 400 / GameThread.MAX_UPS;
	private Paint paint;
	private double radius;
	private GameControl control;

	public Player(GameControl control, double positionX, double positionY, double radius) {
		super(positionX, positionY);
		this.control = control;
		this.radius = radius;
		paint = new Paint();
		paint.setColor(0xffff0000);
		paint.setAntiAlias(true);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
	}

	public void update() {
		velocityX = control.getActuatorX() * MAX_SPEED;
		velocityY = control.getActuatorY() * MAX_SPEED;
		if (velocityX < GameEngine.displayWidth) {
			positionX += velocityX;
		} if (velocityY < GameEngine.displayHeight) {
			positionY += velocityY;
		}
	}
}
