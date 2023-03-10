package com.example.projectandroid.ui.fragment

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.projectandroid.R
import com.example.projectandroid.data.local.localdatasource.AgentEntity
import com.example.projectandroid.databinding.FragmentAgentDetailBinding
import com.example.projectandroid.ui.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_agent_detail.*

class AgentDetailFragment: Fragment(R.layout.fragment_agent_detail) {
    private val navigationArgs: AgentDetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, DetailViewModel.Factory(activity.application))[DetailViewModel::class.java]
    }

    private lateinit var agent: AgentEntity

    private var _binding: FragmentAgentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.slide_bottom)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

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
            context?.let {
                Glide.with(it).load(agent.displayIcon).into(agentIcon)
                Glide.with(it).load(agent.fullPortrait).into(portraitAgent)
                Glide.with(it).load(agent.background).into(backgroundImage)
                Glide.with(it).load(agent.abilityIcon).into(abilityIcon1)
                Glide.with(it).load(agent.abilityIcon2).into(abilityIcon2)
                Glide.with(it).load(agent.abilityIcon3).into(abilityIcon3)
                Glide.with(it).load(agent.abilityIcon4).into(abilityIcon4)
            }
            agentName.text = agent.displayName
            agentDesc.text = agent.description
            abilityName1.text = agent.abilityName
            abilityName2.text = agent.abilityName2
            abilityName3.text = agent.abilityName3
            abilityName4.text = agent.abilityName4
        }
    }
}