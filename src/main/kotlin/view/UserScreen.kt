package view

import app.Styles
import controller.ViewState
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.*

class UserScreen : View() {
    override val root = BorderPane()
    val model = get(ViewState::selectedUser)

    init {
        with(root) {
            top {
                addClass(Styles.rowWrapper)
                this += TopBar::class
            }

            center {
                addClass(Styles.rowWrapper)
                hbox {
                    this += UserInfo::class
                }
            }
        }
    }
}

class UserInfo : View() {
    override val root = VBox()
    val model = get(ViewState::selectedUser)

    init {
        with(root) {
            imageview {
                imageProperty().bind(
                        model.avatarUrl.objectBinding { Image(model.avatarUrl.value, true) }
                )
            }
        }
    }
}