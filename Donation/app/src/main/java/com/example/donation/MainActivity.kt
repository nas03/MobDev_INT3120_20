package com.example.donation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.donation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.minValue = 0
        numberPicker.maxValue = 1000
        numberPicker.value = 0
        var inputValue = findViewById<TextInputEditText>(R.id.inputValue)
        var totalDonation = 0
        val donateButton = findViewById<Button>(R.id.donateButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var donateText = findViewById<TextView>(R.id.totalDonation)
        numberPicker.setOnValueChangedListener { _, _, newValue ->
            inputValue.setText(newValue.toString())
        }
        donateButton.setOnClickListener {
            totalDonation += inputValue.text.toString().toInt()
            progressBar.progress = totalDonation
            donateText.text = "$$totalDonation"
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}