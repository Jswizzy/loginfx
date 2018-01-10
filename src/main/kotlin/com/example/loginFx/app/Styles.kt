package com.example.loginFx.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val warning by cssclass()
        val login by cssclass()
        val loginFx by cssclass()
        val fx by cssclass()
    }

    init {
        form {
            backgroundColor = multi(Color.SLATEGREY)
        }
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        loginFx {
            padding = box(20.px)
            fontFamily = "Helvetica"
            fontWeight = FontWeight.BOLD
        }
        login {
            fill = Color.PURPLE
            fontSize = 20.px
        }
        fx {
            fill = Color.ORANGE
            fontSize = 28.px
        }
        warning {
            textFill = Color.RED
            fontWeight = FontWeight.BOLD
        }
    }
}