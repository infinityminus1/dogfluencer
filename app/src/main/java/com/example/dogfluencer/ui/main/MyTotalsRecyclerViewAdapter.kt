package com.example.dogfluencer.ui.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogfluencer.Event
import com.example.dogfluencer.R
import com.example.dogfluencer.Schedule
import com.example.dogfluencer.ui.main.TotalsFragment.OnListFragmentInteractionListener
import com.example.dogfluencer.ui.main.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_totals.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTotalsRecyclerViewAdapter(
    private var mValues: Map<String, Schedule>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyTotalsRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Event
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    fun update() {
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_totals, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listOfSchedules = mValues.values.toList()
//        val item = listOfSchedules[position].getTotalEvents()
        val item = listOfSchedules[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.getTotalEvents().toString()

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
