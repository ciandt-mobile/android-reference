package com.ciandt.androidreference.ui.publicGists


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ciandt.androidreference.R
import com.ciandt.androidreference.entity.gist.Gist
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_public_gists.*
import javax.inject.Inject

class PublicGistsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PublicGistsViewModel

    private val adapter = GistsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_public_gists, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupGistList()
        setupViewModel()
    }

    private fun setupGistList() {
        publicGistsList.also {
            it.layoutManager = LinearLayoutManager(context)
            it.hasFixedSize()
            it.adapter = adapter
        }
    }

    private fun setupViewModel() {
        viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(PublicGistsViewModel::class.java)

        viewModel.gists.observe(this, Observer(::update))

        viewModel.init()
    }

    private fun update(gists: Array<Gist>?) {
        gists?.let { adapter.update(it) }
    }
}