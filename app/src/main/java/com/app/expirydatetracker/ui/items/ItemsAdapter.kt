package com.app.expirydatetracker.ui.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.expirydatetracker.databinding.RowExpiryItemBinding
import com.app.expirydatetracker.models.ExpiryItem

class ItemsAdapter(
    private var languageList: List<ExpiryItem>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private lateinit var context: Context

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(
        val binding: RowExpiryItemBinding,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowExpiryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context

        return ViewHolder(binding, onItemClicked)
    }

    // bind the items with each item of the list languageList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(languageList[position]){
                binding.itemNameText.text = this.name
                binding.itemIcon.setImageDrawable(ContextCompat.getDrawable(context, this.category.icon))
                binding.expiryDateText.text = this.dateExpiringFormatted
            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return languageList.size
    }
}