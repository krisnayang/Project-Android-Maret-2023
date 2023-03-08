package com.example.projectandroid.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.R
import com.example.projectandroid.data.local.model.Agent
import com.example.projectandroid.databinding.FragmentHomeBinding
import com.example.projectandroid.ui.adapter.AgentListAdapter
import com.example.projectandroid.ui.viewmodel.AgentViewModel

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel: AgentViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, AgentViewModel.AgentViewModelFactory(activity.application))[AgentViewModel::class.java]
    }

    private var viewModelAdapter: AgentListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.agent.observe(viewLifecycleOwner, Observer<List<Agent>> { agents ->
            agents?.apply {
                viewModelAdapter?.agents = agents
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModelAdapter = AgentListAdapter()
        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        return binding.root
    }

    class AgentClick(val block: (Agent) -> Unit) {
        fun onClick(video: Agent) = block(video)
    }
}
