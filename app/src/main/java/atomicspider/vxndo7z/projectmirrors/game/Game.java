package atomicspider.vxndo7z.projectmirrors.game;

import android.content.*;
import android.view.*;
import android.graphics.*;
import android.text.*;

public class Game
extends Engine {

	private final Player player;
	private final Joystick joystick;

	public Game(Context context) {
		super(context);
		Rect display = getDisplayRect();
		player = new Player(display.width()/2, display.height()/2, 30);
		joystick = new Joystick(150, display.height() - 150, 80, 40);
		//getMainThread().setMaxFPS(60);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case event.ACTION_DOWN:
				if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
					joystick.setIsPressed(true);
				} return true;
			case event.ACTION_MOVE:
				if (joystick.getIsPressed()) {
					joystick.setActuator((double) event.getX(), (double) event.getY());
				} return true;
			case event.ACTION_UP:
				joystick.setIsPressed(false);
				joystick.resetActuator();
				return true;
		} return super.onTouchEvent(event);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		joystick.draw(canvas);
		player.draw(canvas);
	}

	@Override
	public void update() {
		joystick.update();
		player.update(joystick);
	}
}
