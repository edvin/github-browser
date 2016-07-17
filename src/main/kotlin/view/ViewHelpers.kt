package view

import app.Styles.Companion.content
import app.Styles.Companion.contentWrapper
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority.ALWAYS
import tornadofx.addClass
import tornadofx.hbox
import tornadofx.hboxConstraints

/**
 * Add common wrapper elements used in most Views
 */
fun Pane.contentBox(op: HBox.() -> Unit) {
    hbox {
        addClass(contentWrapper)
        hbox {
            addClass(content)
            hboxConstraints { hGrow = ALWAYS }
            op()
        }
    }
}