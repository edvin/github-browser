package view

import app.Styles
import app.Styles.Companion.bold
import app.Styles.Companion.commentIcon
import app.Styles.Companion.defaultSpacing
import app.Styles.Companion.h2
import app.Styles.Companion.icon
import app.Styles.Companion.openIssueIcon
import controller.GitHub
import javafx.geometry.Insets
import javafx.scene.control.ListView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Priority.ALWAYS
import model.Issue
import model.Issue.State.open
import tornadofx.*

class IssueList : View() {
    override val root = ListView<Issue>()
    val github: GitHub by inject()

    init {
        title = "Issues"

        with(root) {
            cellFormat {
                graphic = HBox().apply {
                    addClass(defaultSpacing)
                    hbox {
                        label() {
                            addClass(icon, openIssueIcon)
                            padding = Insets(10.0)
                        }
                    }
                    vbox {
                        addClass(defaultSpacing)
                        hboxConstraints { hGrow = ALWAYS }
                        label(it.title).addClass(h2, bold)
                        label("#${it.number.value} opened ${it.humanCreatedSince} by ${it.user.value}")
                    }
                    hbox {
                        addClass(defaultSpacing)
                        label().addClass(icon, commentIcon)
                        label("10")
                    }
                }
            }

            asyncItems { github.listIssues(state = open) }
        }

    }

}