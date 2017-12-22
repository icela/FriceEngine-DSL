package frice

import org.frice.dsl.FriceBase
import org.frice.launch

/**
 * Created by ice1000 on 2016/10/26.
 *
 * @author ice1000
 */

fun game(block: FriceBase.() -> Unit) {
	launch(FriceBase(block))
}

