package com.example.baseprob

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.NonNull
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseprob.databinding.QuizzItemBinding
import android.view.animation.Animation
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.ListAdapter

class QuizzAdapter(val listener: Listener):RecyclerView.Adapter<QuizzAdapter.QuizzHolder> (){
    val quizlist = ArrayList<Quizz>()
    class QuizzHolder(item:View):RecyclerView.ViewHolder(item) {
        val binding = QuizzItemBinding.bind(item)
        fun bind(quizz: Quizz, listener: Listener) = with(binding){
            title.text = quizz.title
            imageView.setImageResource(quizz.imageId)
            itemView.setOnClickListener{
                listener.onClick(quizz)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizzHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quizz_item, parent, false)
        return QuizzHolder(view)
    }

    override fun onBindViewHolder(holder: QuizzHolder, position: Int) {
        holder.bind(quizlist[position], listener )

    }


    override fun getItemCount(): Int {
        return quizlist.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addquizz(quizz: Quizz){
        quizlist.add(quizz)
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClick(quizz: Quizz)
    }
}