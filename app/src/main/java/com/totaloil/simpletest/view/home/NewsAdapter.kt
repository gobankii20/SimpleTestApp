package com.totaloil.simpletest.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.totaloil.simpletest.R
import com.totaloil.simpletest.data.remote.model.response.DataListNews
import com.totaloil.simpletest.databinding.ItemRowNewsBinding
import com.totaloil.simpletest.utils.SingleLiveData

class NewsAdapter(
    private val mListNews: ArrayList<DataListNews>,
    private val onItemClickListener: SingleLiveData<DataListNews>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_row_news,
                parent,
                false
            ) as ItemRowNewsBinding

        return ViewHolderBinding(binding)
    }

    override fun getItemCount(): Int {
        return mListNews.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderBinding).apply {
            binding.dataResponse = mListNews[position]

            binding.root.setOnClickListener {
                onItemClickListener.value = mListNews[position]
            }
        }
    }

    class ViewHolderBinding(val binding: ItemRowNewsBinding) : RecyclerView.ViewHolder(binding.root)

}