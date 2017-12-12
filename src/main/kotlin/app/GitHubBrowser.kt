package app

import javafx.stage.Stage
import tornadofx.*
import view.LoginScreen

class GitHubBrowser : App(LoginScreen::class, Styles::class) {
    override fun start(stage: Stage) {
        stage.width = 1060.0
        super.start(stage)
    }
}

fun main(args: Array<String>) {
    launch<GitHubBrowser>(args)
}