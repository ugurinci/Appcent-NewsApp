package com.ugurinci.appcentnewsapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso
import com.ugurinci.appcentnewsapp.R
import com.ugurinci.appcentnewsapp.model.Article
import com.ugurinci.appcentnewsapp.model.Favorites
import com.ugurinci.appcentnewsapp.model.Source
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val url = DetailFragmentArgs.fromBundle(it).url

            val author = DetailFragmentArgs.fromBundle(it).author
            val content = DetailFragmentArgs.fromBundle(it).content
            val publishedAt = DetailFragmentArgs.fromBundle(it).publishedAt
            val title = DetailFragmentArgs.fromBundle(it).title

            val urlToImage = DetailFragmentArgs.fromBundle(it).urlToImage

            val source = Source("", DetailFragmentArgs.fromBundle(it).source)
            val description = DetailFragmentArgs.fromBundle(it).description

            textViewDetail_Author.text = DetailFragmentArgs.fromBundle(it).author
            textViewDetail_Content.text = DetailFragmentArgs.fromBundle(it).content
            textViewDetail_PublishedAt.text = DetailFragmentArgs.fromBundle(it).publishedAt
            textViewDetail_Title.text = DetailFragmentArgs.fromBundle(it).title

            if (DetailFragmentArgs.fromBundle(it).urlToImage != "") {
                Picasso.get().load(DetailFragmentArgs.fromBundle(it).urlToImage)
                    .into(imageViewDetail)
            }

            if (url == null) {
                buttonDetail_NewsSource.visibility = View.INVISIBLE
            } else {
                buttonDetail_NewsSource.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(DetailFragmentDirections.actionDetailFragmentToWebFragment(url))
                }
            }

            imageButtonDetail_Favorite.setOnClickListener {
                Favorites.article.add(
                    Article(
                        source,
                        author,
                        title,
                        description,
                        url,
                        urlToImage,
                        publishedAt,
                        content
                    )
                )
            }

            imageButtonDetail_Share.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}