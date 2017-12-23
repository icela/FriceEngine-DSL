package flappy

import frice.game

fun Array<String>.main() = game {
	title = "flappy bird demo by ice1000"
	bounds {
		x = 100
		y = 100
		width = 400
		height = 500
	}
	whenExit { closeWindow() }
	whenClicked {
		tell("2333") {
			stop()
			velocity { y = -200.0 }
			accelerate { y = 600.0 }
		}
	}
	oval {
		name("2333")
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
		whenColliding("2333") {
			kill("2333")
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