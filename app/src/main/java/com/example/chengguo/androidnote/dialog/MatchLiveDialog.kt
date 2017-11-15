

import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.ViewGroup
import com.ghoome.g1.R
import com.ghoome.g1.entity.live.LiveLineResp
import com.ghoome.g1.ui.base.BaseActivity
import kotlinx.android.synthetic.main.dialog_match_live_line.*

/**
 *
 *  这个dialog实现了了背景渐变
 *
 *
 * Created by Chengguo on 2017/7/27.
 *
 *    <!--  BaseDialogFragment 的默认 style -->
<style name="BaseDialogFragmentStyle" parent="@android:style/Theme.Dialog">
<item name="android:windowNoTitle">true</item>
<item name="android:windowBackground">@android:color/transparent</item>
<item name="android:colorBackgroundCacheHint">@null</item>
<item name="android:windowSoftInputMode">adjustPan</item>
<item name="android:windowIsFloating">true</item>
</style>
 */
class MatchLiveDialog(val activity: BaseActivity<*>,private val itemClick: (Int, Int) -> Unit)
    : Dialog(activity, R.style.BaseDialogFragmentStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_match_live_line)

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val lp = window.attributes
        //调节灰色背景透明度[0-1]，默认0.5f
        lp.dimAmount = 0.5f
        //是否在底部显示
        lp.gravity = Gravity.BOTTOM
        //设置dialog进入、退出的动画
        window.setWindowAnimations(R.style.BottomAnimation)

//        <!--  下滑进入，下滑退出 -->
//        <style name="BottomAnimation" parent="android:Animation">
//        <item name="android:windowEnterAnimation">@anim/anim_trany_1_0</item>
//        <item name="android:windowExitAnimation">@anim/anim_trany_0_1</item>
//        </style>

        window.attributes = lp

        //初始化界面控件的事件
        initEvent()
    }

    private fun initEvent() {


    }
}