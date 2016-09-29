import org.frice.dsl.game

/**
 * Created by ice1000 on 2016/9/29.
 *
 * @author ice1000
 */

fun main(args: Array<String>) {
	game {
		bounds(100, 100, 500, 500)

		oval {
			x = 30.0
			y = 30.0
			width = 100.0
			height = 80.0
			color = PINK
		}

		image {
			url("https://avatars1.githubusercontent.com/u/21008243")
			x = 120.0
			y = 120.0
		}
	}
}