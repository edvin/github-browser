package view

import app.Styles.Companion.content
import app.Styles.Companion.contentWrapper
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority.ALWAYS
import tornadofx.addClass
import tornadofx.hbox
import tornadofx.hboxConstraints
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

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

val ZonedDateTime.humanSince : String get() {
    val days = until(ZonedDateTime.now(), ChronoUnit.DAYS)
    if (days > 0) return "$days days ago"
    val hours = until(ZonedDateTime.now(), ChronoUnit.HOURS)
    if (hours > 0) return "$hours hours ago"
    val minutes = until(ZonedDateTime.now(), ChronoUnit.MINUTES)
    return if (minutes == 0L) "just now" else "$minutes minutes ago"
}
