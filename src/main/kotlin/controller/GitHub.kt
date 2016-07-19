package controller

import model.Issue
import model.Repo
import model.UserModel
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.toModel

class GitHub : Controller() {
    val selectedUser = UserModel()
    private val api: Rest by inject()

    init {
        api.baseURI = "https://api.github.com/"
    }

    fun listIssues(username: String = "edvin", repo: String = "tornadofx", state: Issue.State)
            = api.get("repos/$username/$repo/issues?state=$state").list().toModel<Issue>()

    fun listRepos(username: String = selectedUser.login.value)
            = api.get("users/$username/repos").list().toModel<Repo>()

    fun login(username: String, password: String): Boolean {
        api.setBasicAuth(username, password)
        val result = api.get("user")
        val json = result.one()
        if (result.ok()) {
            selectedUser.user = json.toModel()
            return true
        }
        return false
    }
}
