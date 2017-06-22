package view

import app.Styles
import app.Styles.Companion.topbar
import tornadofx.*

class TopBar : Fragment() {
    override val root = hbox {
        addClass(topbar)
        contentBox {
            label {
                addClass(Styles.logoIcon, Styles.icon, Styles.medium)
            }
            label("GitHub Browser")
            textfield {
                prefColumnCount = 20
                promptText = "Search"
            }
        }
    }
}
