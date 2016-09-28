import org.frice.dsl.game

/**
 * Created by ice1000 on 2016/9/28.
 *
 * @author ice1000
 */
fun main(args: Array<String>) {
	game {
//		size(10, 10)
		bounds(500, 500, 800, 750)
		rectangle {
			x = 100.0
			y = 100.0
		}
		oval {
			x = 30.0
			y = 25.0
		}
		image {
			file("C:/frice.png")
			x = 200.0
			y = 300.0
		}
	}
}