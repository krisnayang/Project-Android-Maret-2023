package com.example.projectandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectandroid.R
import com.example.projectandroid.data.local.model.Agent
import com.example.projectandroid.databinding.ListItemAgentBinding
import kotlinx.coroutines.withContext

class AgentListAdapter(

): RecyclerView.Adapter<AgentListAdapter.AgentViewHolder>() {
    private lateinit var context: Context
    var agents: List<Agent> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
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

    override fun getItemCount()= agents.size

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.agent = agents[position]
            Glide.with(context).load(agents[position].displayIcon).into(holder.viewDataBinding.agentIcon)
        }
    }

    class AgentViewHolder(val viewDataBinding: ListItemAgentBinding):
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.list_item_agent
        }
    }

}