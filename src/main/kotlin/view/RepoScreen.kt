package view

import app.IssueTabActivated
import app.Styles
import app.Styles.Companion.codeIcon
import app.Styles.Companion.commentIcon
import app.Styles.Companion.defaultContentPadding
import app.Styles.Companion.defaultSpacing
import app.Styles.Companion.h1
import app.Styles.Companion.h2
import app.Styles.Companion.hContainer
import app.Styles.Companion.head
import app.Styles.Companion.icon
import app.Styles.Companion.issuelist
import app.Styles.Companion.issuesIcon
import app.Styles.Companion.lightBackground
import app.Styles.Companion.linkLook
import app.Styles.Companion.openIssueIcon
import app.Styles.Companion.pullRequestsIcon
import app.Styles.Companion.repoIcon
import app.Styles.Companion.rowWrapper
import app.Styles.Companion.settingsIcon
import app.Styles.Companion.successButton
import app.Styles.Companion.whiteBackground
import controller.GitHub
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import model.Issue
import model.RepoModel
import tornadofx.*
import tornadofx.ViewTransition.Direction.LEFT

class RepoScreen : View("TornadoFX GitHub Browser") {
    val repo: RepoModel by inject()

    override val root = borderpane {
        top = vbox {
            addClass(rowWrapper)
            this += TopBar::class
            repoHeading()
        }

        center = vbox {
            addClass(rowWrapper)
            repoTabs()
        }
    }

    fun VBox.repoHeading() = vbox {
        addClass(head)

        contentBox {
            addClass(hContainer)
            label().addClass(icon, repoIcon)
            hyperlink(repo.ownerLogin) {
                addClass(h1)
                setOnAction {
                    replaceWith(UserScreen::class, ViewTransition.Slide(0.3.seconds, LEFT))
                }
            }
            label("/").addClass(h1, linkLook)
            label(repo.name).addClass(h1, linkLook)
        }
    }

    fun VBox.repoTabs() = stackpane() {
        addClass(lightBackground)
        contentBox {
            tabpane {
                tab("Code") {
                    graphic = Label().addClass(icon, codeIcon)
                    content {
                        this += CodeView::class
                    }
                }
                tab("Issues") {
                    graphic = Label().addClass(icon, issuesIcon)
                    content {
                        this += IssueList::class
                    }
                    selectedProperty().onChange { tabActivated ->
                        // Load the issue list when the tab is selected
                        if (tabActivated == true)
                            fire(IssueTabActivated)
                    }
                    whenDocked {
                        // Make sure the issue list is refreshed if the View is reactivated
                        // and the Issues tab is already selected
                        if (isSelected)
                            fire(IssueTabActivated)
                    }
                }
                tab("Pull requests") {
                    graphic = Label().addClass(icon, pullRequestsIcon)
                    content {
                        label("Pull requests: Not implemented - submit a PR? :)").addClass(h2, defaultContentPadding)
                    }
                }
                tab("Settings") {
                    graphic = Label().addClass(icon, settingsIcon)
                    content {
                        label("Settings: Not implemented - submit a PR? :)").addClass(h2, defaultContentPadding)
                    }
                }

                tabs.forEach { it.isClosable = false }
            }
        }
    }
}

class IssueList : View("Issyes") {
    override val root = VBox().addClass(issuelist)
    val github: GitHub by inject()

    init {
        with(root) {
            addClass(whiteBackground, defaultContentPadding)
            hbox {
                alignment = Pos.CENTER_RIGHT
                addClass(defaultContentPadding)
                button("New issue") {
                    addClass(successButton)
                }
            }
            listview<Issue> {
                cellFormat {
                    graphic = HBox().apply {
                        addClass(defaultSpacing)
                        hbox {
                            label() {
                                addClass(icon, openIssueIcon)
                            }
                        }
                        vbox {
                            addClass(defaultSpacing)
                            hboxConstraints { hGrow = Priority.ALWAYS }
                            label(it.title).addClass(h2, Styles.bold)
                            label("#${it.number} opened ${it.created.humanSince} by ${it.user}")
                        }
                        hbox {
                            addClass(defaultSpacing)
                            label().addClass(icon, commentIcon)
                            label("${it.comments}")
                        }
                    }
                }

                subscribe<IssueTabActivated> {
                    runAsyncWithProgress {
                        github.listIssues(state = Issue.State.open)
                    } ui {
                        items = it
                    }
                }
            }
        }
    }
}


