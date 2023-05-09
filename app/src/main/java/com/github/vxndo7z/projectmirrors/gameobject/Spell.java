package com.github.vxndo7z.projectmirrors.gameobject;

import com.github.vxndo7z.projectmirrors.*;

public class Spell
extends Circle {

	public static final double SPEED_PIXELS_PER_SECOND = 800.0;
	private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Game.MAX_UPS;

	public Spell(Player player) {
		super(0xffFF9400, player.getPositionX(), player.getPositionY(), 25);
		velocityX = player.getDirectionX() * MAX_SPEED;
		velocityY = player.getDirectionY() * MAX_SPEED;
	}

	@Override
	public void update() {
		positionX = positionX + velocityX;
		positionY = positionY + velocityY;
	}
}