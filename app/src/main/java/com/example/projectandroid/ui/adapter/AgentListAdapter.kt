package com.example.projectandroid.ui.adapter

import android.content.Context
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectandroid.R
import com.example.projectandroid.data.local.model.Agent
import com.example.projectandroid.databinding.ListItemAgentBinding
import kotlinx.coroutines.withContext

class AgentListAdapter(
    private val clickListener: (Agent) -> Unit
): ListAdapter<Agent, AgentListAdapter.AgentViewHolder>(DiffCallback) {
    private lateinit var context: Context

    companion object DiffCallback: DiffUtil.ItemCallback<Agent>() {
        override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val withDataBinding: ListItemAgentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AgentViewHolder.LAYOUT,
            parent,
            false)
        context = parent.context
        return AgentViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val agent = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(agent)
//            val extra = FragmentNavigatorExtras(holder.viewDataBinding.agentIcon to "big_icon")
        }
        Glide.with(context).load(agent.displayIcon).into(holder.viewDataBinding.agentIcon)
        holder.bind(agent)
    }

    class AgentViewHolder(val viewDataBinding: ListItemAgentBinding):
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.list_item_agent
        }
        fun bind(agent: Agent){
            viewDataBinding.agent = agent
            viewDataBinding.executePendingBindings()
        }
    }

}