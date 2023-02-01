package com.example.marvelcomicslist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcomicslist.R
import com.example.marvelcomicslist.core.base.BaseViewHolder
import com.example.marvelcomicslist.databinding.ItemComicListBinding
import com.example.marvelcomicslist.domain.models.Comic

class ComicAdapter(
    private val context: Context,
    private val comicClickListener: OnComicClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val listComic: ArrayList<Comic> = arrayListOf()

    interface OnComicClickListener {
        fun onComicClick(comic: Comic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemComicListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ComicViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ComicViewHolder -> holder.bind(listComic[position])
        }
    }

    override fun getItemCount() = listComic.size

    inner class ComicViewHolder(private val itemBinding: ItemComicListBinding) :
        BaseViewHolder<Comic>(itemBinding.root) {
        override fun bind(item: Comic): Unit = with(itemBinding) {
            Glide.with(context).load(item.image).placeholder(R.drawable.load).into(thumbnail)
            title.text = item.title
            itemView.setOnClickListener {
                comicClickListener.onComicClick(item)
            }
        }
    }

    fun dataSet(comic: Collection<Comic>){
        listComic.clear()
        listComic.addAll(comic)
        notifyDataSetChanged()
    }
}