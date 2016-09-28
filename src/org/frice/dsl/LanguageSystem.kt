package org.frice.dsl

import org.frice.game.Game
import org.frice.game.obj.sub.ImageObject
import org.frice.game.obj.sub.ShapeObject
import org.frice.game.resource.graphics.ColorResource
import org.frice.game.resource.image.ImageResource
import org.frice.game.utils.graphics.shape.FOval
import org.frice.game.utils.graphics.shape.FRectangle
import java.awt.Dimension
import java.awt.Rectangle

/**
 * LanguageSystem framework of frice engine
 * Created by ice1000 on 2016/9/28.
 *
 * @author ice1000
 */
class LanguageSystem(val block: LanguageSystem.() -> Unit) : Game() {

	var onExit: (() -> Unit)? = null

	/**
	 * cannot be in 'onInit'
	 */
	override fun onLastInit() {
		super.onInit()
		block.invoke(this)
	}

	fun size(width: Int, height: Int) {
		size = Dimension(width, height)
	}

	fun bounds(x: Int, y: Int, width: Int, height: Int) {
		bounds = Rectangle(x, y, width, height)
	}

	fun rectangle(block: ShapeObject.() -> Unit) {
		val so = ShapeObject(ColorResource.西木野真姬, FRectangle(50, 50))
		block(so)
		addObject(so)
	}

	fun oval(block: ShapeObject.() -> Unit) {
		val so = ShapeObject(ColorResource.西木野真姬, FOval(25.0, 25.0))
		block(so)
		addObject(so)
	}

	fun image(block: ImageObject.() -> Unit) {
		val io = ImageObject(ImageResource.empty())
		block(io)
		addObject(io)
	}

	fun ImageObject.file(s: String) {
		res = ImageResource.fromPath(s)
	}

	fun ImageObject.url(s: String) {
		res = ImageResource.fromWeb(s)
	}
}

@JvmName("game")
fun game(block: LanguageSystem.() -> Unit) {
	val dsl = LanguageSystem(block)
}