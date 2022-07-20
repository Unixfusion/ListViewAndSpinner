package com.example.listviewandspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import com.example.listviewandspinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListViewSimple()

    }

//    private fun setupListViewSimpleGeneratedData() {
//        val data = (1..100).map {
//            mapOf(
//                KEY_TITLE to "Item # $it",
//                KEY_DESCRIPTION to "Description # $it"
//            )
//        }
//    }

    private fun setupListViewSimple() {
//        val data = listOf(
//            mapOf(
//                KEY_TITLE to "First title 111",
//                KEY_DESCRIPTION to "First key description"),
//            mapOf(
//                KEY_TITLE to "Second title 222",
//                KEY_DESCRIPTION to "Second key description"),
//            mapOf(
//                KEY_TITLE to "Third title 33",
//                KEY_DESCRIPTION to "Third key description")
//            )
        val data = (1..100).map {
            mapOf(
                KEY_TITLE to "Item # $it",
                KEY_DESCRIPTION to "Description # $it"
            )
        }

        val adapter = SimpleAdapter(
            //context
            this,
            //data to be displayed
            data,
            //maquette for each element of list
            android.R.layout.simple_list_item_2,
            //keys of data
            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
            // id's of components getting from simple_list_item_1
        intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listView.adapter = adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItemTitle = data[position][KEY_TITLE]
            val selectedItemDescription = data[position][KEY_DESCRIPTION]

            val dialog = AlertDialog.Builder(this).
                setTitle(selectedItemTitle)
                .setMessage(getString(R.string.item_selected_message, selectedItemDescription))
                .setPositiveButton("OK") { _, _ ->}
                .create()
            dialog.show()
        }
    }

    companion object {
        @JvmStatic val KEY_TITLE = "KEY_TITLE"
        @JvmStatic val KEY_DESCRIPTION = "KEY_DESCRIPTION"
    }
}