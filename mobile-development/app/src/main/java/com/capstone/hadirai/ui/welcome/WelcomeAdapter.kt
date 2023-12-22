package com.capstone.hadirai.ui.welcome

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.hadirai.data.model.WelcomeImage
import com.capstone.hadirai.databinding.PagerItemBinding

class WelcomeAdapter(private val context: Context, private val imageList: List<WelcomeImage>) :
    RecyclerView.Adapter<WelcomeAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = PagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val imageList = imageList[position]
        holder.bind(imageList)

    }

    class PagerViewHolder(private var itemHolderBinding: PagerItemBinding) :
        RecyclerView.ViewHolder(itemHolderBinding.root) {
        fun bind(label: WelcomeImage) {
            itemHolderBinding.itemTitle.text = (label.title)
            itemHolderBinding.itemImage.setImageResource(label.image)
        }
    }

}