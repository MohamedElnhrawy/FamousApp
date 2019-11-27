package com.example.famousapp.ui.populars

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.famousapp.data.model.Person
import com.example.famousapp.famous.ui.base.BaseAdapter
import com.example.famousapp.famous.utils.interfaces.onViewItemClicked
import java.util.*
import kotlin.collections.ArrayList

class PopularsAdapter(
    parentLifecycle: Lifecycle,
    val populars: ArrayList<Person>, val filterList : ArrayList<Person>, private val itemListener: onViewItemClicked?)
    : BaseAdapter<Person, PopularItemViewHolder>(parentLifecycle, populars,filterList) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PopularItemViewHolder(parent,itemListener)

    fun FilterList(name: String) {
        var name = name
        name = name.toLowerCase(Locale.getDefault())
        populars.clear()
        if (name.length == 0) {
            populars.addAll(filterList)
        } else {
            for (nam in filterList) {
                if (nam.name.toLowerCase(Locale.getDefault()).contains(name)) {
                    populars.add(nam)
                }
            }
        }
        notifyDataSetChanged()
    }

}