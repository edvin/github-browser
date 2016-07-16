package model

import javafx.beans.property.SimpleStringProperty
import tornadofx.JsonModel
import tornadofx.string
import javax.json.JsonObject

class User : JsonModel {
    val login = SimpleStringProperty()
    val avatarUrl = SimpleStringProperty()

    override fun updateModel(json: JsonObject) {
        with(json) {
            login.value = string("login")
            avatarUrl.value = string("avatar_url")
        }
    }

    override fun toString() = login.value
}
