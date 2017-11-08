package org.frice.dsl.extension

import org.frice.obj.sub.ShapeObject
import org.frice.resource.graphics.ColorResource
import org.frice.utils.shape.FShapeInt

/**
 * Provide sth that is needed by DSL
 * Created by ice1000 on 2016/9/29.
 *
 * @author ice1000
 */
class DSLShapeObject(res: ColorResource, shape: FShapeInt) : ShapeObject(res, shape) {
	var color: ColorResource
		get() = res
		set(value) {
			res = value
		}
}

