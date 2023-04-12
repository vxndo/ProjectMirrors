package atomicspider.vxndo7z.projectmirrors.game.object;

import android.graphics.*;

public abstract class GameObject {

	protected double positionX;
	protected double positionY;
	protected double velocityX;
	protected double velocityY;

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

	public double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
		return Math.sqrt(
			Math.pow(obj2.getPositionX() - obj1.getPositionX(), 2) +
			Math.pow(obj2.getPositionY() - obj1.getPositionY(), 2)
		);
	}

	public abstract void draw(Canvas canvas);
	public abstract void update();
}
