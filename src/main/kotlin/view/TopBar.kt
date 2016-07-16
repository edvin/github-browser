package view

import javafx.scene.layout.HBox
import app.Styles.Companion.logoIcon
import app.Styles.Companion.topbar
import tornadofx.View
import tornadofx.addClass
import tornadofx.label
import tornadofx.textfield

class TopBar : View() {
    override val root = HBox()

    init {
        with(root) {
            addClass(topbar)
            contentBox {
                label {
                    addClass(logoIcon)
                }
                label("GitHub Browser")
                textfield {
                    promptText = "Search"
                }
            }
        }
    }
}
