package com.irfan.rcviewadopter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irfan.recyclerviewadapter.BR


/**
 * @author Irfan Khan
 *
 * @Definition Generic adaptor that handle data rendering in Recycler view
 *
 * @constructor create instance of the adaptor with specified context and listeners
 * @param Context  binding context
 * @param ItemLayoutManger view handler
 * @param ItemClickListener Click Listener
 */


open class RcAdapter<T>(
    private val context: Context,
    private val itemLayoutManger: ItemLayoutManger
) :
    RecyclerView.Adapter<MViewHolder>() {

    private var itemList = ArrayList<T>()
    private var recyclerview: RecyclerView? = null



    /**
     * Attaching to recycler view
     * @param RecyclerView
     * @param Recycler view Layout manager
     */
    fun bindRecyclerView(
        recyclerview: RecyclerView,
        layoutManger: RecyclerView.LayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
    ) {

        this.recyclerview = recyclerview
        this.recyclerview!!.adapter = this
        this.recyclerview!!.layoutManager = layoutManger
    }

    /**
     * Detaching from recyclerView to avoid memory leak
     */
    fun detachRecyclerView() {
        this.recyclerview?.adapter = null
        this.recyclerview = null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, viewType, parent, false)
        return MViewHolder(binding, itemLayoutManger)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return itemLayoutManger.getLayoutId(position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItems(newItemList: ArrayList<T>) {
        if (newItemList.isNullOrEmpty()) return
        itemList = newItemList
        this.notifyDataSetChanged()
    }

    fun replaceItems(newItemList: ArrayList<T>) {
        if (newItemList.isNullOrEmpty()) return
        itemList.clear()
        itemList = ArrayList()
        this.itemList.addAll(newItemList)
        this.notifyDataSetChanged()
    }

    fun addItemsAt(newItemList: ArrayList<T>, position: Int) {
        if (newItemList.isNullOrEmpty() || position < 0) return
        this.itemList.addAll(position, newItemList)
        this.notifyItemRangeInserted(position, position + newItemList.count())
    }

    fun addItems(items: ArrayList<T>) {
        if (items.isNullOrEmpty()) return
        this.itemList.addAll(items)
        this.notifyItemRangeInserted(itemList.indices.last, items.count())
    }

    fun addItem(item: T) {
        this.itemList.add(item)
        this.notifyItemInserted(itemList.indexOf(item))
    }

    fun addItem(item: T, position: Int = -1) {
        this.itemList.add(position, item)
        this.notifyItemInserted(position)
    }

    fun getItem(position: Int): T {
        Log.d("DebugNOtif", "getItem Position: $position")
        return this.itemList[position]
    }

    fun getItems(): ArrayList<T> {
        return this.itemList
    }

    fun removeItem(position: Int) {
        Log.d("DebugNOtif", "removeItem Position: $position")
        if (position < 0) return
        this.itemList.removeAt(position)
        this.notifyItemRemoved(position)
    }

    fun removeItem(item: T) {
        this.itemList.remove(item)
        this.notifyItemRemoved(itemList.indexOf(item))
    }
}

class MViewHolder(
    private val binding: ViewDataBinding,
    private val layoutManger: ItemLayoutManger
) : RecyclerView.ViewHolder(binding.root) {

    internal fun <T>  bind(item: T) {

        binding.setVariable(BR.dataObject, item)
        layoutManger.bindView(binding.root, ::getAdapterPosition)
        binding.executePendingBindings()
    }
}


interface ItemLayoutManger {
    fun getLayoutId(position: Int): Int
    fun bindView(view: View, getAdapterPosition: () -> Int) {
    }
}