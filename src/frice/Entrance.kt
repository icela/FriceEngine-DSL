package frice

import org.frice.dsl.FriceBase

/**
 * Created by ice1000 on 2016/10/26.
 *
 * @author ice1000
 */

@JvmName("game")
fun game(block: FriceBase.() -> Unit) {
    FriceBase(block)
}

