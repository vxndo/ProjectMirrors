package atomicspider.vxndo7z.projectmirrors.game.engine;

import android.graphics.*;
import android.view.*;

public class GameThread
extends Thread {

	public static final double MAX_UPS = 60;
	private static final double UPS_PERIOD = 1E+3 / MAX_UPS;
	private boolean isRunning;
	private OnUpdateListener onUpdateListener;
	private SurfaceHolder holder;
	private double averageUPS;
	private double averageFPS;

	public GameThread(SurfaceHolder holder) {
		this.holder = holder;
	}

	public void setOnUpdateListener(OnUpdateListener listener) {
		onUpdateListener = listener;
	}

	public Double getAverageFPS() {
		return averageFPS;
	}

	public Double getAverageUPS() {
		return averageUPS;
	}

	public void startLoop() {
		isRunning = true;
		start();
	}

	@Override
	public void run() {
		super.run();

		// Declare time and cycle count variables
		int updateCount = 0;
		int frameCount = 0;
		long elapsedTime;
		long sleepTime;

		// Game Loop
		Canvas canvas = null;
		long startTime = System.currentTimeMillis();
		while (isRunning) {

			// Try to update and render game
			try {
				canvas = holder.lockCanvas();
				synchronized (holder) {
					if (onUpdateListener != null) {
						onUpdateListener.update();
					} updateCount++;
					if (onUpdateListener != null) {
						onUpdateListener.draw(canvas);
					}
				}
			} catch (Exception e) {} finally {
				if (canvas != null) {
					try {
						holder.unlockCanvasAndPost(canvas);
						frameCount++;
					} catch (Exception e) {}
				}
			}

			// Pause Game Loop to not exceed target UPS
			elapsedTime = System.currentTimeMillis() - startTime;
			sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
			if (sleepTime > 0) {
				try {
					sleep(sleepTime);
				} catch (Exception e) {}
			}

			// Skip frames to keep up with UPS
			while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
				if (onUpdateListener != null) {
					onUpdateListener.update();
				} updateCount++;
				elapsedTime = System.currentTimeMillis() - startTime;
				sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
			}

			// Calculate average UPS and FPS
			elapsedTime = System.currentTimeMillis() - startTime;
			if (elapsedTime >= 1000) {
				averageUPS = updateCount / (1E-3 * elapsedTime);
				averageFPS = frameCount / (1E-3 * elapsedTime);
				frameCount = 0;
				updateCount = 0;
				startTime = System.currentTimeMillis();
			}
		}
	}

	public static interface OnUpdateListener {
		void draw(Canvas canvas);
		void update();
	}
}
