package view

import app.Styles.Companion.codeIcon
import app.Styles.Companion.h1
import app.Styles.Companion.head
import app.Styles.Companion.icon
import app.Styles.Companion.issuesIcon
import app.Styles.Companion.lightBackground
import app.Styles.Companion.pullRequestsIcon
import app.Styles.Companion.repoIcon
import app.Styles.Companion.rowWrapper
import app.Styles.Companion.settingsIcon
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import tornadofx.*

class RepoView : View() {
    override val root = BorderPane()

    init {
        title = "TornadoFX GitHub Browser"
        root.top {
            addClass(rowWrapper)
            this += TopBar::class
            this += RepoHeading::class
        }

        root.center {
            addClass(rowWrapper)
            this += RepoTabs::class
        }
    }
}

class RepoHeading : View() {
    override val root = VBox().addClass(head)

    init {
        root.contentBox {
            addClass(h1)
            label().addClass(icon, repoIcon)
            label("edvin")
            label("/")
            label("tornadofx")
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