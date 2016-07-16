package view

import javafx.scene.layout.BorderPane
import app.Styles.Companion.contentWrapper
import app.Styles.Companion.defaultContentPadding
import app.Styles.Companion.h2
import app.Styles.Companion.whiteBackground
import tornadofx.*

class CodeView : View() {
    override val root = BorderPane()

    init {
        with(root) {
            addClass(whiteBackground, defaultContentPadding)
            top {
                addClass(contentWrapper)
                hbox {
                    addClass(h2)
                    label("Lightweight JavaFX Framework for Kotlin")
                    label("-")
                    hyperlink("Edit")
                }
            }
        }
    }
}
