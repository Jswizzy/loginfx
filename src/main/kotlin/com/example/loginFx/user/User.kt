package com.example.loginFx.user

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class User: JsonModel {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name")
        }
    }
}

class UserModel : ItemViewModel<User>() {
    val name = bind(User::nameProperty)
}
