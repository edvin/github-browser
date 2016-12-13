package view

import app.Styles.Companion.icon
import app.Styles.Companion.logoIcon
import app.Styles.Companion.medium
import app.Styles.Companion.topbar
import tornadofx.*

class TopBar : Fragment() {
    override val root = hbox {
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
