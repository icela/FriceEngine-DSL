package com.lfk.justweengine;

/**
 * 代码写的太糟糕啦。不过我反正会给你封装的好好的，才不会让别人看见我家丰恺写的东西呢。 ——ice1000
 * @author liufengkai
 *         Created by liufengkai on 15/11/27.
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

	// 这个必须删掉哦。冰封的游戏引擎里面不能出现这种东西呢。
	// public void rest(int ms)

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