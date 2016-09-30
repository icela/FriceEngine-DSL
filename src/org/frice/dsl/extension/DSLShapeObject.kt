package org.frice.dsl.extension

import org.frice.game.obj.sub.ShapeObject
import org.frice.game.resource.graphics.ColorResource
import org.frice.game.utils.graphics.shape.FShape

/**
 * Provide sth that is needed by DSL
 * Created by ice1000 on 2016/9/29.
 *
 * @author ice1000
 */
class DSLShapeObject(res: ColorResource, shape: FShape) : ShapeObject(res, shape) {
	var color: ColorResource
		get() = res
		set(value) {
			res = value
		}
}