package com.example.loginFx.app

import com.example.loginFx.view.LoginScreen
import tornadofx.*

class MyApp: App(LoginScreen::class, Styles::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}