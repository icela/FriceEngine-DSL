
import org.frice.dsl.game

/**
 * Created by ice1000 on 2016/9/28.
 *
 * @author ice1000
 */
fun main(args: Array<String>) {
	game {
		bounds(500, 500, 800, 750)
		showFPS = false

		whenExit {
			closeWindow()
		}

		whenUpdate {

		}

		rectangle {
			name("rectangle")
			x = 100.0
			y = 100.0
			width = 100.0
		}
		oval {
			x = 0.0
			y = 85.0
			accelerate {
				x = 10.0
//				y = 10.0
			}
			whenColliding("rectangle") {
				stop()
				x -= 5
				accelerate {
					x = -2.0
					y = 10.0
				}
			}
		}
		image {
			file("C:/frice.png")
			x = 200.0
			y = 300.0
			velocity {
				x = -5.5
//				y = -5.5
			}
		}
	}
}