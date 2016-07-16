package model

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.JsonModel
import tornadofx.jsonModel
import tornadofx.long
import tornadofx.string
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit.DAYS
import javax.json.JsonObject

class Issue : JsonModel {
    enum class State { open, closed, all }

    val number = SimpleLongProperty()
    val title = SimpleStringProperty()
    val user = SimpleObjectProperty<User>()
    val state = SimpleObjectProperty<State>()
    val created = SimpleObjectProperty<ZonedDateTime>()
    val updated = SimpleObjectProperty<ZonedDateTime>()

    override fun updateModel(json: JsonObject) {
        with(json) {
            number.value = long("number")
            title.value = string("title")
            user.value = jsonModel("user")
            state.value = State.valueOf(string("state")!!)
            created.value = ZonedDateTime.parse(getString("created_at"))
            updated.value = ZonedDateTime.parse(getString("updated_at"))
        }
    }

    val humanCreatedSince : String get() {
        return "${created.value.until(ZonedDateTime.now(), DAYS)} days ago"
    }
}