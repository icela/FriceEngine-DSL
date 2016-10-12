# Frice DSL

![](https://avatars1.githubusercontent.com/u/21008243)

This is a DSL system for FriceEngine.<br/>
And it's written in **Kotlin**.

# Overview

If U wonder more about syntax or miscellaneous stuffs, please view [Help doc](./HELP.md).<br/>
It has commented every lines of code with its meaning.

```kotlin
fun demo.main(args: Array<String>) {
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
			}
		}
	}
}
```

See? The syntax looks like Gradle, but this one is more powerful, clear and easy.

And they are all **VALID Kotlin codes**. U can even compile them directly with **kotlinc** the Kotlin compiler!

# How to use

### simplest

Simply using Kotlin compiler **kotlinc** to compile your code, with classpath directing this project and the jar of FriceEngine.

please wait for the release, after that U can import a jar into your IDE and use it.


### Recommended

Gradle is strongly recommended!

```groovy
// build.gradle:

dependencies {
    compile 'com.github.icela:FriceEngine-DSL:v0.1'
}


// global gradle file

allprojects {
    repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
}

```
