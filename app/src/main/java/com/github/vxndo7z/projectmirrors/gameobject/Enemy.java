package com.github.vxndo7z.projectmirrors.gameobject;

import android.content.*;
import com.github.vxndo7z.projectmirrors.*;

public class Enemy
extends Circle {

	private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.6;
	private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Game.MAX_UPS;
	private static final double SPAWNS_PER_MINUTE = 20;
	private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
	private static final double UPDATES_PER_SPAWN = Game.MAX_UPS / SPAWNS_PER_SECOND;
	private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
	private Player player;

	public Enemy(Player player, double positionX, double positionY, double radius) {
		super(0xff00FFFF, positionX, positionY, radius);
		this.player = player;
	}

	public Enemy(Player player) {
		super(
			0xff00FFFF,
			Math.random() * 1000,
			Math.random() * 1000,
			30
		);
		this.player = player;
	}

	public static boolean readyToSpawn() {
		if (updatesUntilNextSpawn <= 0) {
			updatesUntilNextSpawn += UPDATES_PER_SPAWN;
			return true;
		} else {
			updatesUntilNextSpawn --;
			return false;
		}
	}

	public void update() {
		double distanceToPlayerX = player.getPositionX() - positionX;
		double distanceToPlayerY = player.getPositionY() - positionY;
		double distanceToPlayer = getDistanceBetweenObjects(player);
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