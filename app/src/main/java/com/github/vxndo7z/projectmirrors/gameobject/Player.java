package com.github.vxndo7z.projectmirrors.gameobject;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.*;
import com.github.vxndo7z.projectmirrors.gamepanel.*;
import com.github.vxndo7z.projectmirrors.graphics.*;

public class Player
extends Circle {

	public static final double SPEED_PIXELS_PER_SECOND = 400.0;
	private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Game.MAX_UPS;
	public static final int MAX_HEALTH_POINTS = 5;
	private Joystick joystick;
	private HealthBar healthBar;
	private int healthPoints = MAX_HEALTH_POINTS;
	private Animator animator;
	private PlayerState playerState;

	public Player(Joystick joystick, double positionX, double positionY, double radius, Animator animator) {
		super(0xffFF0000, positionX, positionY, radius);
		this.joystick = joystick;
		this.healthBar = new HealthBar(this);
		this.animator = animator;
		this.playerState = new PlayerState(this);
	}

	public void update() {
		velocityX = joystick.getActuatorX() * MAX_SPEED;
		velocityY = joystick.getActuatorY() * MAX_SPEED;
		positionX += velocityX;
		positionY += velocityY;
		if (velocityX != 0 || velocityY != 0) {
			double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
			directionX = velocityX / distance;
			directionY = velocityY / distance;
		} playerState.update();
	}

	public void draw(Canvas canvas, GameDisplay gameDisplay) {
		animator.draw(canvas, gameDisplay, this);
		healthBar.draw(canvas, gameDisplay);
	}

	public int getHealthPoint() {
		return healthPoints;
	}

	public void setHealthPoint(int healthPoints) {
		if (healthPoints >= 0) {
			this.healthPoints = healthPoints;
		}
	}

	public PlayerState getPlayerState() {
		return playerState;
	}
}