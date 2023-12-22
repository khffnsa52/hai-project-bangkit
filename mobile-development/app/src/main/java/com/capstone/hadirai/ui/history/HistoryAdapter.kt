package com.capstone.hadirai.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstone.hadirai.R
import com.capstone.hadirai.data.remote.response.History
import com.capstone.hadirai.databinding.ItemHistoryBinding


class HistoryAdapter : ListAdapter<History, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.tvItemDate.text = "Date : "+ history.date
            binding.tvItemTime.text = "Time : " + history.time
            Glide.with(binding.root.context)
                .load(history.imgUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imgAvatar)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
        }
    }
}
//class HistoryAdapter : ListAdapter<History, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val history = getItem(position)
//        holder.bind(history)
//    }
//
//    class MyViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(history: History){
//            binding.tvItemDate.text = history.date
//            binding.tvItemTime.text = history.time
//            Glide.with(binding.root.context)
//                .load(history.imgUrl)
//                .apply(RequestOptions.circleCropTransform())
//                .into(binding.imgAvatar)
//        }
//    }
//
//    companion object {
//        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>() {
//            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//}

//class HistoryAdapter(private val listHistory: ArrayList<History>) :RecyclerView.Adapter<HistoryAdapter.ListViewHolder>(){
//    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val date : TextView = itemView.findViewById(R.id.tv_item_date)
//        val time : TextView = itemView.findViewById(R.id.tv_item_time)
//        val img : ImageView = itemView.findViewById(R.id.imageView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
////        TODO("Not yet implemented")
//        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
//        return ListViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = listHistory.size
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
////        TODO("Not yet implemented")
//        val(date,imgUrl, time) = listHistory[position]
//        holder.date.text = date
//        holder.time.text = time
//        Glide.with(holder.img.context)
//            .load(imgUrl)
//            .into(holder.img)
//
//    }
//
//}