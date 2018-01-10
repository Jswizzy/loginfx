package com.example.loginFx.view

import com.example.loginFx.app.Styles
import com.example.loginFx.controller.LoginController
import com.example.loginFx.user.UserModel
import javafx.geometry.Pos
import tornadofx.*

class WelcomeScreen : View("Welcome") {
    val loginController: LoginController by inject()
    val user: UserModel by inject()


    override val root = vbox(10) {
        setPrefSize(800.0, 600.0)
        alignment = Pos.CENTER

        label(user.name) {
            addClass(Styles.loginFx)
        }

        button("Logout").action(loginController::logout)
    }
}
