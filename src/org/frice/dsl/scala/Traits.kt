package org.frice.dsl.scala

import org.frice.game.resource.graphics.ColorResource

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

//	val targets = LinkedList<kotlin.Pair<PhysicalObject, FObject.OnCollideEvent>>()
//	val anims = LinkedList <FAnim>()
}


