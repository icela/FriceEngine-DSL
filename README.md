# Frice DSL

![](https://avatars1.githubusercontent.com/u/21008243)

CI|Status
:---:|:---|
Travis CI|[![Build Status](https://travis-ci.org/icela/FriceEngine-DSL.svg?branch=master)](https://travis-ci.org/icela/FriceEngine-DSL)
AppVeyor|[![Build status](https://ci.appveyor.com/api/projects/status/tne8wv0mofxgg31p/branch/master?svg=true)](https://ci.appveyor.com/project/ice1000/friceengine-dsl/branch/master)

[![](https://jitpack.io/v/icela/FriceEngine-DSL.svg)](https://jitpack.io/#icela/FriceEngine-DSL)

This is a DSL system for FriceEngine.<br/>
And it's written in **Kotlin**.

# Overview

If U wonder more about syntax or miscellaneous stuffs, please view [Help doc](./HELP.md).<br/>
It has commented every lines of code with its meaning.

```kotlin
fun main(args: Array<String>) {
	game {
		bounds(500, 500, 800, 750)
		showFPS = false

		whenExit {
			closeWindow()
		}

		whenUpdate {
			if (800.elapsed()) {
				rectangle {
					x = elapsed / 10.0
					y = elapsed / 10.0
					color = PINK
				}
			}
		}

		every(1000) {
			oval {
				x = elapsed / 10.0
				y = elapsed / 10.0
				color = ORANGE
			}
			log("1 second has past.")
		}

		rectangle {
			name("rectangle")
			x = 100.0
			y = 100.0
			width = 100.0
		}
		oval {
			x = 0.0
			y = 85.0
			accelerate {
				x = 10.0
			}
			whenColliding("rectangle") {
				stop()
				x -= 5
				accelerate {
					x = -2.0
					y = 10.0
				}
			}
		}
		image {
			file("C:/frice.png")
			x = 200.0
			y = 300.0
			velocity {
				x = -5.5
			}
		}
	}
}
```

See? The syntax looks like Gradle, but this one is more powerful, clear and easy.

And they are all **VALID Kotlin codes**. U can even compile them directly with **kotlinc** the Kotlin compiler!

# How to use

You can use Gradle.

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
	compile 'com.github.icela:FriceEngine-DSL:v0.3'
}
```

# The Chinese version

我还做了一个魔改版本，暂时称之为易语言，充满了粗鄙之语。省略import和main：

```kotlin
创建一个游戏窗口 {
	一定要把尺寸设置成这样才好看(300, 300)
	你特么给我把标题栏文字改成("我简直不知道中文编程有什么好玩的")
	你特么倒是给我添加一个椭圆啊 {
		name("老司机")
		x = 60.0
		y = 40.0
		前进前进不择手段地前进 {
			x = 10.0
			y = 20.0
		}
	}
	你特么倒是给我添加一个按钮啊 {
		x = 20.0
		y = 10.0
	}
	你特么倒是给我添加一个长方形啊 {
		name("真·老司机")
	}
	当我被鼠标点击时千万不要忘记 {
		告诉这个傻逼("老司机") {
			在这停顿
		}
	}
	每隔这么多秒一定要好好执行这个代码块喔(2000) {
		弹出一个傻逼一样的提示窗口然后再傻逼地显示("我简直日了狗了")
	}
	当我退出历史的舞台时一定要记得 {
		鎏金哇卡雅酷烈("真·老司机")
		把它给我带来一顿痛日("老司机")
		狗日的把这个傻逼的窗口给关了
	}
}
```


