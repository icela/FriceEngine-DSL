package org.frice.dsl

import org.frice.dsl.extension.DSLShapeObject
import org.frice.dsl.scala.Traits
import org.frice.game.Game
import org.frice.game.anim.move.AccelerateMove
import org.frice.game.anim.move.AccurateMove
import org.frice.game.anim.move.DoublePair
import org.frice.game.event.OnClickEvent
import org.frice.game.obj.AbstractObject
import org.frice.game.obj.FObject
import org.frice.game.obj.PhysicalObject
import org.frice.game.obj.button.FButton
import org.frice.game.obj.button.ImageButton
import org.frice.game.obj.button.SimpleButton
import org.frice.game.obj.button.SimpleText
import org.frice.game.obj.sub.ImageObject
import org.frice.game.obj.sub.ShapeObject
import org.frice.game.resource.graphics.ColorResource
import org.frice.game.resource.image.ImageResource
import org.frice.game.utils.graphics.shape.FOval
import org.frice.game.utils.graphics.shape.FRectangle
import org.frice.game.utils.message.FDialog
import org.frice.game.utils.misc.forceRun
import org.frice.game.utils.time.FTimeListener
import java.awt.Dimension
import java.awt.Rectangle
import java.io.File
import java.util.*

/**
 * LanguageSystem framework of frice engine
 * Created by ice1000 on 2016/9/28.
 *
 * @author ice1000
 */
class LanguageSystem(val block: LanguageSystem.() -> Unit) : Game() {

	companion object {
		inline fun unless(condition: Boolean, block: () -> Unit) {
			if (!condition) block()
		}
	}

	val BLACK = ColorResource.BLACK
	val BLUE = ColorResource.BLUE
	val RED = ColorResource.RED
	val PINK = ColorResource.PINK
	val GREEN = ColorResource.GREEN
	val GRAY = ColorResource.GRAY
	val WHITE = ColorResource.WHITE
	val YELLOW = ColorResource.YELLOW
	val COLORLESS = ColorResource.COLORLESS
	val CYAN = ColorResource.CYAN
	val ORANGE = ColorResource.ORANGE
	val MAGENTA = ColorResource.MAGENTA

	var onExit: (() -> Unit)? = null
	var onClick: ((AbstractObject) -> Unit)? = null
	var onUpdate: (() -> Unit)? = null
	val namedObjects = LinkedHashMap<String, AbstractObject>(20)
	val namedTraits = LinkedHashMap<String, Traits>(20)

	val timer = FriceGameTimer()

	val logFile = "frice.log"

	val elapsed: Double
		get() = timer.elapsed

	/**
	 * cannot be in 'onInit'
	 */
	override fun onLastInit() {
		super.onLastInit()
		block.invoke(this)
	}

	fun size(width: Int, height: Int) {
		size = Dimension(width, height)
	}

	fun bounds(x: Int, y: Int, width: Int, height: Int) {
		bounds = Rectangle(x, y, width, height)
	}

	fun bounds(block: Rectangle.() -> Unit) {
		val t = Rectangle(x, y, width, height)
		block(t)
		bounds(t.x, t.y, t.width, t.height)
	}

	fun log(s: String) {
		val f = File(logFile)
		if (!f.exists()) f.createNewFile()
		f.appendText("$s\n")
	}

	fun rectangle(block: DSLShapeObject.() -> Unit) {
		val so = DSLShapeObject(ColorResource.西木野真姬, FRectangle(50, 50))
		block(so)
		addObject(so)
	}

	fun oval(block: DSLShapeObject.() -> Unit) {
		val so = DSLShapeObject(ColorResource.西木野真姬, FOval(25.0, 25.0))
		block(so)
		addObject(so)
	}

	fun image(block: ImageObject.() -> Unit) {
		val io = ImageObject(ImageResource.empty())
		block(io)
		addObject(io)
	}

	fun text(block: SimpleText.() -> Unit) {
		val st = SimpleText("", 0.0, 0.0)
		block(st)
		addObject(st)
	}

	fun button(block: SimpleButton.() -> Unit) {
		val sb = SimpleButton("", 0.0, 0.0, 80.0, 30.0)
		block(sb)
		addObject(sb)
	}

	fun imageButton(block: ImageButton.() -> Unit) {
		val ib = ImageButton(ImageResource.empty(), 0.0, 0.0)
		block(ib)
		addObject(ib)
	}

	fun whenExit(block: () -> Unit) {
		onExit = block
	}

	fun whenUpdate(block: () -> Unit) {
		onUpdate = block
	}

	fun whenClicked(block: AbstractObject.() -> Unit) {
		onClick = block
	}

	fun every(millisSeconds: Int, block: FriceGameTimer.() -> Unit) {
		addTimeListener(FTimeListener(millisSeconds, {
			block(timer)
		}))
	}

	fun tell(name: String, block: FObject.() -> Unit) {
		forceRun {
			block.invoke(namedObjects[name] as FObject)
		}
	}

	fun Long.elapsed() = timer.stopWatch(this)
	fun Int.elapsed() = timer.stopWatch(this.toLong())
	infix fun Long.from(begin: Long) = this - begin
	infix fun Int.from(begin: Int) = this - begin

	infix fun Int.randomTo(int: Int) = (random.nextInt(int - this) + this).toDouble()
	infix fun Int.randomDownTo(int: Int) = (random.nextInt(this - int) + int).toDouble()


	fun AbstractObject.name(s: String) = namedObjects.put(s, this)

	fun ImageObject.file(s: String) {
		res = ImageResource.fromPath(s)
	}

	fun ImageObject.url(s: String) {
		res = ImageResource.fromWeb(s)
	}

	fun FObject.velocity(block: DoublePair.() -> Unit) {
		val a = DoublePair(0.0, 0.0)
		block(a)
		anims.add(AccurateMove(a.x, a.y))
	}

//	fun Traits.velocity(block: DoublePair.() -> Unit) {
//		val a = DoublePair(0.0, 0.0)
//		block(a)
//		anims.add(AccurateMove(a.x, a.y))
//	}

	fun FObject.stop() = anims.clear()

	fun FObject.accelerate(block: DoublePair.() -> Unit) {
		val a = DoublePair(0.0, 0.0)
		block(a)
		anims.add(AccelerateMove(a.x, a.y))
	}

//	fun Traits.accelerate(block: DoublePair.() -> Unit) {
//		val a = DoublePair(0.0, 0.0)
//		block(a)
//		anims.add(AccelerateMove(a.x, a.y))
//	}

	fun FObject.force(block: DoublePair.() -> Unit) {
		val a = DoublePair(0.0, 0.0)
		block(a)
		addForce(a.x, a.y)
	}

	fun FButton.whenClicked(block: (OnClickEvent) -> Unit) {
		onClickListener = object : FButton.OnClickListener {
			override fun onClick(e: OnClickEvent) = block(e)
		}
	}

	fun FObject.whenColliding(
			otherName: String,
			block: PhysicalObject.() -> Unit) {
		val other = namedObjects[otherName]
		if (other is PhysicalObject)
			targets.add(Pair(other, object : FObject.OnCollideEvent {
				override fun handle() = block(this@whenColliding)
			}))
	}

//	fun Traits.whenColliding(
//			otherName: String,
//			block: () -> Unit) {
//		val other = namedObjects[otherName]
//		if (other is PhysicalObject)
//			targets.add(Pair(other, object : FObject.OnCollideEvent {
//				override fun handle() = block()
//			}))
//	}


	fun AbstractObject.include(name: String) {
		forceRun {
			val t = namedTraits[name]!!
			if (t.x != null) x = t.x!!
			if (t.y != null) y = t.y!!
		}
	}

	fun ShapeObject.include(name: String) {
		forceRun {
			val t = namedTraits[name]!!
			if (t.x != null) x = t.x!!
			if (t.y != null) y = t.y!!
			if (t.color != null) res = t.color!!
			if (t.width != null) width = t.width!!
			if (t.height != null) height = t.height!!
//			anims.addAll(t.anims)
//			targets.addAll(t.targets)
		}
	}

	fun SimpleText.include(name: String) {
		forceRun {
			val t = namedTraits[name]!!
			if (t.x != null) x = t.x!!
			if (t.y != null) y = t.y!!
			if (t.color != null) colorResource = t.color!!
			if (t.text != null) text = t.text!!
		}
	}

	fun SimpleButton.include(name: String) {
		forceRun {
			val t = namedTraits[name]!!
			if (t.x != null) x = t.x!!
			if (t.y != null) y = t.y!!
			if (t.color != null) colorResource = t.color!!
			if (t.text != null) text = t.text!!
			if (t.width != null) width = t.width!!
			if (t.height != null) height = t.height!!
		}
	}

	fun traits(name: String, block: Traits.() -> Unit) {
		val t = Traits(name)
		block(t)
		namedTraits.put(t.name, t)
	}

	fun AbstractObject.die() {
		if (this is PhysicalObject) died = true
		for (k in namedObjects.keys) {
			if (this == namedObjects[k]) {
				namedObjects.remove(k)
				break
			}
		}
	}

	fun messageBox(msg: String) = FDialog(this).show(msg)

	fun inputInt(msg: String) = FDialog(this).input(msg).toInt()

	fun inputString(msg: String) = FDialog(this).input(msg)

	fun closeWindow() = System.exit(0)

	override fun onExit() {
		onExit?.invoke()
		super.onExit()
	}

	override fun onRefresh() {
		onUpdate?.invoke()
		super.onRefresh()
	}

	override fun onClick(e: OnClickEvent) {
		onClick?.invoke(mouse)
		super.onClick(e)
	}
}

@JvmName("game")
fun game(block: LanguageSystem.() -> Unit) {
	LanguageSystem(block)
}

