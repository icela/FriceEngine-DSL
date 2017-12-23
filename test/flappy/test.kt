package flappy

import org.frice.dsl.game

fun Array<String>.main() = game {
	bounds {
		x = 100
		y = 100
		width = 400
		height = 500
	}
	var lol = 300.0
	every(1800) {
		rectangle {
			x = lol
			y = 100.0
			width = 100.0
		}
		lol -= 10.0
	}
}