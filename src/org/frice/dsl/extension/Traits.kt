package org.frice.dsl.extension

import org.frice.anim.FAnim
import org.frice.anim.move.AccelerateMove
import org.frice.anim.move.AccurateMove
import org.frice.obj.SideEffect
import org.frice.resource.graphics.ColorResource
import java.util.*

/**
 * attempt to achieve a scala-like traits
 * Created by ice1000 on 2016/9/29.
 *
 * @author ice1000
 */

class Traits(val name: String) {
	var x: Double? = null
	var y: Double? = null
	var color: ColorResource? = null
	var width: Double? = null
	var height: Double? = null
	var text: String? = null

	val targets = LinkedList<FTargetForTraits>()
	val anims = LinkedList <FAnimForTraits>()
}

interface FAnimForTraits {
	fun new(): FAnim
}

class DoublePair(var x: Double, var y: Double)

class 加速度对(var 右行加速度: Double, var 下落加速度: Double)

class 速度对(var 右行速度: Double, var 下落速度: Double)

class AccurateMoveForTraits(var x: Double, var y: Double) : FAnimForTraits {
	override fun new() = AccurateMove(x, y)
}

class AccelerateMoveForTraits(var x: Double, var y: Double): FAnimForTraits {
	override fun new() = AccelerateMove(x, y)
}

class FTargetForTraits(var string: String, var event: SideEffect)
