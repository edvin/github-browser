package view

import app.Styles.Companion.footer
import app.Styles.Companion.h1
import app.Styles.Companion.large
import app.Styles.Companion.loginScreen
import app.Styles.Companion.logoIcon
import app.Styles.Companion.newToGitHub
import app.Styles.Companion.successButton
import controller.GitHub
import javafx.geometry.Orientation.VERTICAL
import javafx.scene.control.Hyperlink
import javafx.scene.layout.VBox
import model.UserModel
import tornadofx.*
import tornadofx.FX.Companion.application

class LoginScreen : View() {
    override val root = VBox().addClass(loginScreen)
    val github : GitHub by inject()
    val model = UserModel()

    init {
        title = "GitHub Browser Login"

        with(root) {
            label() {
                addClass(logoIcon, large)
            }
            label("Sign in to GitHub").addClass(h1)
            form {
                fieldset(labelPosition = VERTICAL) {
                    field("Username or email address") {
                        textfield(model.login)
                    }
                    field("Password") {
                        passwordfield(model.password)
                    }.addForgotPasswordLink()
                }

                button("Sign in") {
                    addClass(successButton)
                    setOnAction {
                        login()
                    }
                }
            }
            hbox {
                addClass(newToGitHub)
                text("New to GitHub?")
                hyperlink("Create an account.") {
                    setOnAction {
                        application.hostServices.showDocument("https://github.com/join?source=login")
                    }
                }
            }
            hbox {
                addClass(footer)
                label("TornadoFX Showcase Application")
            }
        }
    }

    private fun login() {
        runAsync {
            println(github.login(model.login.value, model.password.value))
        }
    }

    fun Field.addForgotPasswordLink() {
        label.style { minWidth = 170.px }
        val link = Hyperlink("Forgot password?")
        link.isFocusTraversable = false
        link.style { fontSize = 12.px }
        labelContainer.add(link)
    }

}

