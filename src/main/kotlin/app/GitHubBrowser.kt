package app

import javafx.application.Application
import javafx.stage.Stage
import tornadofx.App
import view.LoginScreen

class GitHubBrowser : App(LoginScreen::class, Styles::class) {
    override fun start(stage: Stage) {
        stage.width = 1060.0
        super.start(stage)
    }
}

fun main(args: Array<String>) {
    Application.launch(GitHubBrowser::class.java, *args)
}