package view

import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority.ALWAYS
import app.Styles.Companion.content
import app.Styles.Companion.contentWrapper
import controller.GitHub
import javafx.application.Platform
import javafx.concurrent.Task
import javafx.scene.Node
import javafx.scene.control.ProgressIndicator
import model.Issue
import tornadofx.*

/**
 * Add common wrapper elements used in most Views
 */
fun Pane.contentBox(op: HBox.() -> Unit) {
    hbox {
        addClass(contentWrapper)
        hbox {
            addClass(content)
            hboxConstraints { hGrow = ALWAYS }
            op()
        }
    }
}

fun <T> Node.runAsynWithProgress(op: () -> T): Task<T> {
    val progress = ProgressIndicator()
    progress.setPrefSize(boundsInParent.width, boundsInParent.height)
    val children = parent.getChildList()
    val index = children.indexOf(this)
    children.add(index, progress)
    removeFromParent()
    return task {
        val result = op()
        Platform.runLater {
            children.add(index, this)
            progress.removeFromParent()
        }
        result
    }
}