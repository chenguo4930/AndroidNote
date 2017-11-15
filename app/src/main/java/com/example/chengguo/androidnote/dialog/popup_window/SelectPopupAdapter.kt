package com.ghoome.g1.ui.widget.popup_window

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghoome.g1.R
import com.ghoome.g1.app.BaseApplication
import kotlinx.android.synthetic.main.ran_popup_dialog_item.view.*

/**
 *
 * @author Chengguo on 2017/10/24.
 */
class SelectPopupAdapter(private val selectItem: String, private val dates: List<String>, private val itemClick: (String) -> Unit)
    : RecyclerView.Adapter<SelectPopupAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.ran_popup_dialog_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItem(dates[position], selectItem)
    }

    override fun getItemCount(): Int = dates.size

    class ViewHolder(view: View, private val itemClick: (String) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindItem(itemValue: String, selectItemTemp: String) {
            itemView.itemTv.text = itemValue
            if (itemValue == selectItemTemp) {
                itemView.itemSelectedImg.visibility = View.VISIBLE
                itemView.itemTv.setTextColor(BaseApplication.mApplicationContext.resources.getColor(R.color.color_387fce))
            } else {
                itemView.itemSelectedImg.visibility = View.GONE
                itemView.itemTv.setTextColor(BaseApplication.mApplicationContext.resources.getColor(R.color.color_999999))
            }

            itemView.setOnClickListener {
                itemClick(itemValue)
            }
        }
    }
}
