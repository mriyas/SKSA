package com.suraksha.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.suraksha.android.view.utility.callbacks.ClickHelper
import com.suraksha.app.BR
import com.suraksha.app.R
import java.util.*
import kotlin.collections.ArrayList

class UniversalRecyclerViewAdapter<T>(
    private var items: List<T>?,
    private val itemLayoutId: Int
) : RecyclerView.Adapter<UniversalRecyclerViewAdapter.RecyclerBaseViewHolder<T>>(), Filterable {
    var fr: Any? = null
    private var clickHelper: ClickHelper? = null
    private var parentData:Any?= null
    var searchableList: ArrayList<T>? = arrayListOf()

    var addFooter = true

    val VIEW_TYPE_ITEM = 1
    val VIEW_TYPE_FOOTER = 0


    init {
        //  val newArr: ArrayList<T>?=if(items.isNullOrEmpty()) arrayListOf() else ArrayList(items)
        this.searchableList = arrayListOf()
    }

    override fun getItemViewType(position: Int): Int {

        return if (position >= searchableList?.size!! && addFooter) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    fun setListener(clickHelper: ClickHelper?) {
        this.clickHelper = clickHelper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerBaseViewHolder<T> {
        if (viewType == VIEW_TYPE_FOOTER) {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, R.layout.rv_empty_layout, parent, false)
            return RecyclerFooterViewHolder(binding)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, itemLayoutId, parent, false)
            return RecyclerItemViewHolder(binding)
        }


    }

    override fun onBindViewHolder(holder: RecyclerBaseViewHolder<T>, position: Int) {
        if (holder is RecyclerItemViewHolder) {
            val item = searchableList?.get(position)
            holder.bind(item, fr, clickHelper,parentData)
        }
    }



    fun setData(data: List<T>?) {
        items = data
        val newArr: ArrayList<T>? = if (data.isNullOrEmpty()) arrayListOf() else ArrayList(data)
        this.searchableList = newArr
        notifyDataSetChanged()
    }

    fun setClassTimeData(data: List<T>?,classDay:Any?) {
        parentData =classDay
        items = data
        val newArr: ArrayList<T>? = if (data.isNullOrEmpty()) arrayListOf() else ArrayList(data)
        this.searchableList = newArr
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int =
        if (searchableList.isNullOrEmpty()) 0 else if (addFooter) searchableList?.size!! + 1 else searchableList?.size!!

    class RecyclerItemViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerBaseViewHolder<T>(binding) {
        fun bind(
            item: T?,
            fr: Any?,
            clickHelper: ClickHelper?,
            parentData: Any?
        ) {
           /* binding.setVariable(BR.data, item)
            binding.setVariable(BR.fr, fr)
            binding.setVariable(BR.clickHelper, clickHelper)
            binding.setVariable(BR.parentData,parentData)*/


            binding.executePendingBindings()
        }
    }

    class RecyclerFooterViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerBaseViewHolder<T>(binding) {

    }

    open class RecyclerBaseViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private var onNothingFound: (() -> Unit)? = null

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                searchableList?.clear()
                if (constraint.isNullOrBlank()) {
                    searchableList?.addAll(items!!)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                    for (item in 0..items?.size!!) {
                        val it = items?.get(item)
                       //TOOD: Do filter logic

                    }

                }
                return filterResults.also {
                    it.values = searchableList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (searchableList.isNullOrEmpty())
                    onNothingFound?.invoke()
                notifyDataSetChanged()
            }


        }
    }


}