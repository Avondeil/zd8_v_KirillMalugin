package com.example.prakt8

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EditTextActivity : AppCompatActivity() {
    lateinit var redakt1text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        val selectedItemContent = intent.getStringExtra("selectedItemContent")
        redakt1text.text = selectedItemContent
    }

    fun delete(view: View) {}
}