package atomicspider.vxndo7z.projectmirrors.game.object;

import android.graphics.*;
import atomicspider.vxndo7z.projectmirrors.game.engine.*;

public class Enemy
extends GameObject {

	private static final double MAX_SPEED = 240 / GameThread.MAX_UPS;
	private Paint paint;
	private int radius;
	private Player player;

	public Enemy(Player player, double positionX, double positionY, int radius) {
		super(positionX, positionY);
		this.player = player;
		this.radius = radius;
		paint = new Paint();
		paint.setColor(0xff00ffff);
		paint.setAntiAlias(true);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
	}

	@Override
	public void update() {
		double distanceToPlayerX = player.getPositionX() - positionX;
		double distanceToPlayerY = player.getPositionY() - positionY;
		double distanceToPlayer = getDistanceBetweenObjects(this, player);
		double directionX = distanceToPlayerX / distanceToPlayer;
		double directionY = distanceToPlayerY / distanceToPlayer;
		if (distanceToPlayer > 0) {
			velocityX = directionX * MAX_SPEED;
			velocityY = directionY * MAX_SPEED;
		} else {
			velocityX = 0;
			velocityY = 0;
		} positionX += velocityX;
		positionY += velocityY;
	}
}
