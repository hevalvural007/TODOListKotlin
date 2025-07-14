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

    override fun onBindViewHolder(holder: PlanHolder, position: Int) {
        val plan = planList[position]
        holder.binding.todoTitleTextView.text = plan.title

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("planId", plan.id)
            intent.putExtra("planTitle", plan.title)
            intent.putExtra("planDescription", plan.description)
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return planList.size
    }



    class PlanHolder(val binding: RecylerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }
}