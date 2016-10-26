package frice

import org.frice.dsl.LanguageSystem

/**
 * Created by ice1000 on 2016/10/26.
 *
 * @author ice1000
 */

@JvmName("game")
fun game(block: LanguageSystem.() -> Unit) {
    LanguageSystem(block)
}

