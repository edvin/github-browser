package model

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.json.JsonObject

class User : JsonModel {
    val loginProperty = SimpleStringProperty()
    var login: String by loginProperty

    val nameProperty = SimpleStringProperty()
    var name: String? by nameProperty

    val passwordProperty = SimpleStringProperty()
    var password: String? by passwordProperty

    val avatarUrlProperty = SimpleStringProperty()
    var avatarUrl: String? by avatarUrlProperty

    val locationProperty = SimpleStringProperty()
    var location: String? by locationProperty

    val emailProperty = SimpleStringProperty()
    var email: String? by emailProperty

    val blogProperty = SimpleStringProperty()
    var blog: String? by blogProperty

    val followersProperty = SimpleLongProperty()
    var followers: Long? by followersProperty

    val followingProperty = SimpleLongProperty()
    var following: Long? by followingProperty

    val createdProperty = SimpleObjectProperty<ZonedDateTime>()
    var created: ZonedDateTime? by createdProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            login = getString("login")
            name = string("name")
            avatarUrl = string("avatar_url")
            location = string("location")
            email = string("email")
            blog = string("blog")
            followers = long("followers")
            following = long("following")
            if (isNotNullOrNULL("created_at"))
                created = ZonedDateTime.parse(getString("created_at"))
        }
    }

    override fun toString() = login
}

class UserModel : ItemViewModel<User>() {
    val login = bind { item?.loginProperty }
    val name = bind { item?.nameProperty }
    val password = bind { item?.passwordProperty }
    val avatarUrl = bind { item?.avatarUrlProperty }
    val location = bind { item?.locationProperty }
    val email = bind { item?.emailProperty }
    val blog = bind { item?.blogProperty }
    val followers = bind { item?.followersProperty }
    val following = bind { item?.followingProperty }
    val created = bind { item?.createdProperty }

    val joined = created.stringBinding { "Joined on ${it?.format(DateTimeFormatter.ISO_LOCAL_DATE)}" }
}