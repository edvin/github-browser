package model

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.JsonModel
import tornadofx.long
import tornadofx.string
import java.time.ZonedDateTime
import javax.json.JsonObject

class Repo : JsonModel {
    val name = SimpleStringProperty()
    val description = SimpleStringProperty()
    val forksCount = SimpleLongProperty()
    val stargazersCount = SimpleLongProperty()
    val watchersCount = SimpleLongProperty()
    val updated = SimpleObjectProperty<ZonedDateTime>()

    override fun updateModel(json: JsonObject) {
        with (json) {
            name.value = string("name")
            description.value = string("description")
            forksCount.value = long("forks_count")
            stargazersCount.value = long("stargazers_count")
            watchersCount.value = long("watchers_count")
            updated.value = ZonedDateTime.parse(getString("updated_at"))
        }
    }
}
