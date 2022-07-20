package com.example.listviewandspinner

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.listviewandspinner.databinding.ActivityArrayAdapterBinding
import com.example.listviewandspinner.databinding.DialogAddCharacterBinding
import java.util.*

class ArrayAdapterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArrayAdapterBinding
    private lateinit var adapter: ArrayAdapter<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrayAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListWithArrayAdapter()
        binding.addButton.setOnClickListener { onAddPressed() }

    }

    private fun onAddPressed() {
        val dialogBinding = DialogAddCharacterBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Create character")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { d, which ->
                val name: String = dialogBinding.etCharacterName.text.toString()
                if (name.isNotBlank()) {
                    createCharacter(name)
                }
            }.create()
        dialog.show()
    }

    private fun setupListWithArrayAdapter() {
        val data = mutableListOf(
            Character(UUID.randomUUID().toString(), "Homo sapiens"),
            Character(UUID.randomUUID().toString(), "Homo erectus"),
            Character(UUID.randomUUID().toString(), "Homo habilis"),
            Character(UUID.randomUUID().toString(), "Homo ergaster"),
            Character(UUID.randomUUID().toString(), "Homo antecessor"),
            Character(UUID.randomUUID().toString(), "Homo denisovensis"),
            Character(UUID.randomUUID().toString(), "Homo naledi")
        )
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            data
        )

        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                deleteCharacter(it)
            }
        }
    }
        fun createCharacter(name: String) {
            val character = Character(
                id = UUID.randomUUID().toString(),
                name = name
            )
            adapter.add(character)

        }

        fun deleteCharacter(character: Character) {
            val listener = DialogInterface.OnClickListener { dialog, which ->
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    adapter.remove(character)
                }
            }
            val dialog = AlertDialog.Builder(this)
                .setTitle("Delete character?")
                .setMessage("Are you sure you want to delete character?")
                .setPositiveButton("Delete", listener)
                .setNegativeButton("Cancel", listener)
                .create()
            dialog.show()
        }

    class Character(
        val id: String,
        val name: String
    ) {
        override fun toString(): String {
            return name
        }
    }
}