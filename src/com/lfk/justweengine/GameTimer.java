package com.lfk.justweengine;

/**
 * 代码写的太糟糕啦。不过我反正会给你封装的好好的，才不会让别人看见我家丰恺写的东西呢。 ——ice1000
 * Created by liufengkai on 15/11/27.
 *
 * @author liufengkai
 */
public class GameTimer {
	protected double e_start;
	private double e_stopWatchStart;

	public GameTimer() {
		e_start = System.currentTimeMillis();
		e_stopWatchStart = 0;
	}

	public double getElapsed() {
		return System.currentTimeMillis() - e_start;
	}

	public void resetStop() {
		e_stopWatchStart = getElapsed();
	}

	public boolean stopWatch(long ms) {
		if (getElapsed() > e_stopWatchStart + ms) {
			resetStop();
			return true;
		} else return false;
	}
}