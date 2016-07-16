package model

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.JsonModel
import tornadofx.jsonModel
import tornadofx.string
import javax.json.JsonObject

class Issue : JsonModel {
    enum class State { open, closed, all }

    val title = SimpleStringProperty()
    val user = SimpleObjectProperty<User>()
    val state = SimpleObjectProperty<State>()

    override fun updateModel(json: JsonObject) {
        with(json) {
            title.value = string("title")
            user.value = jsonModel("user")
            state.value = State.valueOf(string("state")!!)
        }
    }

}