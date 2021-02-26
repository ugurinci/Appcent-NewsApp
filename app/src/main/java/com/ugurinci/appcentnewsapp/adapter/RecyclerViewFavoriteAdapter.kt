package com.ugurinci.appcentnewsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ugurinci.appcentnewsapp.R
import com.ugurinci.appcentnewsapp.model.Article
import com.ugurinci.appcentnewsapp.model.Favorites
import com.ugurinci.appcentnewsapp.view.FavoriteFragmentDirections
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewFavoriteAdapter(val listener: Listener) :
    RecyclerView.Adapter<RecyclerViewFavoriteAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(article: Article)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(article: Article, listener: Listener) {

            var titleP: String = ""
            var authorP: String = ""
            var publishedAtP: String = ""
            var contentP: String = ""
            var urlToImageP: String = ""
            var urlP: String = ""
            var source: String = ""
            var description: String = ""

            if (article.title != null) {
                titleP = article.title
            }

            if (article.author != null) {
                authorP = article.author
            }

            if (article.publishedAt != null) {
                publishedAtP = article.publishedAt
            }

            if (article.content != null) {
                contentP = article.content
            }

            if (article.urlToImage != null) {
                urlToImageP = article.urlToImage
            }

            if (article.url != null) {
                urlP = article.url
            }

            if (article.source.name != null) {
                source = article.source.name
            }

            if (article.description != null) {
                description = article.description
            }

            itemView.setOnClickListener {
                listener.onItemClick(article)
                Navigation.findNavController(it)
                    .navigate(
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(
                            titleP,
                            authorP,
                            publishedAtP,
                            contentP,
                            urlToImageP,
                            urlP,
                            source,
                            description
                        )
                    )
            }

            itemView.textViewTitle.text = article.title
            itemView.textViewDate.text = article.publishedAt
            itemView.textViewSource.text = article.source.name
            itemView.textViewDescription.text = article.description

            if (urlToImageP != "") {
                Picasso.get().load(urlToImageP).into(itemView.imageViewNews)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(Favorites.article[position], listener)
    }

    override fun getItemCount(): Int {
        return Favorites.article.size
    }
}