package com.github.vxndo7z.projectmirrors.graphics;

import android.graphics.*;

public class StylePaint
extends Paint {

	public StylePaint(boolean aa, boolean dither) {
		setAntiAlias(aa);
		setDither(dither);
	}
}