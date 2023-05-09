package com.github.vxndo7z.projectmirrors.map;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.graphics.*;

class WaterTile
extends Tile {

	private final Sprite sprite;

	public WaterTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
		super(mapLocationRect);
		sprite = spriteSheet.getWaterSprite();
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
	}
}