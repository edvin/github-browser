package view

import app.Styles.Companion.icon
import app.Styles.Companion.logoIcon
import app.Styles.Companion.medium
import app.Styles.Companion.topbar
import javafx.scene.layout.HBox
import tornadofx.Fragment
import tornadofx.addClass
import tornadofx.label
import tornadofx.textfield

class TopBar : Fragment() {
    override val root = HBox()

    init {
        with(root) {
            addClass(topbar)
            contentBox {
                label {
                    addClass(logoIcon, icon, medium)
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
