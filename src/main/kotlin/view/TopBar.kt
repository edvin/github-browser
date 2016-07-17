package view

import app.Styles.Companion.logoIcon
import app.Styles.Companion.medium
import app.Styles.Companion.topbar
import javafx.scene.layout.HBox
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
                    addClass(logoIcon, medium)
                }
                label("GitHub Browser")
                textfield {
                    prefColumnCount = 20
                    promptText = "Search"
                }
            }
        }
    }
}
