package com.example.projectandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.projectandroid.R
import com.example.projectandroid.data.local.localdatasource.AgentEntity
import com.example.projectandroid.data.local.model.Agent
import com.example.projectandroid.databinding.FragmentAgentDetailBinding
import com.example.projectandroid.ui.viewmodel.AgentViewModel

class AgentDetailFragment: Fragment(R.layout.fragment_agent_detail) {
    private val navigationArgs: AgentDetailFragmentArgs by navArgs()

    private val viewModel: AgentViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, AgentViewModel.AgentViewModelFactory(activity.application))[AgentViewModel::class.java]
    }

    private lateinit var agent: AgentEntity

    private var _binding: FragmentAgentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        viewModel.retrieveAgent(id).observe(this.viewLifecycleOwner){
            agent = it
            bindForageable()
        }
    }

    private fun bindForageable() {
        binding.apply {
            context?.let { Glide.with(it).load(agent.displayIcon).into(imageAgent) }
            agentName.text = agent.displayName
        }
    }
}