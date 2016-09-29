# 帮助文档

这里同时也有README里面的那段代码的注释版本。

方便英语不好的中国人阅读。

# 语言

[English](./HELP.md)

```kotlin
/* 允许使用C语言风格的注释 */
// main方法是必须的
fun main(args: Array<String>) {
	// 创建一个游戏窗口（这意味着你可以通过多个game代码块来创建多个窗口
	game {
//		size(10, 10)
		bounds(500, 500, 800, 750)
		showFPS = false

		// 右上叉叉监听到点击时调用这个代码块。
		whenExit {
			closeWindow()
		}

		// 画面刷新时调用这个代码块。
		whenUpdate {
			// 每800毫秒流逝
			if (800.elapsed()) {
				rectangle {
					// elapsed就是游戏创建开始到现在的毫秒数
					x = elapsed / 10.0
					y = elapsed / 10.0
					// 颜色都是大写字母表示的
					color = PINK
				}
			}
		}

		// 这个块每1000ms调用一次
		every(1000) {
			oval {
				x = elapsed / 10.0
				y = elapsed / 10.0
				color = ORANGE
			}
			// 向"frice.log"写入Log。
			log("1 second has past.")
		}

		// 向游戏场景添加一个长方形
		rectangle {
			// 设置name，可以通过这个name指定碰撞事件，用法见下面
			name("rectangle")
			// 坐标
			x = 100.0
			y = 100.0
			// 尺寸
			width = 100.0
		}
		oval {
			x = 0.0
			y = 85.0
			// 加速移动
			accelerate {
				x = 10.0
//				y = 10.0
			}
			// 当检测到一个name等于给定字符串的对象（此处是"rectangle"）的碰撞时，会不断调用这个方法，直到两物体离开。
			whenColliding("rectangle") {
				// 停止移动
				stop()
				// 直接改变坐标
				x -= 5
				// 重新进行加速运动
				accelerate {
					x = -2.0
					y = 10.0
				}
			}
		}
		// 添加一个显示图片的对象。
		image {
			// 通过本地文件创建
			file("C:/frice.png")
			// 也可以使用url创建基于网络的图片
//			url("https://avatars3.githubusercontent.com/u/16398479")
			x = 200.0
			y = 300.0
			// 匀速直线运动
			velocity {
				x = -5.5
//				y = -5.5
			}
		}
	}
}
```
