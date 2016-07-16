package app

import javafx.stage.Stage
import view.RepoView
import tornadofx.App
import tornadofx.importStylesheet

class MyApp: App() {
    override val primaryView = RepoView::class

    init {
        importStylesheet(Styles::class)
    }

    override fun start(stage: Stage) {
        stage.width = 1060.0
        super.start(stage)
    }
}