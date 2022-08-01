package com.example.videoplayer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.R
import com.example.videoplayer.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(){

    companion object {
        fun newInstance() = MainFragment()
    }

    //private val mainViewModel by viewModels<MainViewModel>()
    val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var adapter: MainAdapter

    // Data binding
    private var _binding: MainFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        _binding = DataBindingUtil.bind(view) // For DataBinding
        initUI()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Use viewLifecycleOwner instead of this: https://www.gushiciku.cn/pl/gzt6/zh-tw
        mainViewModel.newsListLiveData.observe(viewLifecycleOwner,
            Observer {
                adapter.notifyDataSetChanged()
            })


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun initUI() {
        adapter = MainAdapter(mainViewModel)
        binding.mainList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        binding.mainList.adapter = adapter
    }


}