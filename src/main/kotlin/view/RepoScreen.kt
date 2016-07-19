package view

import app.IssueEvent
import app.Styles.Companion.codeIcon
import app.Styles.Companion.h1
import app.Styles.Companion.hContainer
import app.Styles.Companion.head
import app.Styles.Companion.icon
import app.Styles.Companion.issuesIcon
import app.Styles.Companion.lightBackground
import app.Styles.Companion.pullRequestsIcon
import app.Styles.Companion.repoIcon
import app.Styles.Companion.rowWrapper
import app.Styles.Companion.settingsIcon
import controller.GitHub
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import tornadofx.*

class RepoScreen : View() {
    override val root = BorderPane()

    init {
        title = "TornadoFX GitHub Browser"

        with(root) {
            top {
                addClass(rowWrapper)
                this += TopBar::class
                this += RepoHeading::class
            }

            center {
                addClass(rowWrapper)
                this += RepoTabs::class
            }
        }
    }
}

class RepoHeading : View() {
    override val root = VBox().addClass(head)
    val github: GitHub by inject()

    init {
        root.contentBox {
            addClass(hContainer)
            label().addClass(icon, repoIcon)
            hyperlink() {
                textProperty().bind(github.selectedRepoProperty.stringBinding { it!!.owner.name })
                addClass(h1)
                setOnAction {
                    // TODO: Load user and set
                    //github.selectedUser = github.selectedRepo.owner
                    replaceWith(UserScreen::class, ViewTransition.SlideOut)
                }
            }
            label("/").addClass(h1)
            label() {
                addClass(h1)
                textProperty().bind(github.selectedRepoProperty.stringBinding { it!!.name.value })
            }
        }
    }
}

class RepoTabs : View() {
    override val root = StackPane()

    init {
        with(root) {
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
                        selectedProperty().onChange { tabActivated ->
                            if (tabActivated == true)
                                primaryStage.fireEvent(IssueEvent(IssueEvent.ISSUE_TAB_ACTIVATED))
                        }
                        content {
                            this += IssueList::class
                        }
                    }
                    tab("Pull requests") {
                        graphic = Label().addClass(icon, pullRequestsIcon)
                        content {

                        }
                    }
                    tab("Settings") {
                        graphic = Label().addClass(icon, settingsIcon)
                        content {

                        }
                    }

                    tabs.forEach { it.isClosable = false }
                }
            }
        }

    }
}