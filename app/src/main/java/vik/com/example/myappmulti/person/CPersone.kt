package vik.com.example.myappmulti.person

import javax.security.auth.login.LoginException

class CPersone (
    var login: String,
    var password : String
)
    {
        var comments : MutableList<String> = mutableListOf()

        override fun toString(): String {
            return "name: $login "
        }

    }
