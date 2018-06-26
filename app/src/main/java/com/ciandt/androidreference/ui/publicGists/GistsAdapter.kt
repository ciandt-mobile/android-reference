package com.ciandt.androidreference.ui.publicGists

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ciandt.androidreference.R
import com.ciandt.androidreference.databinding.GistItemBinding
import com.ciandt.androidreference.entity.gist.Gist

class GistsAdapter() : RecyclerView.Adapter<GistViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater
    private var gists: Array<Gist> = emptyArray()

    fun update(gists: Array<Gist>) {
        this.gists = gists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {

        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding = DataBindingUtil.inflate<GistItemBinding>(
            layoutInflater,
            R.layout.gist_item,
            parent,
            false
        )

        return GistViewHolder(binding)
    }

    override fun getItemCount() = gists.size

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        holder.binding.gist = gists[position]
    }
}

class GistViewHolder(val binding: GistItemBinding) : RecyclerView.ViewHolder(binding.root)