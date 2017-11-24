@file:Suppress("FunctionName", "unused")

package org.frice.dsl

import org.frice.dsl.extension.DSLShapeObject
import org.frice.anim.move.AccurateMove
import org.frice.anim.move.DoublePair
import org.frice.obj.AbstractObject
import org.frice.obj.FObject
import org.frice.obj.button.SimpleButton

/**
 * Created by ice1000 on 2016/10/29.
 *
 * @author ice1000
 */
fun FriceBase.你特么倒是给我添加一个椭圆啊(block: DSLShapeObject.() -> Unit) = oval(block)

fun FriceBase.你特么倒是给我添加一个长方形啊(block: DSLShapeObject.() -> Unit) = rectangle(block)
fun FriceBase.你特么倒是给我添加一个按钮啊(block: SimpleButton.() -> Unit) = button(block)
fun FObject.死吧死吧死吧下地狱吧() {
	died = true
}

fun FriceBase.弹出一个傻逼一样的提示窗口然后再傻逼地显示(msg: String) = messageBox(msg)

fun FriceBase.狗日的把这个傻逼的窗口给关了() = closeWindow()
val FriceBase.狗日的把这个傻逼的窗口给关了: Unit
	get() = 狗日的把这个傻逼的窗口给关了()

fun FriceBase.当我退出历史的舞台时一定要记得(block: () -> Unit) = whenExit(block)
fun FriceBase.当我在画面上不停刷新时千万别忘记(block: () -> Unit) = whenUpdate(block)
fun FriceBase.你特么给我把标题栏文字改成(string: String) {
	title = string
}

fun FriceBase.鎏金哇卡雅酷烈(name: String) = 把它给我带来一顿痛日(name)
fun FriceBase.把它给我带来一顿痛日(name: String) = kill(name)

fun FriceBase.告诉这个傻逼(name: String, block: FObject.() -> Unit) = tell(name, block)

fun FriceBase.每隔这么多秒一定要好好执行这个代码块喔(x: Int, block: FriceGameTimer.() -> Unit) = every(x, block)

fun FObject.在这停顿() = anims.clear()
val FObject.在这停顿: Unit
	get () = 在这停顿()

fun FObject.前进前进不择手段地前进(block: DoublePair.() -> Unit) {
	val a = DoublePair(0.0, 0.0)
	block(a)
	anims.add(AccurateMove(a.x, a.y))
}

fun FriceBase.当我被鼠标点击时千万不要忘记(block: AbstractObject.() -> Unit) = whenClicked(block)

fun FriceBase.一定要把尺寸设置成这样才好看(x: Int, y: Int) = setSize(x, y)

fun 创建一个游戏窗口(block: FriceBase.() -> Unit) {
	game(block)
}
