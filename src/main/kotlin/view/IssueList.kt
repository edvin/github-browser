package view

import javafx.scene.control.TableView
import javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY
import controller.GitHub
import model.Issue
import model.Issue.State.open
import tornadofx.View
import tornadofx.asyncItems
import tornadofx.column

class IssueList : View() {
    override val root = TableView<Issue>()
    val github : GitHub by inject()

    init {
        title = "Issues"

        with (root) {
            setPrefSize(800.0, 600.0)

            column("Title", Issue::title)
            column("User", Issue::user)
            column("State", Issue::state)

            columnResizePolicy = CONSTRAINED_RESIZE_POLICY

            asyncItems { github.listIssues(state = open) }
        }

    }

}