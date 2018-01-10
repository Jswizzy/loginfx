package com.example.loginFx.view

import com.example.loginFx.app.Styles
import com.example.loginFx.controller.LoginController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*


class LoginScreen : View("LoginFX") {
    val loginController: LoginController by inject()

    // only used internal with login screen so not going to create a model
    val model = ViewModel()
    val username = model.bind { SimpleStringProperty() }
    val password = model.bind { SimpleStringProperty() }

    override val root = form {

        textflow {
            addClass(Styles.loginFx)
            text("Login") {
                addClass(Styles.login)
            }
            text("FX") {
                addClass(Styles.fx)
            }
        }

        fieldset(labelPosition = Orientation.VERTICAL) {
            fieldset("Username") {
                textfield(username).required()
            }
            fieldset("Password") {
                passwordfield(password).required()
            }
            button("Log in") {
                enableWhen { model.valid }
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        loginController.login(username.value, password.value)
                    }
                }
            }
        }
        label(loginController.statusProperty) {
            addClass(Styles.warning)
        }

    }

    override fun onDock() {
        username.value = ""
        password.value = ""
        model.clearDecorators() // clears validation messages
    }
}

