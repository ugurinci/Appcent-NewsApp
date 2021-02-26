package com.ugurinci.appcentnewsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ugurinci.appcentnewsapp.R
import com.ugurinci.appcentnewsapp.adapter.RecyclerViewFavoriteAdapter
import com.ugurinci.appcentnewsapp.model.Article
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(), RecyclerViewFavoriteAdapter.Listener {

    lateinit var recyclerViewFavoriteAdapter: RecyclerViewFavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
        recyclerViewFavorite.layoutManager = layoutManager
        recyclerViewFavoriteAdapter = RecyclerViewFavoriteAdapter(this@FavoriteFragment)
        recyclerViewFavorite.adapter = recyclerViewFavoriteAdapter

        imageButtonFavorite_Home.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(FavoriteFragmentDirections.actionFavoriteFragmentToHomeFragment())
        }
    }

    override fun onItemClick(article: Article) {
        println("OK")
    }
}