package com.example.juicerecipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class JuiceRecipeFragment : Fragment() {

    private lateinit var tvSubmittedData: TextView
    private val submittedDataList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_juice_recipe, container, false)

        val tvAbout: TextView = view.findViewById(R.id.tvAbout)
        tvAbout.text = getString(R.string.about_text)

        val spinner: Spinner = view.findViewById(R.id.spinnerJuice)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.juice_array, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val btnSubmit: Button = view.findViewById(R.id.btnSubmit)
        tvSubmittedData = view.findViewById(R.id.tvSubmittedData)

        btnSubmit.setOnClickListener {
            // Handle button click
            val selectedJuice = spinner.selectedItem.toString()
            val size = if (view.findViewById<RadioButton>(R.id.radioSmall).isChecked) "Small" else "Large"
            val ingredients = view.findViewById<EditText>(R.id.etIngredients).text.toString()
            val submittedData = "Juice: $selectedJuice, Size: $size, Ingredients: $ingredients"

            submittedDataList.add(submittedData)
            displaySubmittedData()
        }

        return view
    }

    private fun displaySubmittedData() {
        val displayText = submittedDataList.joinToString(separator = "\n\n")
        tvSubmittedData.text = "Submitted Data:\n$displayText"
    }
}