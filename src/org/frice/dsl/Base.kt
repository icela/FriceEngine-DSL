@file:Suppress("unused")

package org.frice.dsl

import org.frice.Game
import org.frice.anim.move.AccelerateMove
import org.frice.anim.move.AccurateMove
import org.frice.dsl.extension.*
import org.frice.event.*
import org.frice.launch
import org.frice.obj.*
import org.frice.obj.button.*
import org.frice.obj.sub.ImageObject
import org.frice.obj.sub.ShapeObject
import org.frice.resource.graphics.ColorResource
import org.frice.resource.image.ImageResource
import org.frice.utils.forceRun
import org.frice.utils.image2File
import org.frice.utils.shape.FOval
import org.frice.utils.shape.FRectangle
import java.awt.Dimension
import java.awt.Rectangle
import java.io.File
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

/**
 * FriceBase framework of frice engine
 * Created by ice1000 on 2016/9/28.
 *
 * @author ice1000
 */
@Suppress("MemberVisibilityCanPrivate", "PropertyName")
open class FriceBase(val block: FriceBase.() -> Unit) : Game() {

	companion object {
		inline fun unless(condition: Boolean, block: () -> Unit) {
			if (!condition) block()
		}
	}

	val BLACK = ColorResource.BLACK
	val 黑 = BLACK
	val BLUE = ColorResource.BLUE
	val 蓝 = BLUE
	val RED = ColorResource.RED
	val 红 = RED
	val PINK = ColorResource.PINK
	val 粉 = PINK
	val GREEN = ColorResource.GREEN
	val 绿 = GREEN
	val GRAY = ColorResource.GRAY
	val 灰 = GRAY
	val WHITE = ColorResource.WHITE
	val 白 = WHITE
	val YELLOW = ColorResource.YELLOW
	val 黄 = YELLOW
	val COLORLESS = ColorResource.COLORLESS
	val 无色 = COLORLESS
	val CYAN = ColorResource.CYAN
	val 青 = CYAN
	val ORANGE = ColorResource.ORANGE
	val 橙 = ORANGE
	val MAGENTA = ColorResource.MAGENTA
	val 洋红 = MAGENTA

	var onExits = { }
	var onClick = ArrayList<Consumer<AbstractObject>>(20)
	var onPress = ArrayList<Consumer<AbstractObject>>(5)
	var onUpdates = { }
	val collisions = ArrayList<Triple<Collidable, Collidable, SideEffect>>()
	val namedObjects = LinkedHashMap<String, AbstractObject>(20)
	val namedTraits = LinkedHashMap<String, Traits>(20)
	val clickListeningObjects = LinkedHashMap<String, FObject>(20)
	val timers = ArrayList<Pair<Int, SideEffect>>(5)

	private val timer = FriceGameTimer()

	var logFile = "frice.log"

	val elapsed: Double
		get() = timer.elapsed
	val 经过时间: Double
		get() = elapsed

	/**
	 * cannot be in 'onInit'
	 */
	override fun onLastInit() {
		super.onLastInit()
		block(this)
	}

	fun requestGC() = System.gc()

	fun size(width: Int, height: Int) {
		size = Dimension(width, height)
	}

	fun bounds(x: Int, y: Int, width: Int, height: Int) {
		bounds = Rectangle(x, y, width, height)
	}

	fun 边界(左上角x: Int, 左上角y: Int, 宽: Int, 高: Int) = bounds(左上角x, 左上角y, 宽, 高)

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

	fun 日志(文本: String) = log(文本)

	fun rectangle(block: DSLShapeObject.() -> Unit) {
		val so = DSLShapeObject(ColorResource.西木野真姬, FRectangle(50, 50))
		block(so)
		addObject(so)
	}

	fun 长方形(块: DSLShapeObject.() -> Unit) = rectangle(块)

	fun oval(block: DSLShapeObject.() -> Unit) {
		val so = DSLShapeObject(ColorResource.西木野真姬, FOval(25.0, 25.0))
		block(so)
		addObject(so)
	}

	fun 椭圆(块: DSLShapeObject.() -> Unit) = oval(块)

	fun image(block: ImageObject.() -> Unit) {
		val io = ImageObject(ImageResource.empty())
		block(io)
		addObject(io)
	}

	fun 图片(block: DSLImageObject.() -> Unit) {
		val io = DSLImageObject(ImageResource.empty())
		block(io)
		addObject(io)
	}

	fun text(block: SimpleText.() -> Unit) {
		val st = SimpleText(text = "", x = 0.0, y = 0.0)
		block(st)
		addObject(st)
	}

	fun button(block: SimpleButton.() -> Unit) {
		val sb = SimpleButton(text = "", x = 0.0, y = 0.0, width = .0, height = .0)
		block(sb)
		addObject(sb)
	}

	fun imageButton(block: ImageButton.() -> Unit) {
		val ib = ImageButton(ImageResource.empty(), x = 0.0, y = 0.0)
		block(ib)
		addObject(ib)
	}

	fun whenExit(block: () -> Unit) {
		onExits = block
	}

	fun 当退出时(块: () -> Unit) = whenExit(块)

	fun whenUpdate(block: () -> Unit) {
		onUpdates = block
	}

	fun 当更新时(块: () -> Unit) = whenUpdate(块)

	fun whenClicked(block: AbstractObject.() -> Unit) {
		onClick.add(Consumer(block))
	}

	fun every(millisSeconds: Int, block: FriceGameTimer.() -> Unit) {
		timers.add(millisSeconds to SideEffect { block(timer) })
	}

	fun 每隔(毫秒: Int, 块: FriceGameTimer.() -> Unit) = every(毫秒, 块)

	fun tell(name: String, block: FObject.() -> Unit) =
		if (name in namedObjects) block(namedObjects[name] as FObject)
		else throw DSLErrorException()

	fun kill(name: String) = namedObjects[name]?.die

	fun Long.elapsed() = timer.stopWatch(this)
	fun Int.elapsed() = timer.stopWatch(this.toLong())
	fun Int.毫秒后() = this.elapsed()
	infix fun Long.from(begin: Long) = this - begin
	infix fun Long.from(begin: Int) = this - begin
	infix fun Int.from(begin: Int) = this - begin
	infix fun Int.from(begin: Long) = this - begin
	infix fun Int.til(int: Int) = rangeTo(int)
	infix fun Int.til(int: Long) = rangeTo(int)
	infix fun Long.til(long: Long) = rangeTo(long)
	infix fun Long.til(long: Int) = rangeTo(long)

	infix fun Int.randomTo(int: Int) = (Math.random() * (int - this) + this)
	infix fun Int.randomDownTo(int: Int) = (Math.random() * (this - int) + int)

	fun AbstractObject.name(s: String) = namedObjects.put(s, this)
	fun AbstractObject.取名(名字: String) = this.name(名字)

	fun ImageObject.file(s: String) {
		res = ImageResource.fromPath(s)
	}

	fun ImageObject.文件(文件名: String) = this.file(文件名)

	fun ImageObject.url(s: String) {
		res = ImageResource.fromWeb(s)
	}

	fun ImageObject.远程文件(网址: String) = url(网址)

	fun FObject.velocity(block: DoublePair.() -> Unit) {
		val a = DoublePair(0.0, 0.0)
		block(a)
		anims += AccurateMove(a.x, a.y)
	}

	fun FObject.速度(块: 左上角坐标.() -> Unit) {
		val a = 左上角坐标(0.0, 0.0)
		块(a)
		anims += AccurateMove(a.左上角x, a.左上角y)
	}

	fun FObject.velocity(x: Int, y: Int) = velocity(x.toDouble(), y.toDouble())

	fun FObject.velocity(x: Double, y: Double) {
		anims += AccurateMove(x, y)
	}

	fun Traits.velocity(block: AccurateMoveForTraits.() -> Unit) {
		val a = AccurateMoveForTraits(0.0, 0.0)
		block(a)
		anims += a
	}

	fun Traits.velocity(x: Double, y: Double) {
		val a = AccurateMoveForTraits(x, y)
		anims += a
	}

	fun Traits.velocity(x: Int, y: Int) = velocity(x.toDouble(), y.toDouble())

	fun FObject.whenClicked(block: () -> Unit) {
		forceRun {
			onClick.add(element = Consumer { e ->
				if (containsPoint(e.x.toInt(), e.y.toInt())) block()
			})
		}
	}

	fun FObject.whenPressed(block: () -> Unit) {
		forceRun {
			onPress.add(element = Consumer { e ->
				if (containsPoint(e.x.toInt(), e.y.toInt())) block()
			})
		}
	}

	fun FObject.stop() = anims.clear()
	fun FObject.停止() = this.stop()

	val FObject.stop: Unit
		get() = stop()

	fun FObject.accelerate(block: DoublePair.() -> Unit) {
		val a = DoublePair(0.0, 0.0)
		block(a)
		anims += AccelerateMove(a.x, a.y)
	}

	fun FObject.加速(块: 加速度对.() -> Unit) {
		val a = 加速度对(0.0, 0.0)
		块(a)
		anims += AccelerateMove(a.横向加速度_左负右正, a.纵向加速度_上负下正)
	}

	fun FObject.accelerate(x: Double, y: Double) {
		anims += AccelerateMove(x, y)
	}

	fun FObject.加速(横向加速度_左负右正: Double, 纵向加速度_上负下正: Double) = accelerate(横向加速度_左负右正, 纵向加速度_上负下正)

	fun FObject.accelerate(x: Int, y: Int) = accelerate(x.toDouble(), y.toDouble())

	fun FObject.加速(横向加速度_左负右正: Int, 纵向加速度_上负下正: Int) = accelerate(横向加速度_左负右正, 纵向加速度_上负下正)

	fun Traits.accelerate(block: AccelerateMoveForTraits.() -> Unit) {
		val a = AccelerateMoveForTraits(0.0, 0.0)
		block(a)
		anims += a
	}

	fun Traits.accelerate(x: Double, y: Double) {
		anims += AccelerateMoveForTraits(x, y)
	}

	fun Traits.accelerate(x: Int, y: Int) = accelerate(x.toDouble(), y.toDouble())

	fun FButton.whenClicked(block: Consumer<OnMouseEvent>) {
		onMouseListener = block
	}

	fun PhysicalObject.whenColliding(
		otherName: String,
		block: PhysicalObject.() -> Unit) {
		val other = namedObjects[otherName]
		if (other is PhysicalObject)
			collisions += Triple(this, other, SideEffect { block(this@whenColliding) })
	}

	fun PhysicalObject.当碰撞(
		目标物名: String,
		块: PhysicalObject.() -> Unit) = this.whenColliding(目标物名, 块)

	fun Traits.whenColliding(
		otherName: String,
		block: SideEffect) {
		targets += FTargetForTraits(otherName, block)
	}

	fun AbstractObject.include(name: String) {
		namedTraits[name]?.let {
			x = it.x ?: x
			y = it.y ?: y
		}
	}

	fun ShapeObject.include(name: String) {
		forceRun {
			namedTraits[name]?.let {
				x = it.x ?: x
				y = it.y ?: y
				color = it.color ?: color
				width = it.width ?: width
				height = it.height ?: height
				collisions += it.targets
					.filter { it.string in namedObjects }
					.map { target ->
						val str = namedObjects[target.string]!! as PhysicalObject
						Triple(this, str, SideEffect { (target.event)() })
					}
				anims.addAll(it.anims.map(FAnimForTraits::new))
			}
		}
	}

	fun SimpleText.include(name: String) {
		namedTraits[name]?.let {
			x = it.x ?: x
			y = it.y ?: y
			color = it.color ?: color
			text = it.text ?: text
		}
	}

	fun SimpleButton.include(name: String) {
		namedTraits[name]?.let {
			x = it.x ?: x
			y = it.y ?: y
			text = it.text ?: text
			width = it.width ?: width
			height = it.height ?: height
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

	val AbstractObject.die get() = die()

	fun inputInt(msg: String, title: String = "Input an integer") = dialogInput(msg, title).toInt()
	fun inputDouble(msg: String, title: String = "Input a double") = dialogInput(msg, title).toDouble()
	fun inputFloat(msg: String, title: String = "Input a float") = dialogInput(msg, title).toFloat()

	fun inputString(msg: String, title: String = "Input text") = dialogInput(msg, title)

	fun closeWindow() = System.exit(0)
	fun 关闭窗口() = closeWindow()
	val closeWindow get () = closeWindow()

	fun cutScreen() = screenCut.image.image2File("screenshot.png")

	val cutScreen get() = cutScreen()

	override fun onExit() {
		onExits()
		super.onExit()
	}

	override fun onRefresh() {
		onUpdates()
		collisions.forEach { (a, b, event) ->
			if (a.collides(b)) event()
		}
		super.onRefresh()
	}

	override fun onMouse(e: OnMouseEvent) {
		when (e.type) {
			MOUSE_PRESSED -> onPress.forEach { o -> forceRun { o.accept(mouse) } }
			MOUSE_CLICKED -> onClick.forEach { o -> forceRun { o.accept(mouse) } }
		}
		super.onMouse(e)
	}

	var 显示帧率: Boolean
		get() = showFPS
		set(值) {
			showFPS = 值
		}
}

class DSLErrorException : Exception("Error DSL!")

@JvmName("gameInPackage")
fun game(block: FriceBase.() -> Unit) = launch(FriceBase(block))

fun 游戏(块: FriceBase.() -> Unit) = game(块)

