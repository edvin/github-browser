package controller

import model.Issue
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.toModel

class GitHub : Controller() {
    val api : Rest by inject()

    init {
        api.baseURI = "https://api.github.com/"
    }

    fun listIssues(username: String = "edvin", repo: String = "tornadofx", state: Issue.State)
            = api.get("repos/$username/$repo/issues?state=$state").list().toModel<Issue>()

    fun login(username: String, password: String): Boolean {
        api.setBasicAuth(username, password)
        return api.get("user").consume().ok()
    }
}
