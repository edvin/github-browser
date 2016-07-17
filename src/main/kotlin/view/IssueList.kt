package view

import app.IssueEvent
import app.Styles
import app.Styles.Companion.bold
import app.Styles.Companion.commentIcon
import app.Styles.Companion.defaultContentPadding
import app.Styles.Companion.defaultSpacing
import app.Styles.Companion.h2
import app.Styles.Companion.icon
import app.Styles.Companion.issuelist
import app.Styles.Companion.openIssueIcon
import app.Styles.Companion.whiteBackground
import controller.GitHub
import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority.ALWAYS
import javafx.scene.layout.VBox
import model.Issue
import model.Issue.State.open
import tornadofx.*

class IssueList : View() {
    override val root = VBox().addClass(issuelist)
    val github: GitHub by inject()

    init {
        title = "Issues"

        with(root) {
            addClass(whiteBackground, defaultContentPadding)
            hbox {
                alignment = Pos.CENTER_RIGHT
                addClass(defaultContentPadding)
                button("New issue") {
                    addClass(Styles.successButton)
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

                primaryStage.addEventFilter(IssueEvent.ISSUE_TAB_ACTIVATED) {
                    runAsynWithProgress {
                        github.listIssues(state = open)
                    } ui {
                        items = it
                    }
                }
            }
        }
    }
}