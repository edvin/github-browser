package controller

import model.Issue
import model.Repo
import model.RepoModel
import model.UserModel
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.toModel

class GitHub : Controller() {
    private val selectedUser: UserModel by inject()
    private val selectedRepo: RepoModel by inject()

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

        api.get("user").use {
            if (it.ok()) {
                selectedUser.item = it.one().toModel()
                return true
            }
            return false
        }
    }

}
