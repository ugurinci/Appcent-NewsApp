package com.ugurinci.appcentnewsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.ugurinci.appcentnewsapp.R
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.row_layout.view.*

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
            val author = DetailFragmentArgs.fromBundle(it).author
            val content = DetailFragmentArgs.fromBundle(it).content
            val publishedAt = DetailFragmentArgs.fromBundle(it).publishedAt
            val title = DetailFragmentArgs.fromBundle(it).title
            val url = DetailFragmentArgs.fromBundle(it).url
            val urlToImage = DetailFragmentArgs.fromBundle(it).urlToImage

            textViewDetail_Author.text = author
            textViewDetail_Content.text = content
            textViewDetail_PublishedAt.text = publishedAt
            textViewDetail_Title.text = title

            if (urlToImage != "") {
                Picasso.get().load(urlToImage).into(imageViewDetail)
            }
        }
    }
}