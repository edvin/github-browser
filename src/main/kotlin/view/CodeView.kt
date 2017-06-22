package view

import app.Styles
import app.Styles.Companion.black
import app.Styles.Companion.bold
import app.Styles.Companion.codeview
import app.Styles.Companion.defaultContentPadding
import app.Styles.Companion.defaultSpacing
import app.Styles.Companion.h2
import app.Styles.Companion.stat
import app.Styles.Companion.statsbar
import app.Styles.Companion.whiteBackground
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority.ALWAYS
import model.RepoModel
import tornadofx.*

class CodeView : View() {
    val repo: RepoModel by inject()

    override val root = borderpane {
        addClass(codeview, whiteBackground, defaultContentPadding)
        top = vbox {
            addClass(defaultSpacing)
            hbox {
                addClass(h2)
                label(repo.description)
                label(" - ")
                hyperlink("Edit")
            }
            statsbar()
        }
    }

    fun Pane.statsbar() = hbox {
        addClass(statsbar)
        hbox {
            addClass(stat)
            label().addClass(Styles.icon, Styles.historyIcon)
            label("757").addClass(bold, black)
            label("Commits")
        }
        hbox {
            addClass(stat)
            label().addClass(Styles.icon, Styles.branchIcon)
            label("1").addClass(bold, black)
            label("Branch")
        }
        hbox {
            addClass(stat)
            label().addClass(Styles.icon, Styles.releasesIcon)
            label("16").addClass(bold, black)
            label("Releases")
        }
        hbox {
            addClass(stat)
            label().addClass(Styles.icon, Styles.contributorsIcon)
            label("8").addClass(bold, black)
            label("Contributors")
        }
        children.forEach { it.hboxConstraints { hGrow = ALWAYS } }
    }
}