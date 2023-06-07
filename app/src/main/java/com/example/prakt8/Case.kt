package com.example.prakt8

class Case (var name: String, var description: String) {

    override fun toString(): String {
        return "$name\n$description"
    }
}