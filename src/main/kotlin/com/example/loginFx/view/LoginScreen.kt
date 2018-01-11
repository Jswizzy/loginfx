package com.example.loginFx.view

import com.example.loginFx.app.Styles
import com.example.loginFx.controller.LoginController
import com.example.loginFx.service.decrypt
import com.example.loginFx.service.encrypt
import com.example.loginFx.service.encryption
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import se.simbio.encryption.Encryption
import tornadofx.*
import java.security.SecureRandom


class LoginScreen : View("LoginFX") {
    val loginController: LoginController by inject()

    // only used internal with login screen so not going to create a model
    val model = ViewModel()
    val username = model.bind { SimpleStringProperty() }
    val password = model.bind { SimpleStringProperty() }

    private val encryption: Encryption by lazy {
        val e = encryption(config.string("key"), config.string("salt"))
        e
    }

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
                    } ui { success ->
                        if (success) {
                            val e = encryption
                            with(config) {
                                set("username" to (username.value).encrypt(e))
                                set("password" to (password.value).encrypt(e))
                                save()
                            }
                        }
                    }
                }
            }
        }
        label(loginController.statusProperty) {
            addClass(Styles.warning)
        }

    }

    override fun onDock() {
        val e = encryption
        username.value = config.string("username", "").decrypt(e)
        password.value = config.string("password", "").decrypt(e)
        model.clearDecorators() // clears validation messages
    }

    init {
        // use a token instead but this was for learning
        with(config) {
            computeIfAbsent("key", { SecureRandom.getInstance("SHA1PRNG").nextInt().toString() })
            computeIfAbsent("salt", { SecureRandom.getInstance("SHA1PRNG").nextInt().toString() })
            save()
        }
    }
}

