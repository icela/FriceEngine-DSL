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
		showFPS = false
		var gg = false

		whenExit { closeWindow() }

		bounds {
			x = 100
			y = 100
			width = 400
			height = 500
		}

		rectangle {
			name("wall")
			y = 500.0
			width = 400.0
			height = 10.0
		}

		traits("block") {
			y = -200.0
			width = 100.0
			height = 120.0
			color = BLACK
			velocity {
				y = 480.0
			}
			whenColliding("wall") {
//				messageBox("GG!")
				text {
					text = "GG!"
					x = 150.0
					y = 200.0
				}
				gg = true
			}
		}

		if (!gg) every(250) {
			requestGC()
			rectangle {
				x = (0 randomTo 3) * 100.0
				include("block")
				whenPressed {
					x = -1000.0
				}
			}
		}
	}
}