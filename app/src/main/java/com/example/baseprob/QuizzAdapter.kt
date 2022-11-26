package com.example.baseprob


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseprob.databinding.QuizzItemBinding

class QuizzAdapter(val listener: MainActivity):RecyclerView.Adapter<QuizzAdapter.QuizzHolder> (){
    val quizlist = ArrayList<Quizz>()
    class QuizzHolder(item:View):RecyclerView.ViewHolder(item) {
        val binding = QuizzItemBinding.bind(item)
        fun bind(quizz: Quizz, listener: Listener) = with(binding){
            title.text = quizz.title
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