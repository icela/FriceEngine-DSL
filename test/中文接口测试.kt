import org.frice.dsl.game

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

		长方形 {
			name("rectangle")
			x = 100.0
			y = 100.0
			width = 100.0
		}
		/* TODO: 编译报错: Val cannot be reassigned
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
				accelerate(-2, 10)
			}
		}*/
		/* TODO
		image {
			file("C:/frice.png")
			x = 200.0
			y = 300.0
			velocity {
				x = -5.5
//				y = -5.5
			}
		}*/
	}
}