package com.hevalvural.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hevalvural.todolist.databinding.ActivityMainBinding
import com.hevalvural.todolist.databinding.RecylerRowBinding

class PlanAdapter(val planList : ArrayList<Plan>) : RecyclerView.Adapter<PlanAdapter.PlanHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlanHolder {
        val binding = RecylerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlanHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PlanHolder,
        position: Int
    ) {
        holder.binding.todoTitleTextView.text = planList[position].title
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AddNoteActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return planList.size
    }

    class PlanHolder(val binding: RecylerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }
}