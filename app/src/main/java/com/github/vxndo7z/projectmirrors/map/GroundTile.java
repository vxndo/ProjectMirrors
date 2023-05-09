package com.github.vxndo7z.projectmirrors.map;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.graphics.*;

class GroundTile
extends Tile {

	private final Sprite sprite;

	public GroundTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
		super(mapLocationRect);
		sprite = spriteSheet.getGroundSprite();
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
	}
}