package com.olderwold.catlist.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olderwold.catlist.R
import com.olderwold.catlist.data.CatApiImpl

class CatListActivity : AppCompatActivity() {
    private val viewModel by viewModels<CatListViewModel>() {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val catApi = CatApiImpl()
                return CatListViewModel(catApi) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val errorText = findViewById<TextView>(R.id.errorText)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val listAdapter = CatListAdapter()

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        viewModel.state.observe(this) { state ->
            when (state) {
                is CatListViewModel.State.Error -> {
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.VISIBLE
                    recyclerView.visibility = View.VISIBLE

                    errorText.text = state.value.message
                }
                CatListViewModel.State.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    errorText.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
                is CatListViewModel.State.Success -> {
                    progressBar.visibility = View.GONE
                    errorText.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

                    listAdapter.data = state.catList
                    listAdapter.notifyDataSetChanged()
                }
            }.required()
        }

        viewModel.load()
    }

    @Suppress("SimpleRedundantLet")
    private fun <T> T.required(): T {
        return let { it }
    }
}
