package com.example.itunes.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.example.itunes.databinding.ActivityMainBinding
import com.example.itunes.model.api.ItunesApi
import com.example.itunes.model.api.JobServices
import com.example.itunes.model.datamodel.ItunesModel
import com.example.itunes.model.datamodel.SectionModel
import com.example.itunes.view.Details
import com.example.itunes.view.MainActivity
import com.example.itunes.view.getType
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(): BaseViewModel() {
    var itunes: MutableLiveData<Array<ItunesModel>>? = null
    private val itunesApi: JobServices = ItunesApi.getRetroInstance().create(JobServices::class.java)

    fun init(binding: ActivityMainBinding, activity: MainActivity) {
        setViewDataBinding(binding)
        getData()
    }

    fun getData() {
        itunes = MutableLiveData()
        GlobalScope.launch {
            val response = if(getType() == "music")itunesApi.getMusic()
            else if(getType() == "movie")itunesApi.getMovie()
            else if (getType() == "book")itunesApi.getBook()
            else if(getType() == "podcast")itunesApi.getPodcast()
            else itunesApi.getAll()

            val newItems = response.body() as SectionModel
            itunes!!.postValue(newItems.results)
        }
    }

    fun addData() {
        GlobalScope.launch {
            val response = if(getType() == "music")itunesApi.getMusic()
            else if(getType() == "movie")itunesApi.getMovie()
            else if (getType() == "book")itunesApi.getBook()
            else if(getType() == "podcast")itunesApi.getPodcast()
            else itunesApi.getAll()
            val newItems = response.body() as SectionModel
            itunes!!.postValue(itunes?.value?.plus(newItems.results))
        }
    }

    fun itunesClicked(itunes: ItunesModel, context: Context) {
        val intent = Intent(context, Details::class.java)
        intent.putExtra("itunes", Gson().toJson(itunes))
        context.startActivity(intent)
    }
}