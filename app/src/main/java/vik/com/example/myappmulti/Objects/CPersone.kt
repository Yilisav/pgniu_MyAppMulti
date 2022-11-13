package vik.com.example.myappmulti.Objects

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
