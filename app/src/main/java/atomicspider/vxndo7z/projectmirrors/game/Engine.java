package atomicspider.vxndo7z.projectmirrors.game;

import android.content.*;
import android.view.*;
import android.graphics.*;
import android.text.*;
import android.util.*;
import android.app.*;

public abstract class Engine
extends SurfaceView
implements SurfaceHolder.Callback {

	private SurfaceHolder holder;
	private MainThread mainThread;
	private Rect displayRect;

	public Engine(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);
		mainThread = new MainThread(this, holder);
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		displayRect = new Rect(0, 0, dm.widthPixels, dm.heightPixels);
		setFocusable(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder p1) {
		mainThread.startLoop();
	}

	@Override
	public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder p1) {

	}

	public MainThread getMainThread() {
		return mainThread;
	}

	public Rect getDisplayRect() {
		return displayRect;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		drawInfo(canvas);
	}

	private void drawInfo(Canvas canvas) {
		String averageFPS = Integer.toString(mainThread.getAverageFPS().intValue());
		String averageUPS = Integer.toString(mainThread.getAverageUPS().intValue());
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setTextSize(40);
		paint.setTypeface(Typeface.MONOSPACE);
		canvas.drawText("UPS: " + averageUPS, 20, 60, paint);
		canvas.drawText("FPS: " + averageFPS, 20, 100, paint);
	}

	public abstract void update();
}
