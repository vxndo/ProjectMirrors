package com.github.vxndo7z.projectmirrors.graphics;

import android.content.*;
import android.graphics.*;
import com.github.vxndo7z.projectmirrors.*;

public class SpriteSheet {

	private static final int SPRITE_WIDTH_PIXELS = 64;
	private static final int SPRITE_HEIGHT_PIXELS = 64;
	private Bitmap bitmap;

	public SpriteSheet(Context context) {
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inScaled = false;
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
	}

	public Sprite[] getPlayerSpriteArray() {
		Sprite[] spriteArray = new Sprite[3];
		spriteArray[0] = new Sprite(this, new Rect(0 * 64, 0, 1 * 64, 64));
		spriteArray[1] = new Sprite(this, new Rect(1 * 64, 0, 2 * 64, 64));
		spriteArray[2] = new Sprite(this, new Rect(2 * 64, 0, 3 * 64, 64));
		return spriteArray;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public Bitmap getInverseBitmap(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.setScale(-1, 1);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	public Sprite getWaterSprite() {
		return getSpriteByIndex(1, 0);
	}

	public Sprite getLavaSprite() {
		return getSpriteByIndex(1, 1);
	}

	public Sprite getGroundSprite() {
		return getSpriteByIndex(1, 2);
	}

	public Sprite getGrassSprite() {
		return getSpriteByIndex(1, 3);
	}

	public Sprite getTreeSprite() {
		return getSpriteByIndex(1, 4);
	}

	private Sprite getSpriteByIndex(int idxRow, int idxCol) {
		Rect rect = new Rect(
			idxCol * SPRITE_WIDTH_PIXELS,
			idxRow * SPRITE_HEIGHT_PIXELS,
			(idxCol + 1) * SPRITE_WIDTH_PIXELS,
			(idxRow + 1) * SPRITE_HEIGHT_PIXELS
		);
		return new Sprite(this, rect);
	}
}