package vik.com.example.myappmulti.model

import androidx.room.Entity

//@Entity
class ObjPersone (
    var login: String,
    var password : String
)
    {
        var comments : MutableList<String> = mutableListOf()

        override fun toString(): String {
            return "name: $login "
        }

    }
