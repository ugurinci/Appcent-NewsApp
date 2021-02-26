package com.ugurinci.appcentnewsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ugurinci.appcentnewsapp.R
import com.ugurinci.appcentnewsapp.adapter.RecyclerViewHomeAdapter
import com.ugurinci.appcentnewsapp.model.Article
import com.ugurinci.appcentnewsapp.model.News
import com.ugurinci.appcentnewsapp.service.NewsAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.row_layout.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment(), RecyclerViewHomeAdapter.Listener {

    val BASE_URL = "https://newsapi.org/v2/"
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var recyclerViewHomeAdapter: RecyclerViewHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable = CompositeDisposable()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
        recyclerViewHome.layoutManager = layoutManager
        loadData()

        imageButtonHome_Favorite.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(HomeFragmentDirections.actionHomeFragmentToFavoriteFragment())
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    loadSearchData(it, "1")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun loadSearchData(query: String, page: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(NewsAPI::class.java)
        compositeDisposable.add(
            retrofit.searchData(query, page, "90de41f9ae7745cda1b94c5e77fb5a76")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(NewsAPI::class.java)
        compositeDisposable.add(
            retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    fun handleResponse(news: News) {
        recyclerViewHomeAdapter = RecyclerViewHomeAdapter(news.articles, this@HomeFragment)
        recyclerViewHome.adapter = recyclerViewHomeAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onItemClick(article: Article) {
        println("OK")
    }

}