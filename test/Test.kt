import org.frice.dsl.game
import org.frice.obj.SideEffect

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
			if (800.elapsed()) {
				rectangle {
					x = elapsed / 10.0
					y = elapsed / 10.0
					color = PINK
				}
			}
		}

		every(1000) {
			oval {
				x = elapsed / 10.0
				y = elapsed / 10.0
				color = ORANGE
			}
			log("1 second has past.")
		}

		rectangle {
			name("233")
			x = 300.0
			y = 100.0
			width = 100.0
		}
		oval {
			x = 0.0
			y = 85.0
			accelerate {
				x = 20.0
//				y = 10.0
			}
			whenColliding("233") {
				stop()
				x -= 2
				velocity {
					x = -50.0
					y = 0.0
				}
				accelerate(-0, 50)
				runLater(1000L, SideEffect {
					dialogShow("距离碰撞发生，已经过去一秒。")
				})
			}
		}
		image {
			url("https://avatars3.githubusercontent.com/u/16398479")
			x = 200.0
			y = 300.0
			velocity {
				x = -5.5
//				y = -5.5
			}
		}
	}
}