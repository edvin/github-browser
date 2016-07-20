package model

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.ZonedDateTime
import javax.json.JsonObject

class Issue : JsonModel {
    enum class State { open, closed, all }

    val numberProperty = SimpleLongProperty()
    var number by numberProperty

    val titleProperty = SimpleStringProperty()
    var title by titleProperty

    val userProperty = SimpleObjectProperty<User>()
    var user by userProperty

    val stateProperty = SimpleObjectProperty<State>()
    var state by stateProperty

    val createdProperty = SimpleObjectProperty<ZonedDateTime>()
    var created by createdProperty

    val updatedProperty = SimpleObjectProperty<ZonedDateTime>()
    var updated by updatedProperty

    val commentsProperty = SimpleLongProperty()
    var comments by commentsProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            number = getLong("number")
            title = string("title")
            user = jsonModel("user")
            state = State.valueOf(string("state")!!)
            created = ZonedDateTime.parse(getString("created_at"))
            updated = ZonedDateTime.parse(getString("updated_at"))
            comments = getLong("comments")
        }
    }

}