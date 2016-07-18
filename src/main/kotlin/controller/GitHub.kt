package controller

import model.Issue
import model.Repo
import model.User
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.toModel

class GitHub : Controller() {
    private val api: Rest by inject()

    init {
        api.baseURI = "https://api.github.com/"
    }

    fun listIssues(username: String = "edvin", repo: String = "tornadofx", state: Issue.State)
            = api.get("repos/$username/$repo/issues?state=$state").list().toModel<Issue>()

    fun listRepos(username: String)
            = api.get("repos/$username").list().toModel<Repo>()

    fun login(username: String, password: String): Boolean {
        api.setBasicAuth(username, password)
        val result = api.get("user")
        val json = result.one()
        if (result.ok()) {
            get(ViewState::selectedUser).user = json.toModel()
            return true
        }
        return false
    }
}
