package com.ghoome.g1.ui.widget.popup_window

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.ghoome.g1.R
import com.ghoome.g1.app.BaseApplication
import kotlinx.android.synthetic.main.ran_popup_dialog.view.*


/**
 * 解决了popupWindow在android7.0以上显示全屏的问题
 *
 * @author Chengguo on 2017/10/24.
 */

class SelectPopupWindow(context: Context, private var oddTypeTemp: String, private val arrays: List<String>) : PopupWindow() {
    private val conentView: View
    private var listener: OnItemClickListener? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        conentView = inflater.inflate(R.layout.ran_popup_dialog, null)
        val h = BaseApplication.mInstance.SCREEN_HEIGHT
        val w = BaseApplication.mInstance.SCREEN_WIDTH
        // 设置SelectPicPopupWindow的View
        this.contentView = conentView
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.width = w
//        // 设置SelectPicPopupWindow弹出窗体的高
        this.height = ViewGroup.LayoutParams.MATCH_PARENT
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.isFocusable = true
        this.isOutsideTouchable = true
        // 刷新状态
        this.update()
        // 实例化一个ColorDrawable颜色为半透明
        val dw = ColorDrawable(0)
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw)

        val addTaskLayout = conentView.rankRecyclerView as RecyclerView

        //创建默认的线性LayoutManager
        addTaskLayout.run {
            layoutManager = LinearLayoutManager(context)
            //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            setHasFixedSize(true)
            adapter = SelectPopupAdapter(oddTypeTemp, arrays, object : (String) -> Unit {
                override fun invoke(p1: String) {
                    listener?.onClickPosition(p1)
                    dismiss()
                }
            })
        }

        //点击阴影处消失
        conentView.rootView.setOnClickListener { dismiss() }
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    fun showPopupWindow(parent: View) {
        if (!isShowing) {
            // 以下拉方式显示popupwindow
            showAsDropDown(parent)
        } else {
            dismiss()
        }
    }

    /**
     * 设置监听器
     */
    fun setOnItemClickListener(listener2: OnItemClickListener) {
        this.listener = listener2
    }

    interface OnItemClickListener {
        fun onClickPosition(position: String)
    }

    /**
     *  在android7.0上，如果不主动约束PopuWindow的大小，比如，设置布局大小为 MATCH_PARENT,那么PopuWindow会变得尽可能大，
     *  以至于 view下方无空间完全显示PopuWindow，而且view又无法向上滚动，此时PopuWindow会主动上移位置，直到可以显示完全。
     *　解决办法：主动约束PopuWindow的内容大小，重写showAsDropDown方法：
     * @param anchor
     */
    override fun showAsDropDown(anchor: View) {
        if (Build.VERSION.SDK_INT >= 24) {
            val visibleFrame = Rect()
            anchor.getGlobalVisibleRect(visibleFrame)
            val height = anchor.resources.displayMetrics.heightPixels - visibleFrame.bottom
            setHeight(height)
        }
        super.showAsDropDown(anchor)
    }
}