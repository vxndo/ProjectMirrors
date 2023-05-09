package com.github.vxndo7z.projectmirrors.engine;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.content.res.*;

public abstract class MirrorsEngine
extends SurfaceView
implements SurfaceHolder.Callback {

	public static final double MAX_UPS = 60.0;
	private static final double UPS_PERIOD = 1E+3 / MAX_UPS;
	private boolean mIsRunning;
	private double mAverageUPS;
	private double mAverageFPS;
	private MainThread mThread;
	private Display mDisplay;
	private SurfaceHolder mHolder;

	public MirrorsEngine(Context context) {
		super(context);
		mHolder = getHolder();
		mHolder.addCallback(this);
		mThread = new MainThread();
		mDisplay = ((Activity) getContext()).getDisplay();
		setFocusable(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (mThread.getState().equals(Thread.State.TERMINATED)) {
			mHolder = getHolder();
			mHolder.addCallback(this);
			mThread = new MainThread();
		} mThread.startLoop();
	}

	public void pause() {
		mThread.stopLoop();
	}

	public DisplayMetrics getMetrics() {
		DisplayMetrics metrics = new DisplayMetrics();
		mDisplay.getMetrics(metrics);
		return metrics;
	}

	public DisplayMetrics getRealMetrics() {
		DisplayMetrics metrics = new DisplayMetrics();
		mDisplay.getRealMetrics(metrics);
		return metrics;
	}

	public double getAverageUPS() {
		return mAverageUPS;
	}

	public double getAverageFPS() {
		return mAverageFPS;
	}

	public AssetManager getAssets() {
		return getContext().getAssets();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {}
	public abstract void update();

	private class MainThread
	extends Thread {

		public void startLoop() {
			mIsRunning = true;
			start();
		}

		public void stopLoop() {
			mIsRunning = false;
			try {
				join();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		@Override
		public void run() {
			super.run();
			int updateCount = 0;
			int frameCount = 0;
			long startTime;
			long elapsedTime;
			long sleepTime;
			Canvas canvas = null;
			startTime = System.currentTimeMillis();
			while (mIsRunning) {
				try {
					canvas = mHolder.lockCanvas();
					synchronized (mHolder) {
						update();
						updateCount++;
						draw(canvas);
					}
				} catch (Exception e) {
					//e.printStackTrace();
				} finally {
					if (canvas != null) {
						try {
							mHolder.unlockCanvasAndPost(canvas);
							frameCount++;
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				} elapsedTime = System.currentTimeMillis() - startTime;
				sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
				if (sleepTime > 0) {
					try {
						sleep(sleepTime);
					} catch (Exception e) {
						//e.printStackTrace();
					}
				} while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
					update();
					updateCount++;
					elapsedTime = System.currentTimeMillis() - startTime;
					sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
				} elapsedTime = System.currentTimeMillis() - startTime;
				if (elapsedTime >= 1000) {
					mAverageUPS = updateCount / (1E-3 * elapsedTime);
					mAverageFPS = frameCount / (1E-3 * elapsedTime);
					updateCount = 0;
					frameCount = 0;
					startTime = System.currentTimeMillis();
				}
			}
		}
	}
}
