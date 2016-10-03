package demo

import org.frice.dsl.game

/**
 * Created by ice1000 on 2016/10/3.
 *
 * @author ice1000
 */

fun main(args: Array<String>) {
	game {
		autoGC = true

		bounds {
			x = 100
			y = 100
			width = 400
			height = 500
		}

		traits("block") {
			width = 100.0
			height = 120.0
			color = BLACK
		}

		every(1500) {
			button {
				include("block")
			}
		}
	}
}