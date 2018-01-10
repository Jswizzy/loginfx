package com.example.loginFx.controller

import com.example.loginFx.user.UserModel
import com.example.loginFx.view.LoginScreen
import com.example.loginFx.view.WelcomeScreen
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class LoginController: Controller() {
    val statusProperty = SimpleStringProperty("")
    var status by statusProperty

    val user: UserModel by inject()
    val api: Rest by inject()

    init {
        api.baseURI = "https://api.github.com/"
    }

    fun login(username: String, password: String) {
        runLater { status = "" }
        api.setBasicAuth(username, password)
        val response = api.get("user")
        val json = response.one()
        runLater {
            if (response.ok()) {
                user.item = json.toModel()
                find(LoginScreen::class).replaceWith(WelcomeScreen::class, sizeToScene = true, centerOnScreen = true)
            } else {
                status = json.string("message") ?: "Login Failed"
            }
        }

        println("Log in as $username with password $password")
    }

    fun logout() {
        user.item = null
        primaryStage.uiComponent<UIComponent>()?.replaceWith(LoginScreen::class, sizeToScene = true, centerOnScreen = true)
    }

}