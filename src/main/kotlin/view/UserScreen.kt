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
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority.ALWAYS
import javafx.scene.layout.VBox
import javafx.scene.paint.Color.BLACK
import javafx.scene.paint.Color.TRANSPARENT
import model.Repo
import tornadofx.*
import java.time.format.DateTimeFormatter

class UserScreen : View() {
    override val root = BorderPane().addClass(userscreen)

    init {
        title = "GitHub Browser User Screen"
        with(root) {
            top = vbox {
                addClass(rowWrapper)
                this += TopBar::class
            }

            center = hbox {
                addClass(rowWrapper)
                this += UserInfo::class
                this += UserDetail::class
            }
        }
    }
}

class UserDetail : View() {
    override val root = VBox().addClass(detail, defaultSpacing, defaultContentPadding)
    val github: GitHub by inject()

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
                            label("Updated ${it.updated.value.humanSince}")
                        }
                        hbox {
                            addClass(defaultSpacing)
                            label().addClass(starIcon, icon)
                            label(it.stargazersCount.value.toString())
                            label().addClass(branchIcon, icon)
                            label(it.forksCount.value.toString())
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
    }

    private fun editRepo(repo: Repo) {
        github.selectedRepo = repo
        replaceWith(RepoScreen::class, ViewTransition.SlideIn)
    }
}

class UserInfo : View() {
    override val root = VBox().addClass(userinfo)
    val user = get(GitHub::selectedUser)

    init {
        with(root) {
            addClass(defaultSpacing)
            imageview {
                imageProperty().bind(user.avatarUrlProperty.objectBinding { Image(user.avatarUrl, true) })
            }
            vbox {
                label(user.nameProperty).addClass(h1)
                label(user.loginProperty).addClass(h2)
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

                label(user.locationProperty) {
                    textFill = BLACK
                    graphicTextGap = 10.0
                    graphic = label().addClass(locationIcon, icon)
                }
                label(user.blogProperty) {
                    textFill = BLACK
                    graphicTextGap = 10.0
                    graphic = label().addClass(linkIcon, icon)
                }
                label {
                    textFill = BLACK
                    graphicTextGap = 10.0
                    graphic = label().addClass(clockIcon, icon)
                    textProperty().bind(user.createdProperty.stringBinding { "Joined on ${it!!.format(DateTimeFormatter.ISO_LOCAL_DATE)}" })
                }
            }
            hbox {
                addClass(stat)
                vbox {
                    label().textProperty().bind(user.followersProperty.stringBinding { it.toString() })
                    text("Followers") {
                        fill = darkTextColor
                    }
                }
                vbox {
                    label().textProperty().bind(user.followingProperty.stringBinding { it.toString() })
                    text("Following") {
                        fill = darkTextColor
                    }
                }
            }
        }
    }
}