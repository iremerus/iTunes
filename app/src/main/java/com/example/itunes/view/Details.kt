package com.example.itunes.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.itunes.databinding.DetailsLayoutBinding
import com.example.itunes.model.datamodel.ItunesModel
import com.google.gson.Gson

class Details: AppCompatActivity() {
    private lateinit var binding: DetailsLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.let {actionBar.setDisplayHomeAsUpEnabled(true)}

        val itunes = intent.getStringExtra("itunes")
        val new = Gson().fromJson(itunes, ItunesModel::class.java)
        binding.details = new
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
