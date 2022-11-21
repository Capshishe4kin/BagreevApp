package com.example.bagreev.presentation.recycler

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bagreev.R
import com.example.bagreev.core.App
import com.example.bagreev.data.model.DataObject
import com.example.bagreev.data.cloud.DataService
import com.example.bagreev.data.model.DataResult
import com.example.bagreev.databinding.FragmentRecyclerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerBinding
    private val viewModel: RecyclerViewModel by viewModels {
        RecyclerViewModel.provideFactory(
            (requireContext().applicationContext as App).repository,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gifs = mutableListOf<DataObject>()
        val adapter = GifsAdapter(requireContext(), gifs)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        setListeners(adapter, gifs)

        viewModel.getGifts {
            gifs.addAll(it.res)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setListeners(
        adapter: GifsAdapter,
        gifs: MutableList<DataObject>
    ) {
        adapter.setOnItemClickListener(object : GifsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                findNavController().navigate(
                    RecyclerFragmentDirections.actionRecyclerFragmentToDetailFragment(
                        gifs[position].images.ogImage.url
                    )
                )
            }
        })
    }

}