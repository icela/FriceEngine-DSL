# Help document

Here is the commented demo from README.

# Language

[简体中文](./HELP_ZH.md)

```kotlin
/* C-style comments are allowed */
// a necessary main function
fun main(args: Array<String>) {
	// create a game window(this means you can create more than one window simply by using more than one "game" block.
	game {
//		size(10, 10)
		bounds(500, 500, 800, 750)
		showFPS = false

		// called when 'close button' is clicked
		whenExit {
			closeWindow()
		}

		// called when scene has refreshed
		whenUpdate {
			// if 800ms has past
			if (800.elapsed()) {
				rectangle {
					// elapsed = millis second from game create
					x = elapsed / 10.0
					y = elapsed / 10.0
					// colors are represented CAPITALLY.
					color = PINK
				}
			}
		}

		// will be invoked every 1000ms
		every(1000) {
			oval {
				x = elapsed / 10.0
				y = elapsed / 10.0
				color = ORANGE
			}
			// write logs into a file "frice.log"
			log("1 second has past.")
		}

		// add a rectangle to the game scene
		rectangle {
			// name is an identifier of the game object.
			name("rectangle")
			// location
			x = 100.0
			y = 100.0
			// size setting is allowed
			width = 100.0
		}
		oval {
			x = 0.0
			y = 85.0
			// let the object move with acceleration.
			accelerate {
				x = 10.0
//				y = 10.0
			}
			// when colliding the object naming of the given msg("rectangle" here), the code in the block will be called.
			whenColliding("rectangle") {
				// stop moving
				stop()
				// change position
				x -= 5
				// move with acceleration again
				accelerate(-2, 10)
			}
		}
		// add an object displaying image.
		image {
			// from file
			file("C:/frice.png")
			// using url is allowed
//			url("https://avatars3.githubusercontent.com/u/16398479")
			x = 200.0
			y = 300.0
			// move simply
			velocity {
				x = -5.5
//				y = -5.5
			}
		}
	}
}
```


## flappy bird

```kotlin
fun main(args: Array<String>) = game {
	bounds(100, 100, 400, 500)
	title = "flappy bird demo by ice1000"

	whenExit { closeWindow() }

	whenClicked {
		tell("BlankJ") { stop(); velocity { y = -300.0 }; accelerate { y = 10.0 } }
	}

	oval {
		name("BlankJ")
		x = 30.0; y = 80.0; height = 50.0; width = height; color = BLUE
	}

	traits("block") { x = 350.0; y = -50.0; width = 50.0; height = 200.0 }
	traits("text") { x = 200.0; y = 200.0; text = "you lost" }

	every(1800) {
		rectangle {
			include("block")
			height = 50 randomTo 250
			velocity { x = -120.0 }
			whenColliding("BlankJ") { tell("BlankJ") { stop() }; text { include("text") } }
		}
		rectangle {
			include("block")
			y = 300 randomTo 450
			velocity { x = -120.0 }
			whenColliding("BlankJ") { tell("BlankJ") { stop() }; text { include("text") } }
		}
	}
}
```