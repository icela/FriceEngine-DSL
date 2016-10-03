package demo

import org.frice.dsl.game

/**
 * flappy bird
 * Created by ice1000 on 2016/9/29.
 *
 * @author ice1000
 */

fun main(args: Array<String>) = game {
	title = "flappy bird demo by ice1000"
	bounds {
		x = 100
		y = 100
		width = 400
		height = 500
	}

	whenExit { closeWindow() }

	whenClicked {
		tell("BlankJ") {
			stop()
			velocity { y = -300.0 }
			accelerate { y = 10.0 }
		}
	}

	oval {
		name("BlankJ")
		x = 30.0
		y = 80.0
		height = 50.0
		width = height
		color = BLUE
	}

	traits("block") {
		x = 350.0
		y = -50.0
		width = 50.0
		height = 200.0
		velocity { x = -120.0 }
		whenColliding("BlankJ") {
			kill("BlankJ")
			text { include("text") }
		}
	}
	traits("text") {
		x = 200.0
		y = 200.0
		text = "you lost"
	}

	every(1800) {
		rectangle {
			include("block")
			height = 50 randomTo 250
		}
		rectangle {
			include("block")
			y = 300 randomTo 450

		}
	}
}