package model

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.ZonedDateTime
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

class UserModel : ViewModel() {
    var source = User()

    val login = bind { source.loginProperty }
    val name = bind { source.nameProperty }
    val password = bind { source.passwordProperty }
    val avatarUrl = bind { source.avatarUrlProperty }
    val location = bind { source.locationProperty }
    val email = bind { source.emailProperty }
    val blog = bind { source.blogProperty }
    val followers = bind { source.followersProperty }
    val following = bind { source.followingProperty }
    val created = bind { source.createdProperty }
}