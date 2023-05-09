package com.github.vxndo7z.projectmirrors.map;

import android.graphics.*;
import com.github.vxndo7z.projectmirrors.graphics.*;

class TreeTile
extends Tile {

	private final Sprite grassSprite;
	private final Sprite treeSprite;

	public TreeTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
		super(mapLocationRect);
		grassSprite = spriteSheet.getGrassSprite();
		treeSprite = spriteSheet.getTreeSprite();
	}

	@Override
	public void draw(Canvas canvas) {
		grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
		treeSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
	}
}