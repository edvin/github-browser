package view

import app.Styles.Companion.bold
import app.Styles.Companion.borderLineColor
import app.Styles.Companion.branchIcon
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
import app.Styles.Companion.starIcon
import app.Styles.Companion.stat
import app.Styles.Companion.successButton
import app.Styles.Companion.userinfo
import app.Styles.Companion.userscreen
import controller.GitHub
import javafx.geometry.Insets
import javafx.scene.control.Alert.AlertType.WARNING
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
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
    override val root = BorderPane().addClass(userscreen)
    val github: GitHub by inject()
    val user: UserModel by inject()
    val selectedRepo: RepoModel by inject()

    init {
        with(root) {
            top = vbox {
                addClass(rowWrapper)
                this += TopBar::class
            }

            center = hbox {
                addClass(rowWrapper)
                userInfo()
                userDetail()
            }
        }
    }

    fun HBox.userDetail() = vbox {
        addClass(detail, defaultSpacing, defaultContentPadding)
        hbox {
            label("Repositories") {
                addClass(h2)
                graphic = label().addClass(repoIcon, icon)
            }
            spacer()
            button("New", Label().addClass(repoIcon, icon)) {
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
                        hyperlink(it.name) {
                            setOnAction { editRepo(item) }
                            addClass(h2, bold)
                        }
                        label(it.description)
                        label("Updated ${it.updated.humanSince}")
                    }
                    hbox {
                        addClass(defaultSpacing)
                        label().addClass(starIcon, icon)
                        label(it.stargazersCount.toString())
                        label().addClass(branchIcon, icon)
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
                graphic = label().addClass(locationIcon, icon)
            }
            label(user.blog) {
                textFill = BLACK
                graphicTextGap = 10.0
                graphic = label().addClass(linkIcon, icon)
            }
            label(user.joined) {
                textFill = BLACK
                graphicTextGap = 10.0
                graphic = label().addClass(clockIcon, icon)
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


