package atomicspider.vxndo7z.projectmirrors.game;

import android.content.*;
import android.graphics.*;
import android.view.*;
import atomicspider.vxndo7z.projectmirrors.game.control.*;
import atomicspider.vxndo7z.projectmirrors.game.engine.*;
import atomicspider.vxndo7z.projectmirrors.game.object.*;

public class GameView
extends GameEngine {

	private final Player player;
	private final GameControl control;
	private final Enemy enemy;

	public GameView(Context context) {
		super(context);
		control = new Joystick(150, displayHeight - 150, 80, 40);
		player = new Player(control, displayWidth/2, displayHeight/2, 30);
		enemy = new Enemy(player, 0, 0, 30);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case event.ACTION_DOWN:
				if (control.isPressed((double) event.getX(), (double) event.getY())) {
					control.setIsPressed(true);
				} return true;
			case event.ACTION_MOVE:
				if (control.getIsPressed()) {
					control.setActuator((double) event.getX(), (double) event.getY());
				} return true;
			case event.ACTION_UP:
				control.setIsPressed(false);
				control.resetActuator();
				return true;
		} return super.onTouchEvent(event);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		player.draw(canvas);
		enemy.draw(canvas);
		control.draw(canvas);
		drawUPS(canvas);
	}

	@Override
	public void update() {
		control.update();
		player.update();
		enemy.update();
	}
}
