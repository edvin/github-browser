package model

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.ZonedDateTime
import javax.json.JsonObject

class Repo : JsonModel {
    val ownerProperty = SimpleObjectProperty<User>()
    var owner by ownerProperty

    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val descriptionProperty = SimpleStringProperty()
    var description by descriptionProperty

    val forksCountProperty = SimpleLongProperty()
    var forksCount by forksCountProperty

    val stargazersCountProperty = SimpleLongProperty()
    var stargazersCount by stargazersCountProperty

    val watchersCountProperty = SimpleLongProperty()
    var watchersCount by watchersCountProperty

    val updatedProperty = SimpleObjectProperty<ZonedDateTime>()
    var updated by updatedProperty

    override fun updateModel(json: JsonObject) {
        with (json) {
            owner = jsonModel("owner")
            name = string("name")
            description = string("description")
            forksCount = getLong("forks_count")
            stargazersCount = getLong("stargazers_count")
            watchersCount = getLong("watchers_count")
            updated = ZonedDateTime.parse(getString("updated_at"))
        }
    }
}

class RepoModel : ViewModel() {
    var source = Repo()

    val owner = bind { source.ownerProperty }
    val name = bind { source.nameProperty }
    val description = bind { source.descriptionProperty }
    val forksCount = bind { source.forksCountProperty }
    val stargazersCount = bind { source.stargazersCountProperty }
    val watchersCount = bind { source.watchersCountProperty }
    val updated = bind { source.updatedProperty }

    // This StringBinding will always reflect the login name of the user owning the currently selected Repo
    val ownerLogin = owner.stringBinding { it!!.login }
}