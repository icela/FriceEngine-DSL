# Frice DSL

This is a DSL system for FriceEngine.<br/>
And it's written in **Kotlin**.

# What does the "DSL" look like

```kotlin
fun main(args: Array<String>) { // a necessary main function
	game {
//		size(10, 10)
		bounds(500, 500, 800, 750)
		showFPS = false
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
```

See? The syntax looks like the one Gradle scripts.

And they are all **valid Kotlin codes**.
