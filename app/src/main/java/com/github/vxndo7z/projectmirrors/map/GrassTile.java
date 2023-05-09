package com.github.vxndo7z.projectmirrors.map;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.graphics.*;

class GrassTile
extends Tile {

	private final Sprite sprite;

	public GrassTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
		super(mapLocationRect);
		sprite = spriteSheet.getGrassSprite();
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
	}
}