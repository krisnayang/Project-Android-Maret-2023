package com.example.projectandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectandroid.data.local.localdatasource.DatabaseAgent
import com.example.projectandroid.databinding.ListItemAgentBinding

class AgentListAdapter(
    private val clicklistener: (DatabaseAgent) -> Unit
): ListAdapter<DatabaseAgent, AgentListAdapter.AgentViewHolder>(DiffCallback) {
    class AgentViewHolder(
        private var binding: ListItemAgentBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(agent: DatabaseAgent){
            binding.agent = agent
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<DatabaseAgent>() {
        override fun areItemsTheSame(oldItem: DatabaseAgent, newItem: DatabaseAgent): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: DatabaseAgent, newItem: DatabaseAgent): Boolean {
            return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AgentViewHolder(
            ListItemAgentBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val agent = getItem(position)
        holder.itemView.setOnClickListener{
            clicklistener(agent)
        }
        holder.bind(agent)
    }
}