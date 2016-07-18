package controller

import model.UserModel
import tornadofx.Controller

class ViewState : Controller() {
    val selectedUser = UserModel()
}
