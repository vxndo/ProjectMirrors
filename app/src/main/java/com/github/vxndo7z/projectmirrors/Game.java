package com.github.vxndo7z.projectmirrors;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import com.github.vxndo7z.projectmirrors.engine.*;
import com.github.vxndo7z.projectmirrors.gameobject.*;
import com.github.vxndo7z.projectmirrors.gamepanel.*;
import com.github.vxndo7z.projectmirrors.graphics.*;
import com.github.vxndo7z.projectmirrors.map.*;
import java.util.*;

import com.github.vxndo7z.projectmirrors.engine.MirrorsDisplay;

public class Game
extends MirrorsEngine {

	private final Tilemap tilemap;
	private int joystickPointerId = 0;
	private final Joystick joystick;
	private final Player player;
	private List<Enemy> enemyList = new ArrayList<Enemy>();
	private List<Spell> spellList = new ArrayList<Spell>();
	private int numberOfSpellsToCast = 0;
	private GameOver gameOver;
	private Performance performance;
	private MirrorsDisplay gameDisplay;
	private String controllerPos = "";
	
	public Game(Context context) {
		super(context);
		SpriteSheet spriteSheet = new SpriteSheet(context);
		Animator animator = new Animator(spriteSheet.getPlayerSpriteArray());
		DisplayMetrics metrics = getRealMetrics();
		joystick = new Joystick(150, metrics.heightPixels-150, 70, 40);
		player = new Player(joystick, 2 * 500, 500, 32, animator);
		gameDisplay = new MirrorsDisplay(metrics, player);
		performance = new Performance(this);
		gameOver = new GameOver(gameDisplay);
		tilemap = new Tilemap(this, spriteSheet);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				if (joystick.getIsPressed()) {
					numberOfSpellsToCast++;
				} else if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
					joystickPointerId = event.getPointerId(event.getActionIndex());
					joystick.setIsPressed(true);
				} else {
					numberOfSpellsToCast++;
				} return true;
			case MotionEvent.ACTION_MOVE:
				if (joystick.getIsPressed()) {
					joystick.setActuator((double) event.getX(), (double) event.getY());
				} return true;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
					joystick.setIsPressed(false);
					joystick.resetActuator();
				} return true;
		} return super.onTouchEvent(event);
	}

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		if ((event.getSource() & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
					joystick.setActuatorX(event.getAxisValue(event.AXIS_X));
					joystick.setActuatorY(event.getAxisValue(event.AXIS_Y));
					return true;
			}
		}
		return super.onGenericMotionEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((event.getSource() & InputDevice.SOURCE_GAMEPAD) == InputDevice.SOURCE_GAMEPAD) {
			switch (event.getKeyCode()) {
				case event.KEYCODE_BUTTON_R2:
					numberOfSpellsToCast++;
					return true;
			}
		} return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawColor(0xff000000);
		tilemap.draw(canvas, gameDisplay);
		player.draw(canvas, gameDisplay);
		for (Enemy enemy : enemyList) {
			enemy.draw(canvas, gameDisplay);
		} for (Spell spell : spellList) {
			spell.draw(canvas, gameDisplay);
		} joystick.draw(canvas);
		performance.draw(canvas);
		performance.drawText(controllerPos, canvas, 140);
		if (player.getHealthPoint() <= 0) {
			gameOver.draw(canvas);
		}
	}

	public void update() {
		if (player.getHealthPoint() <= 0) {
			return;
		} joystick.update();
		player.update();
		if (Enemy.readyToSpawn()) {
			enemyList.add(new Enemy(player));
		} for (Enemy enemy : enemyList) {
			enemy.update();
		} while (numberOfSpellsToCast > 0) {
			spellList.add(new Spell(player));
			numberOfSpellsToCast --;
		} for (Spell spell : spellList) {
			spell.update();
		} Iterator<Enemy> iteratorEnemy = enemyList.iterator();
		while (iteratorEnemy.hasNext()) {
			Circle enemy = iteratorEnemy.next();
			if (Circle.isColliding(enemy, player)) {
				iteratorEnemy.remove();
				player.setHealthPoint(player.getHealthPoint() - 1);
				continue;
			} Iterator<Spell> iteratorSpell = spellList.iterator();
			while (iteratorSpell.hasNext()) {
				Circle spell = iteratorSpell.next();
				if (Circle.isColliding(spell, enemy)) {
					iteratorSpell.remove();
					iteratorEnemy.remove();
					break;
				}
			}
		} gameDisplay.update();
	}
}
