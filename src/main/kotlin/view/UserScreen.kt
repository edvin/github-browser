package view

import app.Styles
import app.Styles.Companion.bold
import app.Styles.Companion.borderLineColor
import app.Styles.Companion.darkTextColor
import app.Styles.Companion.defaultContentPadding
import app.Styles.Companion.defaultSpacing
import app.Styles.Companion.detail
import app.Styles.Companion.h1
import app.Styles.Companion.h2
import app.Styles.Companion.rowWrapper
import app.Styles.Companion.stat
import app.Styles.Companion.successButton
import app.Styles.Companion.userinfo
import app.Styles.Companion.userscreen
import controller.GitHub
import javafx.geometry.Insets
import javafx.scene.control.Alert.AlertType.WARNING
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority.ALWAYS
import javafx.scene.paint.Color.BLACK
import javafx.scene.paint.Color.TRANSPARENT
import model.Repo
import model.RepoModel
import model.UserModel
import tornadofx.*
import tornadofx.ViewTransition.Direction.LEFT

class UserScreen : View("GitHub Browser User Screen") {
    val github: GitHub by inject()
    val user: UserModel by inject()
    val selectedRepo: RepoModel by inject()

    override val root = borderpane {
        addClass(userscreen)
        top = vbox {
            addClass(rowWrapper)
            add(TopBar::class)
        }

        center = hbox {
            addClass(rowWrapper)
            userInfo()
            userDetail()
        }
    }

    fun HBox.userDetail() = vbox {
        addClass(detail, defaultSpacing, defaultContentPadding)
        hbox {
            label("Repositories") {
                addClass(h2)
                graphic = label().addClass(Styles.repoIcon, Styles.icon)
            }
            spacer()
            button("New", Label().addClass(Styles.repoIcon, Styles.icon)) {
                addClass(successButton)
                setOnAction {
                    alert(WARNING, "Not implemented", "New repo is not implemented in this demo.")
                }
            }
        }
        listview<Repo> {
            vboxConstraints { vGrow = ALWAYS }
            cellFormat {
                graphic = HBox().apply {
                    addClass(defaultSpacing)
                    vbox {
                        addClass(defaultSpacing)
                        hboxConstraints { hGrow = ALWAYS }
                        hyperlink(it.nameProperty) {
                            setOnAction { editRepo(item) }
                            addClass(h2, bold)
                        }
                        label(it.descriptionProperty)
                        label("Updated ${it.updated.humanSince}")
                    }
                    hbox {
                        addClass(defaultSpacing)
                        label().addClass(Styles.starIcon, Styles.icon)
                        label(it.stargazersCount.toString())
                        label().addClass(Styles.branchIcon, Styles.icon)
                        label(it.forksCount.toString())
                    }
                }
            }
            onUserSelect {
                editRepo(it)
            }
            whenDocked {
                asyncItems { github.listRepos() }
            }
        }

    }

    fun editRepo(repo: Repo) {
        selectedRepo.item = repo
        replaceWith(RepoScreen::class, ViewTransition.Slide(0.3.seconds, LEFT))
    }

    fun HBox.userInfo() = vbox {
        addClass(userinfo)
        addClass(defaultSpacing)
        imageview {
            imageProperty().bind(user.avatarUrl.objectBinding { Image(it, true) })
        }
        vbox {
            label(user.name).addClass(h1)
            label(user.login).addClass(h2)
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

            label(user.location) {
                textFill = BLACK
                graphicTextGap = 10.0
                graphic = label().addClass(Styles.locationIcon, Styles.icon)
            }
            label(user.blog) {
                textFill = BLACK
                graphicTextGap = 10.0
                graphic = label().addClass(Styles.linkIcon, Styles.icon)
            }
            label(user.joined) {
                textFill = BLACK
                graphicTextGap = 10.0
                graphic = label().addClass(Styles.clockIcon, Styles.icon)
            }
        }
        hbox {
            addClass(stat)
            vbox {
                label(user.followers)
                text("Followers") {
                    fill = darkTextColor
                }
            }
            vbox {
                label(user.following)
                text("Following") {
                    fill = darkTextColor
                }
            }
        }
    }
}


