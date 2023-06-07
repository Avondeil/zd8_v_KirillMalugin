package com.example.prakt8

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.provider.Settings.Secure.putString
import android.provider.Settings.System.putString
import android.view.Menu
import android.view.View
import android.widget.*

class GeneralActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<Case>
    private lateinit var nameText: EditText
    private lateinit var descriptionText: EditText
    private lateinit var cases: ArrayList<Case>
    private lateinit var listView: ListView
    private var selectedPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)

        nameText = findViewById(R.id.editText4)
        descriptionText = findViewById(R.id.editText5)
        listView = findViewById(R.id.list)
        cases = ArrayList()

        adapter = ArrayAdapter<Case>(this, android.R.layout.simple_list_item_1, cases)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            selectedPosition = position
        }

        val button = findViewById<ImageView>(R.id.button)
        val popupMenu = PopupMenu(this, button)
        popupMenu.menu.add(Menu.NONE, 1, Menu.NONE, "Удалить выбранный элемент")
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                1 -> {
                    if (selectedPosition != -1) {
                        cases.removeAt(selectedPosition)
                        adapter.notifyDataSetChanged()
                        selectedPosition = -1
                    }
                    true
                }
                else -> false
            }
        }

        button.setOnClickListener {
            popupMenu.show()
        }
    }


    fun addcase(view: View) {
        val name = nameText.text.toString()
        val description = descriptionText.text.toString()
        val case = Case(name, description)
        cases.add(case)
        adapter.notifyDataSetChanged()
        //ADD TO JSON
        val result = JSONHelper.exportToJSON(this, cases)
        if (result) {
            Toast.makeText(this, "Дело добавлено", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show()
        }
    }

    fun open(view: View) {
        cases = JSONHelper.importFromJSON(this) as ArrayList<Case>
        if (cases != null) {
            adapter.clear()
            adapter.addAll(cases)
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show()
        }
    }




}