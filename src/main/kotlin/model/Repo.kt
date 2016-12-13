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

class RepoModel : ItemViewModel<Repo>() {
    val owner = bind { item?.ownerProperty }
    val name = bind { item?.nameProperty }
    val description = bind { item?.descriptionProperty }
    val forksCount = bind { item?.forksCountProperty }
    val stargazersCount = bind { item?.stargazersCountProperty }
    val watchersCount = bind { item?.watchersCountProperty }
    val updated = bind { item?.updatedProperty }

    // This StringBinding will always reflect the login name of the user owning the currently selected Repo
    val ownerLogin = owner.stringBinding { it?.login }
}