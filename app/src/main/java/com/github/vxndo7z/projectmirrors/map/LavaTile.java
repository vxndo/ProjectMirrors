package com.github.vxndo7z.projectmirrors.map;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.graphics.*;

class LavaTile
extends Tile {

	private final Sprite sprite;

	public LavaTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
		super(mapLocationRect);
		sprite = spriteSheet.getLavaSprite();
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
	}
}