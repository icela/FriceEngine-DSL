package org.frice.dsl

import com.lfk.justweengine.GameTimer

/**
 * Created by ice1000 on 2016/9/29.
 *
 * @author ice1000
 */
class FriceGameTimer : GameTimer() {
	fun reset() = resetStop()
	val begin get() = e_start
}