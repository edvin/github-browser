package model

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.JsonModel
import tornadofx.jsonModel
import tornadofx.long
import tornadofx.string
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit.*
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
        val days = created.value.until(ZonedDateTime.now(), DAYS)
        if (days > 0) return "$days days ago"
        val hours = created.value.until(ZonedDateTime.now(), HOURS)
        if (hours > 0) return "$hours hours ago"
        val minutes = created.value.until(ZonedDateTime.now(), MINUTES)
        return if (minutes == 0L) "just now" else "$minutes minutes ago"
    }
}