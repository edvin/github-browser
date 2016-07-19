package view

import app.Styles.Companion.borderLineColor
import app.Styles.Companion.clockIcon
import app.Styles.Companion.darkTextColor
import app.Styles.Companion.defaultContentPadding
import app.Styles.Companion.defaultSpacing
import app.Styles.Companion.detail
import app.Styles.Companion.h1
import app.Styles.Companion.h2
import app.Styles.Companion.icon
import app.Styles.Companion.linkIcon
import app.Styles.Companion.locationIcon
import app.Styles.Companion.repoIcon
import app.Styles.Companion.rowWrapper
import app.Styles.Companion.stat
import app.Styles.Companion.successButton
import app.Styles.Companion.userinfo
import app.Styles.Companion.userscreen
import controller.ViewState
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color.BLACK
import javafx.scene.paint.Color.TRANSPARENT
import model.Repo
import tornadofx.*
import java.time.format.DateTimeFormatter

class UserScreen : View() {
    override val root = BorderPane().addClass(userscreen)
    val model = get(ViewState::selectedUser)

    init {
        title = "GitHub Browser User Screen"
        with(root) {
            top = vbox {
                addClass(rowWrapper)
                this += TopBar::class
            }

            center = hbox {
                addClass(rowWrapper)
                hbox {
                    this += UserInfo::class
                    this += UserDetail::class
                }
            }
        }
    }
}

class UserDetail : View() {
    override val root = VBox().addClass(detail, defaultSpacing, defaultContentPadding)
    val model = get(ViewState::selectedUser)

    init {
        with(root) {
            hbox {
                label("Repositories") {
                    addClass(h2)
                    graphic = label().addClass(repoIcon, icon)
                }
                spacer()
                button("New", Label().addClass(repoIcon, icon)) {
                    addClass(successButton)
                    setOnAction {

                    }
                }
            }
            listview<Repo> {

            }
        }
    }
}

class UserInfo : View() {
    override val root = VBox().addClass(userinfo)
    val model = get(ViewState::selectedUser)

    init {
        with(root) {
            addClass(defaultSpacing)
            imageview {
                imageProperty().bind(model.avatarUrl.objectBinding { Image(model.avatarUrl.value, true) })
            }
            vbox {
                label(model.name).addClass(h1)
                label(model.login).addClass(h2)
            }
            hyperlink("Add a bio") {
                padding = Insets(0.0)
            }
            hbox {
                style {
                    borderColor += box(TRANSPARENT, TRANSPARENT, borderLineColor, TRANSPARENT)
                }
            }
            vbox {
                padding = Insets(10.0, 0.0, 10.0, 0.0)
                spacing = 6.0

                label(model.location) {
                    textFill = BLACK
                    graphicTextGap = 10.0
                    graphic = label().addClass(locationIcon, icon)
                }
                label(model.blog) {
                    textFill = BLACK
                    graphicTextGap = 10.0
                    graphic = label().addClass(linkIcon, icon)
                }
                label {
                    textFill = BLACK
                    graphicTextGap = 10.0
                    graphic = label().addClass(clockIcon, icon)
                    textProperty().bind(model.created.stringBinding { "Joined on ${this!!.format(DateTimeFormatter.ISO_LOCAL_DATE)}" })
                }
            }
            hbox {
                addClass(stat)
                vbox {
                    label().textProperty().bind(model.followers.stringBinding { toString() })
                    text("Followers") {
                        fill = darkTextColor
                    }
                }
                vbox {
                    label().textProperty().bind(model.following.stringBinding { toString() })
                    text("Following") {
                        fill = darkTextColor
                    }
                }
            }
        }
    }
}