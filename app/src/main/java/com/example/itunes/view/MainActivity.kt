package com.example.itunes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunes.databinding.ActivityMainBinding
import com.example.itunes.model.datamodel.ItunesModel
import com.example.itunes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

private var type = "all"
private var term = "all"
private var page = 1

class MainActivity : AppCompatActivity() {
    private lateinit var itunesAdapter: ItunesAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        rv_itunes.layoutManager = layoutManager

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.init(binding, this)
        binding.viewModel = viewModel
        itunesAdapter = ItunesAdapter(emptyArray(), viewModel, this)
        rv_itunes.adapter = itunesAdapter
        searchView = sv_search

        btn_book.setOnClickListener(){
            page = 1
            type = "book"
            viewModel.getData()
            observeData()
        }
        btn_movie.setOnClickListener(){
            page = 1
            type="movie"
            viewModel.getData()
            observeData()
        }
        btn_music.setOnClickListener(){
            page = 1
            type="music"
            viewModel.getData()
            observeData()
        }
        btn_podcast.setOnClickListener(){
            page = 1
            type="podcast"
            viewModel.getData()
            observeData()
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                term = query!!
                page = 1
                viewModel.getData()
                observeData()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                term = newText!!
                return false
            }
        })

        var total = 0
        var loading = false
        rv_itunes.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visible = layoutManager.childCount
                    val past = layoutManager.findFirstCompletelyVisibleItemPosition()
                    total = itunesAdapter.itemCount
                    if ((visible + past > total) && !loading) {
                        loading = true
                        page += 1
                        viewModel.addData()
                    }
                }
                if (total < itunesAdapter.itemCount){
                    loading = false
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        term?.let { observeData() }
    }

    private fun observeData() {
        val observer =
            Observer<Array<ItunesModel>> { itunes -> itunesAdapter.updateData(itunes) }
        viewModel.itunes?.observe(this, observer)
    }
}

fun getTerm(): String {
    return term
}
fun getType(): String{
    return type
}
fun getLimit(): Int{
    return page * 20
}

