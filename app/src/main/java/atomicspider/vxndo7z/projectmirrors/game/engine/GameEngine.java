package atomicspider.vxndo7z.projectmirrors.game.engine;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public abstract class GameEngine
extends SurfaceView
implements SurfaceHolder.Callback,
GameThread.OnUpdateListener {

	public static int displayWidth;
	public static int displayHeight;
	private SurfaceHolder holder;
	private GameThread gameThread;

	public GameEngine(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);
		gameThread = new GameThread(holder);
		gameThread.setOnUpdateListener(this);
		DisplayMetrics dm = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getRealMetrics(dm);
		displayWidth = dm.widthPixels;
		displayHeight = dm.heightPixels;
		setFocusable(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder p1) {
		gameThread.startLoop();
	}

	@Override
	public void surfaceChanged(SurfaceHolder p1, int p2, int p3, int p4) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder p1) {

	}

	public GameThread getGameThread() {
		return gameThread;
	}

	protected void drawUPS(Canvas canvas) {
		String averageFPS = Integer.toString(gameThread.getAverageFPS().intValue());
		String averageUPS = Integer.toString(gameThread.getAverageUPS().intValue());
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setTextSize(40);
		paint.setTypeface(Typeface.MONOSPACE);
		canvas.drawText("UPS: " + averageUPS, 20, 60, paint);
		canvas.drawText("FPS: " + averageFPS, 20, 100, paint);
	}
}
