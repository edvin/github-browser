package controller

import model.*
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.rebind
import tornadofx.toModel

class GitHub : Controller() {
    var selectedUser = UserModel()
    val selectedRepo = RepoModel()

    private val api: Rest by inject()

    init {
        api.baseURI = "https://api.github.com/"
    }

    fun listIssues(username: String = selectedUser.login.value, repo: String = selectedRepo.name.value, state: Issue.State)
            = api.get("repos/$username/$repo/issues?state=$state").list().toModel<Issue>()

    fun listRepos(username: String = selectedUser.login.value)
            = api.get("users/$username/repos").list().toModel<Repo>()

    fun login(username: String, password: String): Boolean {
        api.setBasicAuth(username, password)
        val result = api.get("user")
        if (result.ok()) {
            selectUser(result.one().toModel())
            return true
        }
        result.consume()
        return false
    }

    fun selectRepo(repo: Repo) {
        selectedRepo.rebind { source = repo}
    }

    fun selectUser(user: User) {
        selectedUser.rebind { source = user }
    }
}
