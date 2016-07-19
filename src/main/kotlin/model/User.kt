package model

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.ZonedDateTime
import javax.json.JsonObject

class User : JsonModel {
    val login = SimpleStringProperty()
    val name = SimpleStringProperty()
    val password = SimpleStringProperty()
    val avatarUrl = SimpleStringProperty()
    val location = SimpleStringProperty()
    val email = SimpleStringProperty()
    val blog = SimpleStringProperty()
    val followers = SimpleLongProperty()
    val following = SimpleLongProperty()
    val created = SimpleObjectProperty<ZonedDateTime>()

    override fun updateModel(json: JsonObject) {
        with(json) {
            login.value = string("login")
            name.value = string("name")
            avatarUrl.value = string("avatar_url")
            location.value = string("location")
            email.value = string("email")
            blog.value = string("blog")
            followers.value = long("followers")
            following.value = long("following")
            if (isNotNullOrNULL("created_at"))
                created.value = ZonedDateTime.parse(getString("created_at"))
        }
    }

    override fun toString() = login.value
}

class UserModel : ViewModel() {
    val userProperty = SimpleObjectProperty(User())
    var user by userProperty

    init {
        rebindOnChange(userProperty)
    }

    val login = bind { user.login }
    val name = bind { user.name }
    val password = bind { user.password }
    val avatarUrl = bind { user.avatarUrl }
    val location = bind { user.location }
    val email = bind { user.email }
    val blog = bind { user.blog }
    val followers = bind { user.followers }
    val following = bind { user.following }
    val created = bind { user.created }
}