package model

import javafx.beans.property.SimpleStringProperty
import tornadofx.JsonModel
import tornadofx.string
import javax.json.JsonObject

class User : JsonModel {
    val login = SimpleStringProperty()

    override fun updateModel(json: JsonObject) {
        with(json) {
            login.value = string("login")
        }
    }

    override fun toString() = login.value
}
