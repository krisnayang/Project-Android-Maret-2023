package com.example.projectandroid.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.R
import com.example.projectandroid.databinding.FragmentHomeBinding
import com.example.projectandroid.ui.MenuActivity
import com.example.projectandroid.ui.adapter.AgentListAdapter
import com.example.projectandroid.ui.viewmodel.AgentViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.list_item_agent.view.*

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel: AgentViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, AgentViewModel.AgentViewModelFactory(activity.application))[AgentViewModel::class.java]
    }
    private var viewModelAdapter: AgentListAdapter? = null
    private var size: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAgents().observe(this.viewLifecycleOwner){
            viewModelAdapter?.submitList(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        getCurrentActivity()?.getBottomNav()?.visibility = View.VISIBLE

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModelAdapter = AgentListAdapter{agent, view ->
            getCurrentActivity()?.getBottomNav()?.visibility = View.GONE
            //kalo pakai binding.recyclerView bisa
            val extra = FragmentNavigatorExtras(view.agentIcon to "big_icon")
            val action = HomeFragmentDirections
                .actionHomeFragmentToAgentDetailFragment(agent.uuid)
            findNavController().navigate(action, extra)
        }
        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        binding.swipeContainer.setOnRefreshListener {
            viewModel.refreshAgent()
            swipeContainer.isRefreshing = false
        }

        binding.shimmerContainer.startShimmer()
        binding.recyclerView.visibility = View.GONE
        Handler(Looper.getMainLooper()).postDelayed({
            binding.shimmerContainer.stopShimmer()
            binding.shimmerContainer.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        },1000)

        return binding.root
    }

    private fun getCurrentActivity(): MenuActivity?{
        return (activity as? MenuActivity)
    }

}
