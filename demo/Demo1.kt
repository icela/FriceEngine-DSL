import org.frice.dsl.game

/**
 * Created by ice1000 on 2016/9/29.
 *
 * @author ice1000
 */

fun main(args: Array<String>) {
	game {
		bounds(100, 100, 500, 500)
		title = "game demo"

		oval {
			x = 30.0
			height = 80.0
			color = PINK
		}

		image {
			url("https://avatars1.githubusercontent.com/u/21008243")
			y = 120.0
			velocity {
				y = 10.0
			}
		}

		rectangle {
			x = 300.0
			height = 50.0
			velocity {
				x = 10.0
			}
		}
	}
}